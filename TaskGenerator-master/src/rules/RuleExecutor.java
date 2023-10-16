/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules;

import abstractions.ProgramSegment;
import com.rits.cloning.Cloner;
import complexity.Complexity;
import complexity.ComplexityOperations;
import gui.treedata.TGException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.javatuples.Pair;
import utils.XmlMarshaller;
import programSegment.ComplType;
import programSegment.IfType;
import programSegment.LoopType;
import programSegment.Nested;
import programSegment.ProgramType;
import programSegment.Segment;
import programSegment.Sequence;
import rules.TemplateType.VarMap;
import utils.YacasExecutor;
import xmlprocessors.IfPortAdjuster;
import xmlprocessors.IfSegmentRenamer;
import xmlprocessors.PortAdjuster;
import xmlprocessors.PortCleanup;
import xmlprocessors.SegmentHardRenamer;
import xmlprocessors.SegmentRenamer;
import xmlprocessors.TwoMostDeepFinder;
import xmlprocessors.XmlSegmentTypeDeterminator;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * @author djordje
 */
public class RuleExecutor {
    
    private String ruleName;
    private Segment newSegment;
    private String ruleOutput;
    private Rule rule;
    private Segment seg1, seg2;
    private boolean skipCalc;
    
    public RuleExecutor(String ruleName) {
        this.ruleName = ruleName;
        ruleOutput = ruleName.substring(0, ruleName.lastIndexOf(".")) + "Output.xml";
    }
    
    public RuleExecutor(Rule r, boolean skipCalc) {
        rule = r;
        this.skipCalc = skipCalc;
    }
    
