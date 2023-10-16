/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import assemblymanager.InitStage;
import assemblymanager.Algorithm;
import gui.treedata.TGException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rules.ComplType;
import programSegment.InPortType;
import programSegment.OutPortType;
import programSegment.Segment;
import rules.ConnectionType;
import rules.OperationType;
import rules.OperationType.Selection;
import rules.Rule;
import rules.RuleExecutor;
import rules.TemplateType;
import rules.TemplateType.VarMap;
import utils.Randomizator;
import utils.SegmentUtils;
import utils.XmlSegmentPrinter;
import xmlprocessors.XmlSegmentTypeDeterminator;

/**
 *
 * @author djordje Faza i: Uzeti postojeci segment iz populacije i ukrstiti ga
 * sa jednom jednostrukom petljom. ovaj korak se ponavlja dok ne dodjemo do
 * ogranicenja u broju petlji u segmentu. finalna faza: populacija se menja
 * mutacionim algoritmom.
 */
public class GeneticAlgorithm extends Algorithm {

    private Population.ShrinkMode mode;
    Population population;
    List<Segment> emptyLoops;
    List<Segment> currentLevel = new ArrayList<>(), nextLevel = new ArrayList<>();

    public GeneticAlgorithm(int maxLoopNumber, int populationSize, Population.ShrinkMode shrinkMode) {
        super(maxLoopNumber, populationSize);
        mode = shrinkMode;
    }

