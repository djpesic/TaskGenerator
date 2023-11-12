/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import abstractions.ConstSegment;
import abstractions.IfSegment;
import abstractions.Loop;
import abstractions.ProgramSegment;
import abstractions.ProgramSequence;
import gui.treedata.TGException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.math.NumberUtils;
import programSegment.CondType;
import programSegment.ConstType;
import programSegment.FactorType;
import programSegment.ForType;
import programSegment.IfType;
import programSegment.InPortType;
import programSegment.InPortType.Port;
import programSegment.LoopType;
import programSegment.Nested;
import programSegment.OutPortType;
import programSegment.ProgramType;
import programSegment.Segment;
import programSegment.Sequence;
import programSegment.SignedFactorType;
import programSegment.SimpleExprType;
import programSegment.SimpleLoop;
import programSegment.TermType;
import syntaxelements.nonterminals.Expression;
import syntaxelements.nonterminals.Factor;
import syntaxelements.nonterminals.SignedFactor;
import syntaxelements.nonterminals.SimpleExpression;
import syntaxelements.nonterminals.Term;
import syntaxelements.nonterminals.Variable;

/**
 *
 * @author djordje
 */
public class SegmentRenamer extends XmlProcessorAdapter {

    protected Map<String, String> renamingMap = new HashMap<>();// old->new
    protected Segment seg1;
    protected Segment seg2;
    Set<String> portNames;

    public SegmentRenamer(Segment seg1, Segment seg2) {
        this.seg1 = seg1;
        this.seg2 = seg2;
    }

