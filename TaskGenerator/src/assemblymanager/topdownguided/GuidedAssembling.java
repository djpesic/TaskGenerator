/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.topdownguided;

import assemblymanager.Algorithm;
import assemblymanager.InitStage;
import assemblymanager.Population;
import assemblymanager.topdownguided.graph.FuncNode;
import assemblymanager.topdownguided.graph.Graph;
import assemblymanager.topdownguided.graph.OpNode;
import assemblymanager.topdownguided.graph.Node;
import complexity.Complexity;
import complexity.ComplexityFunctionsLexer;
import complexity.ComplexityFunctionsParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.javatuples.Triplet;
import programSegment.Segment;
import utils.YacasExecutor;
import visitors.ExpNumToSymComplexityVisitor;

/**
 *
 * @author djordje
 */
public class GuidedAssembling extends Algorithm {

    private List<String> desiredComplexities;
    private List<String> divisorComplexities = new ArrayList<>();
    private Map<String, List<Triplet<String, String, String>>> factoringMap = new HashMap<>();
    private Graph factGraph = new Graph();
    Map<String, Boolean> cache = new HashMap<>();
    Population resultPopulation;

    public GuidedAssembling(int maxLoopNumber, int populationSize, List<String> desiredCompls) {
        super(maxLoopNumber, populationSize);
        this.desiredComplexities = desiredCompls;
        resultPopulation = new Population(populationSize, Population.ShrinkMode.NONE);

        for (int i = 0; i < desiredCompls.size(); i++) {

            String compl = desiredCompls.get(i);
            if (compl.contains("^")) {
                ComplexityFunctionsLexer lexer = new ComplexityFunctionsLexer(
                        new ANTLRInputStream(compl));

                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ComplexityFunctionsParser parser = new ComplexityFunctionsParser(tokens);
                ComplexityFunctionsParser.ComplexityContext complexityCtx = parser.complexity();
                Complexity cmpl = complexityCtx.compl;
                ExpNumToSymComplexityVisitor visitor = new ExpNumToSymComplexityVisitor();
                cmpl.accept(visitor);
                desiredComplexities.set(i, cmpl.toString());
            }
        }
        this.desiredComplexities = desiredCompls.stream().map(compl -> YacasExecutor.simplify(compl)).collect(Collectors.toList());
        String[] compls = {"n^2", "log(n)^2", "n", "log(n)", "Sqrt(n)", "n*log(n)"};
        for (String compl : compls) {
            divisorComplexities.add(compl);
        }
        List<Triplet<String, String, String>> list = new ArrayList<>();
        list.add(new Triplet<>("Sqrt(n)", "Sqrt(n)", "*"));
        list.add(new Triplet<>("Sqrt(n)", "n", "nestAdd"));
        list.add(new Triplet<>("n", "log(n)", "nestAdd"));
        list.add(new Triplet<>("log(n)", "n", "nestMul"));
        list.add(new Triplet<>("Sqrt(n)", "log(n)", "nestAdd"));
        list.add(new Triplet<>("log(n)", "n", "seq1"));
        list.add(new Triplet<>("Sqrt(n)", "n", "seq1"));
        list.add(new Triplet<>("n", "n", "seq3"));
        list.add(new Triplet<>("log(n)", "n", "seq3"));
        list.add(new Triplet<>("Sqrt(n)", "n", "seq3"));
        list.add(new Triplet<>("log(n)", "n", "seq3"));
        factoringMap.put("n", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("n", "n", "nestAdd"));
        list.add(new Triplet<>("n", "log(n)", "nestMul"));
        list.add(new Triplet<>("n", "n", "seq1"));
        factoringMap.put("n^2", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("log(n)", "n", "nestAdd"));
        list.add(new Triplet<>("log(n)", "log(n)", "nestMul"));
        list.add(new Triplet<>("n", "log(n)", "seq1"));
        factoringMap.put("log(n)^2", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("log(n)", "Sqrt(n)", "nestMul"));
        list.add(new Triplet<>("log(n)", "Sqrt(n)", "seq1"));
        list.add(new Triplet<>("Sqrt(n)", "Sqrt(n)", "seq1"));
        list.add(new Triplet<>("log(n)", "Sqrt(n)", "seq2"));
        list.add(new Triplet<>("n", "Sqrt(n)", "seq3"));
        list.add(new Triplet<>("log(n)", "Sqrt(n)", "seq3"));
        list.add(new Triplet<>("Sqrt(n)", "Sqrt(n)", "seq3"));
        list.add(new Triplet<>("n", "Sqrt(n)", "seq4"));
        list.add(new Triplet<>("log(n)", "Sqrt(n)", "seq4"));
        factoringMap.put("Sqrt(n)", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("log(n)", "log(n)", "seq1"));
        list.add(new Triplet<>("Sqrt(n)", "log(n)", "seq1"));
        list.add(new Triplet<>("Sqrt(n)", "Sqrt(n)", "seq1"));
        list.add(new Triplet<>("n", "log(n)", "seq3"));
        list.add(new Triplet<>("log(n)", "log(n)", "seq3"));
        list.add(new Triplet<>("Sqrt(n)", "log(n)", "seq3"));
        list.add(new Triplet<>("log(n)", "log(n)", "seq4"));
        factoringMap.put("log(n)", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("log(n)", "n", "seq2"));
        factoringMap.put("n*log(n)", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("n", "n", "seq2"));
        factoringMap.put("n!", list);

        list = new ArrayList<>();
        list.add(new Triplet<>("n", "n", "nestMul"));
        list.add(new Triplet<>("n", "n", "seq4"));
        factoringMap.put("o^n", list);
    }

