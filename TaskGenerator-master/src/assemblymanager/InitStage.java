/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager;

import assemblymanager.genalg.GeneticAlgorithm;
import static assemblymanager.genalg.GeneticAlgorithm.combine;
import com.rits.cloning.Cloner;
import complexity.ComplexityOperations;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import programSegment.Segment;
import rules.ConnectionType;
import rules.OperationType;
import rules.TemplateType.VarMap;
import utils.Permutations;
import utils.Randomizator;
import utils.XmlMarshaller;
import xmlprocessors.SegmentRenamer;
import xmlprocessors.XmlSegmentTypeDeterminator;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * faza 0: Potrebno je isforsitai da se pojavi svaka moguca jednostruka petlja,
 * makar jednom. Faza 1: linearna kerefeka (predzadnji slucaj iz
 * karakterizacije. Faza 2: sqrt jednostruka petlja.
 */
public class InitStage extends Stage {

    List<Segment> currentLevel = new ArrayList<>();
    List<Segment> emptyLoops = new ArrayList<>();
    List<String> templateFileNames = new ArrayList<>();

    public InitStage(Population population, int maxLoopNumber) {
        super(population, maxLoopNumber);
    }

    private List<String> loadTemplateNames(String location) {
        List<String> result = new ArrayList<>();
        try {
            result = Files.walk(Paths.get(location), FileVisitOption.FOLLOW_LINKS)
                    .filter((path) -> path.toString().endsWith(".xml"))
                    .map((path) -> path.toString())
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override

    public Population run() {
        Runnable r = phase0();
        Runnable r1 = phase1();
        List<Runnable> rs2 = phase2();
        Runnable r3 = phase3();

        // add empty for loop to population
        Segment seg = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, XmlBasedGenerator.EMPTY_FOR);
        population.add(seg);
        rs2.add(r);
        rs2.add(r1);
        rs2.add(r3);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (Runnable rn : rs2) {
            executor.submit(rn);

        }
        try {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return population;
    }

    private Runnable phase0() {
        List<Segment> statementsA = prepare0();
        Runnable r = () -> {
            run0(statementsA);
        };
        return r;
    }

    private void run0(List<Segment> statementsA) {
        // crates statements with "b" as one and only variable
        Map<Segment, Segment> statAtoStatB = new HashMap<>();
        Cloner cloner = new Cloner();
        for (Segment statA : statementsA) {
            Segment statB = cloner.deepClone(statA);
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            SegmentRenamer renamer = new SegmentRenamer(statB, statA);
            renamer.setRenamingMap(map);
            renamer.startProcessing();
            statAtoStatB.put(statA, statB);
        }
        // Generates all possible O(n) and O(log(n)) with one loop. 
        // Case 1 and case 2 from characterisation document.
        for (Segment sLoop : emptyLoops) {
            for (Segment sA : statementsA) {
                // one statement inside the loop
                ConnectionType conn = new ConnectionType();
                conn.setInPort("a");
                conn.setOutPort("a");
                List<ConnectionType> conns = new ArrayList<>();
                conns.add(conn);
                Segment resultA = GeneticAlgorithm.combine(sA, sLoop, conns, false);
                if (resultA != null) {
                    population.add(resultA);
                }
//                GeneticAlgorithm.logResult(sLoop, sA, resultA);

                Segment sB = statAtoStatB.get(sA);
                conn = new ConnectionType();
                conn.setInPort("b");
                conn.setOutPort("b");
                conns = new ArrayList<>();
                conns.add(conn);
                Segment resultB = GeneticAlgorithm.combine(sB, sLoop, conns, false);
                if (resultB != null) {
                    population.add(resultB);
                }
//                GeneticAlgorithm.logResult(sLoop, sB, resultB);
            }
        }
        int k = 0;
        for (Segment sLoop : emptyLoops) {
            for (Segment sA : statementsA) {
                for (Segment sB : statAtoStatB.values()) {
                    k++;
//                    System.out.println("Iteraton " + k);
//                    two statements inside the loop
                    OperationType opType = new OperationType();
                    opType.setSequence(true);
                    List<ConnectionType> conns = new ArrayList<>();
                    ConnectionType conn = new ConnectionType();
                    conn.setInPort("b");
                    conn.setOutPort("b");
                    conns.add(conn);
                    conn = new ConnectionType();
                    conn.setInPort("a");
                    conn.setOutPort("a");
                    conns.add(conn);
                    Segment sAB = GeneticAlgorithm.combine(sA, sB, opType, true, conns, null, null, false);
                    opType = new OperationType();
                    opType.setSequence(true);
                    Segment sBA = GeneticAlgorithm.combine(sB, sA, opType, true, conns, null, null, false);

                    opType = new OperationType();
                    opType.setNesting(true);
                    Segment resultAB = GeneticAlgorithm.combine(sAB, sLoop, opType, true, conns, null, null, false);
                    opType = new OperationType();
                    opType.setNesting(true);
                    Segment resultBA = GeneticAlgorithm.combine(sBA, sLoop, opType, true, conns, null, null, false);
                    if (resultAB != null) {
                        population.add(resultAB);
                    }
//                    GeneticAlgorithm.logResult(sAB, sLoop, resultAB);
                    if (resultBA != null) {
                        population.add(resultBA);
                    }
//                    GeneticAlgorithm.logResult(sBA, sLoop, resultBA);
                }
            }
        }
    }

    private List<Segment> prepare0() {
        // initialization stage. Based on the initial database, we generate all possible segments
        // with exactly one loop.
        templateFileNames = loadTemplateNames(XmlBasedGenerator.MGR_DB);
        //load segments from files
        for (String s : templateFileNames) {
            Segment seg = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, s);
            currentLevel.add(seg);
        }
        // separate statements and empty loops. Statements contains "a" as one
        // and only variable
        List<Segment> statementsA;
        statementsA = currentLevel.stream().filter((s)
                -> XmlSegmentTypeDeterminator.isExpressionSequence(s.getProgram()))
                .collect(Collectors.toList());
        emptyLoops = currentLevel.stream().filter((s)
                -> XmlSegmentTypeDeterminator.isEmptyWhileRepeatLoop(s.getProgram()))
                .collect(Collectors.toList());
        currentLevel.removeAll(emptyLoops);
        currentLevel.removeAll(statementsA);
        return statementsA;
    }

    private Runnable phase1() {
        prepare1();
        Runnable r = () -> {
            run1();
        };
        return r;
    }

    private void run1() {
        //create init statement sequences
        List<Segment> initsSeq = new ArrayList<>();
        OperationType opType = new OperationType();
        opType.setSequence(true);
        for (int i = 0; i <= 1; i++) {
            Segment init = combine(currentLevel.get(i),
                    currentLevel.get(1 - i), opType, false, null, null, null, false);
            initsSeq.add(init);
        }

        //create body statement sequences. lenght is 4.
        currentLevel.remove(0);
        currentLevel.remove(0);
        Permutations<Segment> p = new Permutations<>();
//        List<Segment> permutations = p.permute(currentLevel);
        List<Segment> permutations = currentLevel;

        List<Segment> bodies = new ArrayList<>();
        for (int i = 0; i < permutations.size(); i += 4) {
            opType = new OperationType();
            opType.setSequence(true);
            List<ConnectionType> conns = new ArrayList<>();
            ConnectionType conn = new ConnectionType();
            conn.setInPort("k");
            conn.setOutPort("k");
            conns.add(conn);
            conn = new ConnectionType();
            conn.setInPort("l");
            conn.setOutPort("l");
            conns.add(conn);
            List<VarMap> varMap1 = new ArrayList<>();
            List<VarMap> varMap2 = new ArrayList<>();
            VarMap vm1 = new VarMap();
            vm1.setCurrent("p");
            int act = Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
            vm1.setActual("" + act);
            varMap1.add(vm1);
            vm1 = new VarMap();
            vm1.setCurrent("p");
            vm1.setActual("" + act);
            varMap2.add(vm1);
            Segment tmp = combine(permutations.get(i), permutations.get(i + 1), opType, true, conns, varMap1, varMap2, false);
            tmp = combine(tmp, permutations.get(i + 2), opType, true, conns, varMap1, varMap2, false);
            tmp = combine(tmp, permutations.get(i + 3), opType, true, conns, varMap1, varMap2, false);
            bodies.add(tmp);
        }
        int i = 0;
        for (Segment loop : emptyLoops) {
            for (Segment init : initsSeq) {
                for (Segment body : bodies) {
                    opType = new OperationType();
                    opType.setNesting(true);
//                    System.out.println("Iteration" + (i++));
                    List<ConnectionType> conns = new ArrayList<>();
                    ConnectionType conn = new ConnectionType();
                    conn.setInPort("a");
                    conn.setOutPort("a");
                    conns.add(conn);
                    conn = new ConnectionType();
                    conn.setInPort("b");
                    conn.setOutPort("b");
                    conns.add(conn);
                    Segment loopBody = combine(body, loop, opType, true, conns, null, null, true);
//                    GeneticAlgorithm.logResult(body, loop, loopBody);

                    conn = new ConnectionType();
                    conn.setInPort("k");
                    conn.setOutPort("k");
                    conns.add(conn);
                    conn = new ConnectionType();
                    conn.setInPort("l");
                    conn.setOutPort("l");
                    conns.add(conn);
                    opType = new OperationType();
                    opType.setSequence(true);
                    Segment initLoopBody = combine(init, loopBody, opType, true, conns, null, null, false);
                    if (initLoopBody != null) {
                        population.add(initLoopBody);
                    }
//                    GeneticAlgorithm.logResult(init, loopBody, initLoopBody);
                }
            }
        }
    }

    private void prepare1() {
        //linearna jednostruka petlja - kerefeka iz karakterizacije
        templateFileNames = loadTemplateNames(XmlBasedGenerator.SPECLIN_INITS);
        for (String s : templateFileNames) {
            Segment seg = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, s);
            currentLevel.add(seg);
        }
        templateFileNames = loadTemplateNames(XmlBasedGenerator.SPECLIN_BODY);
        for (String s : templateFileNames) {
            Segment seg = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, s);
            currentLevel.add(seg);
        }
        //current level contains statements. empyloops are filled in phase0
        //current level[0] and current level[1] are inits.
    }

    private List<Runnable> phase2() {
        // sqrt petlje iz karakterizacije
        List<String> inits = loadTemplateNames(XmlBasedGenerator.SQRT_INITS);
        List<String> bodies = loadTemplateNames(XmlBasedGenerator.SQRT_BODY);
        //loop1
        Segment body1 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, bodies.get(1)); //a=k*k
        Segment body2 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, bodies.get(2)); //k=k+c
        Segment init = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, inits.get(0)); //k=0;
        Runnable r1 = () -> {
            phase2Helper(body1, body2, init);
        };

        //loop2
        Segment body11 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, bodies.get(0)); //a=a+k
        Segment body22 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, bodies.get(2)); //k=k+c
        Segment init1 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, inits.get(0)); //k=0;
        Runnable r = () -> {
            phase2Helper(body11, body22, init1);
        };
        List<Runnable> result = new LinkedList<>();
        result.add(r1);
        result.add(r);
        return result;
    }

    private void phase2Helper(Segment body1, Segment body2, Segment init) {
        for (Segment loop : emptyLoops) {//from phase0
            OperationType op = new OperationType();
            op.setSequence(true);
            List<ConnectionType> conns = new ArrayList<>();
            ConnectionType conn = new ConnectionType();
            conn.setInPort("k");
            conn.setOutPort("k");
            conns.add(conn);
            Segment[] bodies = new Segment[2];
            bodies[0] = combine(body1, body2, op, true, conns, null, null, true);
            bodies[1] = combine(body2, body1, op, true, conns, null, null, true);
            for (int i = 0; i < 2; i++) {
                op = new OperationType();
                op.setNesting(true);
                conn = new ConnectionType();
                conn.setInPort("a");
                conn.setOutPort("a");
                conns.add(conn);
                conn = new ConnectionType();
                conn.setInPort("b");
                conn.setOutPort("b");
                conns.add(conn);
                Segment loopBody = combine(bodies[i], loop, op, true, conns, null, null, true);

                op = new OperationType();
                op.setSequence(true);
                conn = new ConnectionType();
                conn.setInPort("k");
                conn.setOutPort("k");
                conns.add(conn);

                Segment result = combine(init, loopBody, op, true, conns, null, null, false);
//                GeneticAlgorithm.logResult(init, loopBody, result);
                if (result != null) {
                    population.add(result);
                }
            }
        }
    }

    //linearna petlja, a ide na gore, b na dole
    private Runnable phase3() {
        Segment body1 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, XmlBasedGenerator.A_IS_B_ADD_C); //a=b+c
        Segment body2 = XmlMarshaller.unmarshallSegment(
                XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, XmlBasedGenerator.A_IS_B_SUB_C); //a=b-c
        Runnable r = () -> {
            phase3Helper(body1, body2);
        };
        return r;
    }

    private void phase3Helper(Segment body1, Segment body2) {
        for (Segment loop : emptyLoops) {//from phase0
            OperationType op = new OperationType();
            op.setSequence(true);
            List<ConnectionType> conns = new ArrayList<>();
            ConnectionType conn = new ConnectionType();
            conn.setInPort("a");
            conn.setOutPort("a");
            conns.add(conn);
            conn = new ConnectionType();
            conn.setInPort("b");
            conn.setOutPort("b");
            conns.add(conn);

            List<VarMap> varMap1 = new ArrayList<>();
            VarMap vm1 = new VarMap();
            vm1.setCurrent("b");
            int act = Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
            vm1.setActual("a");
            varMap1.add(vm1);
            vm1 = new VarMap();
            vm1.setCurrent("c");
            vm1.setActual("" + act);
            varMap1.add(vm1);

            List<VarMap> varMap2 = new ArrayList<>();
            VarMap vm2 = new VarMap();
            vm2.setCurrent("a");
            act = Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
            vm2.setActual("b");
            varMap2.add(vm2);
            vm2 = new VarMap();
            vm2.setCurrent("c");
            vm2.setActual("" + act);
            varMap2.add(vm2);

            Segment[] bodies = new Segment[2];
            bodies[0] = combine(body1, body2, op, true, conns, varMap1, varMap2, true);
            bodies[1] = combine(body2, body1, op, true, conns, varMap1, varMap2, true);
            for (int i = 0; i < 2; i++) {
                op = new OperationType();
                op.setNesting(true);
                conn = new ConnectionType();
                conn.setInPort("a");
                conn.setOutPort("a");
                conns.add(conn);
                conn = new ConnectionType();
                conn.setInPort("b");
                conn.setOutPort("b");
                conns.add(conn);
                Segment result = combine(bodies[i], loop, op, true, conns, null, null, false);

                if (result != null) {
                    population.add(result);
                }
            }
        }
    }

}
