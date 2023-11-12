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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import programSegment.CondType;
import programSegment.ConstType;
import programSegment.FactorType;
import programSegment.ForType;
import programSegment.IfType;
import utils.ProgramPort;
import utils.ProgramPortSet;
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
 * @author djordje remove ports with numbers and refVar as name.
 */
public class PortCleanup extends XmlProcessorAdapter {

    private String refVar;
    private Segment segment;

    public PortCleanup(String refVar, Segment segment) {
        this.refVar = refVar;
        this.segment = segment;
    }

    private boolean shouldRemove(Port port) {
        if (refVar.equals(port.getName())) {
            return true;
        } else {
            try {
                Double.parseDouble(port.getName());
                return true;
            } catch (NumberFormatException ex) {
            }
        }
        return false;
    }

    @Override
    public void startProcessing() {
        try {
            processProgramSegment(segment.getProgram());
        } catch (TGException ex) {
        }

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

        return null;
    }

    private void processOutPorts(OutPortType outPortType) {

        List<Port> ports = outPortType.getPort();
        processPortList(ports);
    }

    private void processPortList(List<Port> ports) {
        if (ports != null) {
            for (int i = 0; i < ports.size();) {
                Port p = ports.get(i);
                if (shouldRemove(p)) {
                    ports.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    private void processInPorts(InPortType inPortType) {

        if (inPortType.getPort() != null) {
            List<Port> ports = inPortType.getPort();
            processPortList(ports);
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

}