    private List<String> factorizeByMult(String compl) {
        if (!compl.contains("*")) {
            return null;
        }
        List<String> result = new ArrayList<>();
        String[] tokens = compl.split("\\*");
        for (String str : tokens) {
            result.add(str);
        }
        return result;
    }

    private List<String> factorizeByDiv(String compl, String divisor) {
        if (compl.equals(divisor)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        String quotient = YacasExecutor.div(compl, divisor);
        if (quotient.contains("/") || quotient.equals("1")) {
            return null;
        }
        result.add(quotient);
        result.add(divisor);
        return result;
    }

    private void addNodes(FuncNode parent, List<String> compls, List<Node> nodeQueue, String oper) {
        OpNode mulNode = new OpNode(oper);
        factGraph.addNode(mulNode);
        factGraph.addVertice(parent, mulNode);
        for (String s : compls) {
            FuncNode fn = new FuncNode(s);
            fn.setFuncGen();
            Node added = factGraph.addNode(fn);
            factGraph.addVertice(mulNode, added);
            if (!cache.containsKey(fn.getComplexity())) {
                nodeQueue.add(fn);
                cache.put(fn.getComplexity(), Boolean.TRUE);
            }
        }

    }

    private void createGraph() {
        //add root nodes
        List<Node> queue = new LinkedList<>();
        for (String compl : desiredComplexities) {
            FuncNode node = new FuncNode(compl);
            node.setFuncGen();
            node.setRoot(true);
            node.setFuncGen();
            factGraph.addNode(node);
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            Node node = queue.get(0);
            queue.remove(0);
            FuncNode toFactor = (FuncNode) node;
            List<String> factorsMul = factorizeByMult(toFactor.getComplexity());
            List<String> toAdd = new ArrayList<>();
            if (factorsMul != null) {
                addNodes(toFactor, factorsMul, queue, "*");
                for (String s : factorsMul) {
                    if (divisorComplexities.remove(s)) {
                        toAdd.add(s);
                    }
                }
            }
            List<Triplet<String, String, String>> factorsMapList = factoringMap.get(toFactor.getComplexity());
            if (factorsMapList != null) {
                for (Triplet<String, String, String> factorsMap : factorsMapList) {
                    OpNode opNode = new OpNode(factorsMap.getValue2());
                    factGraph.addNode(opNode);
                    factGraph.addVertice(toFactor, opNode);
                    FuncNode fn = new FuncNode(factorsMap.getValue0());
                    fn.setFuncGen();
                    Node added = factGraph.addNode(fn);
                    factGraph.addVertice(opNode, added);
                    if (!cache.containsKey(fn.getComplexity())) {
                        queue.add(fn);

                        cache.put(fn.getComplexity(), true);
                    }

                    fn = new FuncNode(factorsMap.getValue1());
                    fn.setFuncGen();
                    added = factGraph.addNode(fn);

                    factGraph.addVertice(opNode, added);
                    if (!cache.containsKey(fn.getComplexity())) {
                        queue.add(fn);

                        cache.put(fn.getComplexity(), true);
                    }
                }
            }
            List<String> factorsDiv;
            for (String divisor : divisorComplexities) {

                factorsDiv = factorizeByDiv(toFactor.getComplexity(), divisor);
                if (factorsDiv != null) {
                    addNodes(toFactor, factorsDiv, queue, "*");
                }

            }
            divisorComplexities.addAll(toAdd);
        }
    }

    private void generateSegments() {
        Graph factTree = factGraph.extractFactorizationTree();
        System.out.println(factTree);
        
        InitStage initStage = new InitStage(resultPopulation, maxLoopNumber);
        try {
//            resultPopulation = initStage.run();
        } catch (Exception ex) {
            
        }
        List<Segment> linearSegs = resultPopulation.getAll().stream().
                filter((Segment t) -> t.getComplexity().getFunc().equals("n")).collect(Collectors.toList());
        List<Segment> sqrtSegs = resultPopulation.getAll().stream().
                filter((Segment t) -> t.getComplexity().getFunc().equals("Sqrt(n)")).collect(Collectors.toList());
        List<Segment> logSegs = resultPopulation.getAll().stream().
                filter((Segment t) -> t.getComplexity().getFunc().equals("log(n)")).collect(Collectors.toList());
        factTree.generateSegments(linearSegs, logSegs, sqrtSegs);
    }

    @Override
    public Population start() {
        createGraph();
       
        generateSegments();
        return null;
    }
}