    public void setRule(Rule rule) {
        this.rule = rule;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    public Segment getNewSegment() {
        return newSegment;
    }
    
    public ProgramSegment generateCode() {
        XmlBasedGenerator gen = new XmlBasedGenerator(ruleOutput);
        return gen.generate();
    }
    
    private Segment createSegment(TemplateType template) throws TGException {

        // create segment from its xml file or use already created
        XmlBasedGenerator gen;
        Segment seg = null;
        String fileName = getFileName(template);
        String rule = template.getRuleName();
        if (fileName != null) {
            gen = new XmlBasedGenerator(fileName);
            seg = gen.unmarshall();
        } else if (rule != null) {
            // call rule1. forward its output to current rule, as a template
            RuleExecutor ruleExec = new RuleExecutor(rule);
            ruleExec.executeRule();
            seg = ruleExec.newSegment;
        } else if (template instanceof Template) {
            seg = ((Template) template).getSeg();
        }
        
        applyVarMapping(template, seg);
        return seg;
    }
    
    public void applyVarMapping(TemplateType template, Segment seg) {
        // if template instantiation requires variable remapping, read mappings
        // from the template and applies them
        if (!(template.getVarMap().isEmpty())) {
            SegmentHardRenamer renamer = new SegmentHardRenamer(seg);
            
            Map<String, String> renameMap = new HashMap<>();
            template.getVarMap().forEach(varMap -> renameMap.put(varMap.getCurrent(), varMap.getActual()));
            
            renamer.setRenamingMap(renameMap);
            renamer.applyPortMapping();
        }
    }
    
    public Segment executeRule() throws TGException {
        
        if (rule == null) {
            rule = XmlMarshaller.unmarshallRule("xml-resources/jaxb/rules/rules.xsd", ruleName);
        }
        // create program segment abstractions from template informations
        TemplateType template1 = null;
        TemplateType template2 = null;
        template1 = rule.getTemplate().get(0);
        template2 = rule.getTemplate().get(1);
        if (seg1 == null) {
            seg1 = createSegment(template1);
        } else {
            applyVarMapping(template1, seg1);
        }
        if (seg2 == null) {
            seg2 = createSegment(template2);
        } else {
            applyVarMapping(template2, seg2);
        }
        OperationType opType = rule.getOperation();
        
        Cloner cloner = new Cloner();
        Segment seg1C = cloner.deepClone(seg1);
        Segment seg2C = cloner.deepClone(seg2);
        
        seg1 = seg1C;
        seg2 = seg2C;
        
        boolean seg1ExprSeq = XmlSegmentTypeDeterminator.isExpressionSequence(seg1.getProgram());
        boolean seg2ExprSeq = XmlSegmentTypeDeterminator.isExpressionSequence(seg2.getProgram());
        boolean seg2EmptyWhileRepeat = XmlSegmentTypeDeterminator.isEmptyWhileRepeatLoop(seg2.getProgram());
        boolean seg1EmptyWhileRepeat = XmlSegmentTypeDeterminator.isEmptyWhileRepeatLoop(seg1.getProgram());
        Segment segResult;
        
        segResult = new Segment();
        ComplType compl1Type = seg1.getComplexity();
        ComplType compl2Type = seg2.getComplexity();
        Complexity compl1 = ComplexityOperations.createComplexity(compl1Type.getFunc());
        Complexity compl2 = ComplexityOperations.createComplexity(compl2Type.getFunc());
        Complexity complResult = null;
        Segment ifTemplate = null;

        /*check if there are same variable names in both segments not included in port description. if so, rename it.   
        process netlist: apply port mapping. input ports take names from connected outputs
        this processing is regular for nesting and sequencing.
        
        IF segment port processing: 
         */
        if (opType.getConnection() != null) {
            SegmentRenamer renamer;
            if (opType.getSelection() == null) {
                renamer = new SegmentRenamer(seg1, seg2);
            } else {
                ifTemplate = createSegment(opType.getSelection().getSelTemplate());
                renamer = new IfSegmentRenamer(segResult, seg1, seg2, ifTemplate);
            }
            Map<String, String> renameMap = new HashMap<>();
            opType.getConnection().forEach(conn -> renameMap.put(conn.getInPort(), conn.getOutPort()));
            
            renamer.setRenamingMap(renameMap);
            renamer.startProcessing();
        }

        // regulate ports: adapt inherited flags after operation execution and connection mapping
        PortAdjuster adjuster;
        if (opType.isSequence() != null) {
            adjuster = new PortAdjuster(seg1, seg2);
        } else if (opType.isNesting() != null) {
            adjuster = new PortAdjuster(seg2, seg1);
        } else {
            adjuster = new IfPortAdjuster(seg1, seg2, ifTemplate);
        }
        adjuster.startProcessing();

        // execute operation
        ProgramType resProgramType = new ProgramType();
        segResult.setProgram(resProgramType);
        boolean dataDepsFound = false;
        if (opType.isSequence() != null) {
            if (seg1EmptyWhileRepeat || seg2EmptyWhileRepeat) {
                System.out.println("Sequence not possible with empty while/repeat");
                return null;
            }
            Sequence seq = new Sequence();
            seq.getProgram().add(seg1.getProgram());
            seq.getProgram().add(seg2.getProgram());
            LoopType loopType = new LoopType();
            loopType.setSequence(seq);
            resProgramType.setLoop(loopType);

            //caluclate result complexity
            if (opType.getConnection().isEmpty()) {
                // no port connections (no data dependency between segments
                complResult = ComplexityOperations.sequence(compl1, compl2);
            } else {
                dataDepsFound = true;
            }
            
        } else if (opType.isNesting() != null) {
            TwoMostDeepFinder twoMostDeepFinder = new TwoMostDeepFinder(seg2.getProgram());
            twoMostDeepFinder.startProcessing();
            if (!twoMostDeepFinder.maxLevelSingle()) {
                return null;
            }
            if (twoMostDeepFinder.getFoundConst() != null) {
                Sequence seq = new Sequence();
                ProgramType progType = new ProgramType();
                progType.setConst(twoMostDeepFinder.getFoundConst());
                seq.getProgram().add(progType);
                seq.getProgram().add(seg1.getProgram());
                LoopType lType = new LoopType();
                lType.setSequence(seq);
                twoMostDeepFinder.getFoundConstAncestor().setConst(null);
                twoMostDeepFinder.getFoundConstAncestor().setLoop(lType);
            } else if (twoMostDeepFinder.getFoundSimpleLoop() != null) {
                Nested nested = new Nested();
                LoopType lType = new LoopType();
                lType.setSimpleLoop(twoMostDeepFinder.getFoundSimpleLoop());
                nested.setOuter(lType);
                nested.setInner(seg1.getProgram());
                twoMostDeepFinder.getFoundSimpleLoopAncestor().setSimpleLoop(null);
                twoMostDeepFinder.getFoundSimpleLoopAncestor().setNested(nested);
            }
            segResult.setProgram(seg2.getProgram());
            if (seg2.getProgram().getLoop() != null) {
                if (opType.getConnection().isEmpty()) {
                    // no port connections (no data dependency between segments
                    complResult = ComplexityOperations.multiply(compl1, compl2);
                } else {
                    dataDepsFound = true;
                }
            } else {
                System.out.println("Outer program segment must be loop!");
                return null;
            }
            if (!dataDepsFound) {
                if (seg1EmptyWhileRepeat || seg2EmptyWhileRepeat) {
                    System.out.println("Nesting not possible with"
                            + " empty while/repeat and without deps");
                    return null;
                }
            }
        } else if (opType.getSelection() != null) {
            if (seg1EmptyWhileRepeat || seg2EmptyWhileRepeat) {
                System.out.println("Selection not possible with empty while/repeat");
                return null;
            }
            IfType ifType = ifTemplate.getProgram().getIf();
            ifType.setThenBranch(seg1.getProgram());
            if (opType.getSelection().isUseElseBranch()) {
                ifType.setElseBranch(seg2.getProgram());
            }
            resProgramType.setIf(ifType);
            if (opType.getConnection().isEmpty()) {
                // no port connections (no data dependency between segments
                // complexity of the if segment is max(complexity of the else branch,
                // complexity of the if branch)
                if (opType.getSelection().isUseElseBranch()) {
                    complResult = ComplexityOperations.sequence(compl1, compl2);
                } else {
                    complResult = compl1;
                }
            } else {
                dataDepsFound = true;
            }
        }
        ComplType complResultType = new ComplType();
        if (complResult == null) {
            complResult = Complexity.createConstant(); // stub value. will be replaced later.
        }
        String result = complResult.toString();
//        if (compl1 != null) {
//            System.out.println("Compl1: " + compl1.toString());
//        }
//        if (compl2 != null) {
//            System.out.println("Compl2: " + compl2.toString());
//        }
//        System.out.println("ComplResult: " + result);
        String simple = YacasExecutor.simplify(result);
//        System.out.println("SimplifiedResult: " + simple);
        complResultType.setFunc(YacasExecutor.simplify(complResult.toString()));
        segResult.setComplexity(complResultType);

        // remove ports with numbers and refVar as name.
        PortCleanup portCleanup = new PortCleanup("n", segResult);
        portCleanup.startProcessing();

        //if data dependency exists, recalculate result complexity. For this, result segment must be postprocessed
        if (dataDepsFound) {
            if (!skipCalc) {
                complResult = ComplexityOperations.calculateWithDataDependencies(
                        compl1, compl2, seg1ExprSeq, seg2ExprSeq, seg2EmptyWhileRepeat, segResult, opType
                );
                if (complResult == null) {
                    //can't calculate complexity. Rule is invalid.
                    return null;
                }
                
                complResultType.setFunc(YacasExecutor.simplify(complResult.toString()));
                
                segResult.setComplexity(complResultType);
            } else {
                complResult = Complexity.createConstant();
                complResultType.setFunc(complResult.toString());
                segResult.setComplexity(complResultType);
            }
        }
        
        newSegment = segResult;
        
        if (ruleOutput != null) {
            XmlMarshaller.marshallSegment(segResult, ruleOutput);
        }
        
        return segResult;
        
    }
    
    private String getFileName(TemplateType template) {
        return template.getFileName();
    }
    
    public void setSeg1(Segment seg1) {
        this.seg1 = seg1;
    }
    
    public void setSeg2(Segment seg2) {
        this.seg2 = seg2;
    }
    
}