    @Override
    public Population start() {
        try {
            Callable<Population> c = () -> {
                population = new Population(populationSize, mode);

                Stage stage = new InitStage(population, maxLoopNumber);
                population = stage.run();
                stage = new ExpansionStage(population, maxLoopNumber);
//                population = stage.run();

                return population;
            };
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Population> f = executor.submit(c);

            Population p = f.get();
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
            return p;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void logResult(Segment s1, Segment s2, Segment result) {
        System.out.println("\n------------s1-----compl " + s1.getComplexity().getFunc());
        System.out.println(XmlSegmentPrinter.generateOutput(s1));

        System.out.println("------------s2-------compl " + s2.getComplexity().getFunc());
        System.out.println(XmlSegmentPrinter.generateOutput(s2));
        if (result != null) {
            System.out.println("------------result------compl " + result.getComplexity().getFunc());
            System.out.println(XmlSegmentPrinter.generateOutput(result));
        }
    }

    public static Segment combine(Segment s1, Segment s2, List<ConnectionType> conns, boolean skipCalc) {
        if (XmlSegmentTypeDeterminator.isExpressionSequence(s1.getProgram())
                && XmlSegmentTypeDeterminator.isExpressionSequence(s2.getProgram())) {
            return null;
        }
        if (XmlSegmentTypeDeterminator.isExpressionSequence(s1.getProgram())
                && XmlSegmentTypeDeterminator.isEmptyWhileRepeatLoop(s2.getProgram())) {
            OperationType opType = new OperationType();
            opType.setNesting(true);
            return combine(s1, s2, opType, true, conns, null, null, skipCalc);
        } else if (XmlSegmentTypeDeterminator.isSimpleLoop(s1.getProgram())
                && XmlSegmentTypeDeterminator.isSimpleLoop(s2.getProgram())) {
            return combine(s1, s2, null, null, conns, null, null, skipCalc);
        } else if (XmlSegmentTypeDeterminator.isComplexLoop(s1.getProgram())
                && XmlSegmentTypeDeterminator.isSimpleLoop(s2.getProgram())) {
            return combine(s1, s2, null, false, conns, null, null, skipCalc);
        } else if (XmlSegmentTypeDeterminator.isComplexLoop(s1.getProgram())
                && XmlSegmentTypeDeterminator.isComplexLoop(s2.getProgram())) {
            return combine(s1, s2, null, false, conns, null, null, skipCalc);
        }
        return null;
    }

    public static Selection createSelection() {
        Selection sel = new Selection();
        TemplateType selTemplate = new TemplateType();
        selTemplate.setFileName("src/xml/emptyIf.xml");
        ComplType compl = new ComplType();
        compl.setFunc("1");
        selTemplate.setInComplexity(compl);
        sel.setSelTemplate(selTemplate);
        sel.setUseElseBranch(Randomizator.getRandomBoolean());

        return sel;
    }

    // if operatin is null, it is chosen randomly. 
    // if connectPorts is null, its value is chosen randomly
    public static Segment combine(Segment s1, Segment s2, OperationType operation, Boolean connectPorts,
            List<ConnectionType> conns, List<VarMap> varMap1, List<VarMap> varMap2, boolean skipCalc) {
        if (connectPorts == null) {
            connectPorts = Randomizator.getRandomBoolean();
        }
        if (operation == null) {
            operation = new OperationType();
            int opNum = Randomizator.getRandomInt(0, 2);
            switch (opNum) {
                case 0:
                    operation.setNesting(true);
                    break;
                case 1:
                    operation.setSequence(true);
                    break;
                default:
                    Selection sel = createSelection();
                    operation.setSelection(sel);
                    break;
            }

        }
        Rule rule = new Rule();
        rule.setOperation(operation);

        TemplateType t1 = new TemplateType();
        rules.ComplType c1 = new rules.ComplType();
        c1.setFunc(s1.getComplexity().getFunc());
        t1.setInComplexity(c1);
        if (varMap1 != null) {
            t1.getVarMap().addAll(varMap1);
        }

        TemplateType t2 = new TemplateType();
        rules.ComplType c2 = new rules.ComplType();
        c2.setFunc(s2.getComplexity().getFunc());
        t2.setInComplexity(c1);
        if (varMap2 != null) {
            t2.getVarMap().addAll(varMap2);
        }

        if (connectPorts && conns != null) {
            operation.getConnection().addAll(conns);
        } else if (connectPorts && conns == null) {
            if (XmlSegmentTypeDeterminator.isExpressionSequence(s1.getProgram())
                    && XmlSegmentTypeDeterminator.isEmptyWhileRepeatLoop(s2.getProgram())) {
                String s1Var = s1.getProgram().getConst().getExpr().getVarName();
                int bound = Randomizator.getRandomInt(0, 1);
                String s2LoopBound;
                InPortType inp = SegmentUtils.getInPortType(s2);

                if (bound == 0) {
                    s2LoopBound = inp.getLower().getName();
                } else {
                    s2LoopBound = inp.getUpper().getName();
                }
                ConnectionType conn = new ConnectionType();
                conn.setInPort(s1Var);
                conn.setOutPort(s2LoopBound);
                operation.getConnection().add(conn);

            } else if (XmlSegmentTypeDeterminator.isSimpleLoop(s1.getProgram())
                    && XmlSegmentTypeDeterminator.isSimpleLoop(s2.getProgram())) {
                if (operation.isSequence()) {
                    OutPortType outp = SegmentUtils.getOutPortType(s1);
                    int index = Randomizator.getRandomInt(0, outp.getPort().size() - 1);
                    String outName = outp.getPort().get(index).getName();

                    InPortType inp = SegmentUtils.getInPortType(s2);
                    String inName = inp.getUpper().getName();
                    ConnectionType conn = new ConnectionType();
                    conn.setOutPort(outName);
                    conn.setInPort(inName);
                    operation.getConnection().add(conn);

                } else if (operation.isNesting()) {
                    InPortType inp = SegmentUtils.getInPortType(s1);
                    OutPortType outp = SegmentUtils.getOutPortType(s2);
                    int number = Randomizator.getRandomInt(1, 2);
                    String inName1 = "", inName2 = "";
                    String outName1 = "", outName2 = "";
                    if (number == 1) {
                        boolean upper = Randomizator.getRandomBoolean();
                        if (upper) {
                            inName1 = inp.getUpper().getName();
                        } else {
                            inName1 = inp.getLower().getName();
                        }

                        int index = Randomizator.getRandomInt(0, outp.getPort().size() - 1);
                        outName1 = outp.getPort().get(index).getName();
                        ConnectionType conn = new ConnectionType();
                        conn.setInPort(inName1);
                        conn.setOutPort(outName1);
                        operation.getConnection().add(conn);
                    } else if (number == 2) {
                        inName1 = inp.getUpper().getName();
                        inName2 = inp.getLower().getName();
                        int index1 = Randomizator.getRandomInt(0, outp.getPort().size() - 1);
                        int index2 = Randomizator.getRandomInt(0, outp.getPort().size() - 1);
                        if (index1 == index2) {
                            index2 = (index1 + 1) % outp.getPort().size();
                        }
                        outName1 = outp.getPort().get(index1).getName();
                        outName2 = outp.getPort().get(index2).getName();
                        ConnectionType conn = new ConnectionType();
                        conn.setInPort(inName1);
                        conn.setOutPort(outName1);
                        operation.getConnection().add(conn);

                        conn = new ConnectionType();
                        conn.setInPort(inName2);
                        conn.setOutPort(outName2);
                        operation.getConnection().add(conn);
                    }

                }
            }
        }

        rule.getTemplate().add(t1);
        rule.getTemplate().add(t2);

        RuleExecutor ruleExec = new RuleExecutor(rule, skipCalc);
        ruleExec.setSeg1(s1);
        ruleExec.setSeg2(s2);

        try {
            ruleExec.executeRule();
            return ruleExec.getNewSegment();
        } catch (TGException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