    protected List<String> collectPortNames(Segment segment) {
        List<String> result = new LinkedList<>();
        try {
            JXPathContext context = JXPathContext.newContext(segment);

            context.setLenient(true);
            for (Iterator<String> it = context.iterate("//inPorts/port/@name"); it.hasNext();) {
                result.add(it.next());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void startProcessing() {

        renameInSegments(seg2, seg1);
        applyPortMapping();
    }

    public void applyPortMapping() {
        //process netlist: apply port mapping. input ports take names from connected outputs.
        // rename map: old->new
        try {
            processProgramSegment(seg1.getProgram());
        } catch (TGException ex) {

        }
    }

    public void setRenamingMap(Map<String, String> renamingMap) {
        this.renamingMap = renamingMap;
    }

    protected void renameInSegments(Segment seg1, Segment seg2) {
        //check if there are same variable names in both seg1 and seg2, not included in port description. if so, rename it. 

        List<String> portNames1 = collectPortNames(seg1);
        List<String> portNames2 = collectPortNames(seg2);
        portNames = new HashSet<>();
        // same port names from both seg1 and seg2
        portNames = portNames1.stream().filter((p) -> portNames2.contains(p)).collect(Collectors.toSet());
        List<String> newPortNames2 = portNames2.stream().distinct().collect(Collectors.toList());
        portNames.stream().filter(name -> !renamingMap.containsKey(name) && !NumberUtils.isCreatable(name))
                .forEach(name -> {
                    long number = 0;
                    while (newPortNames2.contains(name + number)) {
                        number++;
                    }
                    renamingMap.put(name, name + number);
                });

    }

    @Override
    public ProgramSegment processProgramSegment(ProgramType programType) throws TGException {
        if (programType.getConst() != null) {
            processConst(programType.getConst());
        } else if (programType.getIf() != null) {
            processIf(programType.getIf());
        } else if (programType.getLoop() != null) {
            processLoopType(programType.getLoop());
        }
        return null;
    }

    @Override
    public IfSegment processIf(IfType ifType) throws TGException {
        if (ifType.getElseBranch() != null) {
            processProgramSegment(ifType.getElseBranch());
        } else if (ifType.getThenBranch() != null) {
            processProgramSegment(ifType.getThenBranch());
        } else if (ifType.getInPorts() != null) {
            processInPorts(ifType.getInPorts());
        }
        if (ifType.getOutPorts() != null) {
            processOutPorts(ifType.getOutPorts());
        }
        if (ifType.getLeft() != null) {
            processXmlSimpleExpr(ifType.getLeft());
        }
        if (ifType.getRight() != null) {
            processXmlSimpleExpr(ifType.getRight());
        }
        return null;
    }

    private void processOutPorts(OutPortType outPortType) {
        if (outPortType.getIter() != null) {
            String newName = renamingMap.get(outPortType.getIter().getName());
            if (newName != null) {
                outPortType.getIter().setName(newName);
            }
        }
        List<Port> ports = outPortType.getPort();
        if (ports != null) {
            for (int i = 0; i < ports.size(); i++) {
                Port p = ports.get(i);
                String newName = renamingMap.get(p.getName());
                if (newName != null) {
                    p.setName(newName);
                }
            }
        }
    }

    private void processInPorts(InPortType inPortType) {
        if (inPortType.getLower() != null) {
            InPortType.Lower lower = inPortType.getLower();
            String newName = renamingMap.get(lower.getName());
            if (newName != null) {
                lower.setName(newName);
            }
        }
        if (inPortType.getUpper() != null) {
            InPortType.Upper upper = inPortType.getUpper();
            String newName = renamingMap.get(upper.getName());
            if (newName != null) {
                upper.setName(newName);
            }
        }
        if (inPortType.getPort() != null) {
            List<Port> ports = inPortType.getPort();
            if (ports != null) {
                for (int i = 0; i < ports.size(); i++) {
                    Port p = ports.get(i);
                    String newName = renamingMap.get(p.getName());
                    if (newName != null) {
                        p.setName(newName);
                    }
                }
            }
        }
    }

    @Override
    public Loop processCondLoop(CondType c, boolean isWhile) {
        if (c.getPorts() != null) {
            if (c.getPorts().getInPorts() != null) {
                processInPorts(c.getPorts().getInPorts());
            }
            if (c.getPorts().getOutPorts() != null) {
                processOutPorts(c.getPorts().getOutPorts());
            }
        }
        return null;
    }

    @Override
    public ConstSegment processConst(ConstType xmlConst) {
        if (xmlConst.getInPorts() != null) {
            processInPorts(xmlConst.getInPorts());
        }
        if (xmlConst.getOutPorts() != null) {
            processOutPorts(xmlConst.getOutPorts());
        }
        if (xmlConst.getExpr() != null) {
            String newName = renamingMap.get(xmlConst.getExpr().getVarName());
            if (newName != null) {
                xmlConst.getExpr().setVarName(newName);
            }
            processXmlSimpleExpr(xmlConst.getExpr().getSimpleExpr());
        }
        return null;
    }

    @Override
    public Loop processForLoop(ForType f) {
        if (f.getInPorts() != null) {
            processInPorts(f.getInPorts());
        }
        if (f.getOutPorts() != null) {
            processOutPorts(f.getOutPorts());
        }
        return null;
    }

    @Override
    public ProgramSegment processNested(Nested nested) throws TGException {
        processProgramSegment(nested.getInner());
        processLoopType(nested.getOuter());
        return null;
    }

    @Override
    public ProgramSequence processSequence(Sequence sequence) throws TGException {
        for (ProgramType progType : sequence.getProgram()) {
            processProgramSegment(progType);
        }
        return null;
    }

    @Override
    public Loop processSimpleLoop(SimpleLoop xmlLoop) {
        if (xmlLoop.getFor() != null) {
            processForLoop(xmlLoop.getFor());
        } else if (xmlLoop.getRepeat() != null) {
            processCondLoop(xmlLoop.getRepeat(), false);
        } else if (xmlLoop.getWhile() != null) {
            processCondLoop(xmlLoop.getWhile(), true);
        }
        return null;
    }

    @Override
    public Factor processXmlFactor(FactorType xmlFactor) {

        if (xmlFactor.getNotFactor() != null) {
            processXmlFactor(xmlFactor.getNotFactor());
        }
        if (xmlFactor.getSubExpr() != null) {
            processXmlSubExpr(xmlFactor.getSubExpr());
        }
        if (xmlFactor.getVar() != null) {
            processXmlVar(xmlFactor.getVar());
        }
        return null;
    }

    @Override
    public LoopType processLoopType(LoopType loopType) throws TGException {
        if (loopType.getNested() != null) {
            processNested(loopType.getNested());
        } else if (loopType.getSequence() != null) {
            processSequence(loopType.getSequence());
        } else if (loopType.getSimpleLoop() != null) {
            processSimpleLoop(loopType.getSimpleLoop());
        }
        return null;
    }

    @Override
    public SignedFactor processXmlSignedFactor(SignedFactorType xmlSignedFactor) {
        if (xmlSignedFactor.getFactor() != null) {
            processXmlFactor(xmlSignedFactor.getFactor());
        }
        return null;
    }

    @Override
    public Variable processXmlIndexed(FactorType.Var.Indexed xmlIndexed) {
        String newName = renamingMap.get(xmlIndexed.getName());
        if (newName != null) {
            xmlIndexed.setName(newName);
        }
        processXmlSimpleExpr(xmlIndexed.getSimpleExpr());
        for (FactorType.Var.Indexed.CommaExpr ce : xmlIndexed.getCommaExpr()) {
            processXmlSimpleExpr(ce.getSimpleExpr());
        }
        return null;
    }

    @Override
    public SimpleExpression processXmlSimpleExpr(SimpleExprType xmlSimpleExpr) {
        processXmlTerm(xmlSimpleExpr.getTerm());
        for (SimpleExprType.AddopTerm at : xmlSimpleExpr.getAddopTerm()) {
            processXmlTerm(at.getTerm());
        }
        return null;
    }

    @Override
    public Expression processXmlSubExpr(FactorType.SubExpr xmlSubExpr) {
        processXmlSimpleExpr(xmlSubExpr.getSimpleExpr());
        return null;
    }

    @Override
    public Term processXmlTerm(TermType xmlTerm) {
        processXmlSignedFactor(xmlTerm.getSignedFactor());
        for (TermType.MulopSignedFactor msf : xmlTerm.getMulopSignedFactor()) {
            processXmlSignedFactor(msf.getSignedFactor());
        }
        return null;
    }

    @Override
    public Variable processXmlVar(FactorType.Var xmlVar) {
        String newName = renamingMap.get(xmlVar.getName());
        if (newName != null) {
            xmlVar.setName(newName);
        }
        if (xmlVar.getIndexed() != null) {
            processXmlIndexed(xmlVar.getIndexed());
        }
        return null;
    }
}
