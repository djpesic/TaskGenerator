package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import com.rits.cloning.Cloner;
import complexity.ComplexityOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.javatuples.Pair;
import programSegment.Segment;
import rules.ConnectionType;
import rules.OperationType;
import rules.TemplateType.VarMap;
import utils.Randomizator;
import utils.XmlMarshaller;
import xmlprocessors.XmlSegmentTypeDeterminator;
import xmlprocessors.generators.XmlBasedGenerator;
//O(g(n)) nest in O(f(n)) -> O(f(n))

public class NestingSumsStrategy extends Strategy {

    private List<Segment> currentLevel = new ArrayList<>();
    private Cloner cloner = new Cloner();
    private Segment initA;
    private Segment initB;

    public NestingSumsStrategy(Stage stage) {
        super(stage);
    }

    @Override
    public void run() {

        currentLevel = stage.getPopulation().getAll();
        resultPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());

        List<String> mulAdd1 = new ArrayList<>();
        List<String> mulAdd2 = new ArrayList<>();
        mulAdd1.add(XmlBasedGenerator.A_IS_B_MUL_C);
        mulAdd1.add(XmlBasedGenerator.A_IS_B_ADD_C);
        mulAdd2.add(XmlBasedGenerator.A_IS_B_DIV_C);
        mulAdd2.add(XmlBasedGenerator.A_IS_B_SUB_C);

        Segment sAssign = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B);
        initA = cloner.deepClone(sAssign);
        initB = cloner.deepClone(sAssign);
        for (int i = 0; i < 2; i++) {

            Segment sMul = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                    mulAdd1.get(i));
            Segment sAssign1 = cloner.deepClone(sAssign);
            Segment sAssign2 = cloner.deepClone(sAssign);
            Segment sDiv = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                    mulAdd2.get(i));
            Segment sMul1 = cloner.deepClone(sMul);
            Segment sDiv1 = cloner.deepClone(sDiv);

            String o = "" + Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM + 1, XmlBasedGenerator.NUMBER_STUB_TOP);
            List<VarMap> varMap1 = new ArrayList<>(), varMap2 = new ArrayList<>();
            VarMap vm = new VarMap();
            vm.setCurrent("c");
            vm.setActual(o);
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("c");
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("c");
            varMap1.add(vm);

            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("d");
            varMap2.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("n");
            varMap2.add(vm);

            generate(sMul, sAssign1, varMap1, varMap2);// x=x(*|+)0, y=n

            String c1 = "" + Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
            o = "" + Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM + 1, XmlBasedGenerator.NUMBER_STUB_TOP);
            varMap1.clear();
            varMap2.clear();
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("d");
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("c");
            vm.setActual(o);
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("d");
            varMap1.add(vm);

            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual(c1);
            varMap2.add(vm);
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("c");
            varMap2.add(vm);
            generate(sDiv, sAssign2, varMap1, varMap2);// y=y(/|-)0, x=c1

            o = "" + Randomizator.getRandomInt(
                    XmlBasedGenerator.NUMBER_STUB_BOTTOM + 1, XmlBasedGenerator.NUMBER_STUB_TOP);
            varMap1.clear();
            varMap2.clear();
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("c");
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("c");
            varMap1.add(vm);
            vm = new VarMap();
            vm.setCurrent("c");
            vm.setActual(o);
            varMap1.add(vm);

            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("d");
            varMap2.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("d");
            varMap2.add(vm);
            vm = new VarMap();
            vm.setCurrent("c");
            vm.setActual(o);
            varMap2.add(vm);
            generate(sMul1, sDiv1, varMap1, varMap2);// x=x(*|+)o, y=y(/|-)o
        }
    }

    private void generate(Segment s1, Segment s2, List<VarMap> varMap1, List<VarMap> varMap2) {
        OperationType opType = new OperationType();
        opType.setSequence(true);

        Segment s1s2 = GeneticAlgorithm.combine(s1, s2, opType, false, null, varMap1, varMap2, false);

        Segment s2s1 = GeneticAlgorithm.combine(s2, s1, opType, false, null, null, null, false);//already mapped
        assemble(s1s2);
        assemble(s2s1);
    }
    int cnt = 0;

    private void assemble(Segment seg) {
        OperationType opType;
        Selector selector = new SuccessiveSelector(currentLevel);
        while (selector.hasNext()) {
            Pair<Segment, Segment> selected = selector.select();
            opType = new OperationType();
            opType.setSequence(true);
            List<ConnectionType> conns = new ArrayList<>();
            ConnectionType conn = new ConnectionType();
            conn.setInPort("c");
            conn.setOutPort("c");
            conns.add(conn);
            conn = new ConnectionType();
            conn.setInPort("d");
            conn.setOutPort("d");
            conns.add(conn);
            List<VarMap> varMap = new ArrayList<>();
            VarMap vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("c");
            varMap.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("d");
            varMap.add(vm);
            Segment inner = GeneticAlgorithm.combine(seg, selected.getValue0(),
                    opType, Boolean.TRUE, conns, null, varMap, true);
//            GeneticAlgorithm.logResult(seg, selected.getValue0(), inner);
            inner.setComplexity(selected.getValue0().getComplexity());
            opType = new OperationType();
            opType.setSequence(true);
            varMap = new ArrayList<>();
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("n");
            varMap.add(vm);
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("d");
            varMap.add(vm);
            Segment result1 = GeneticAlgorithm.combine(initA, selected.getValue1(), opType, false, null, varMap, null, false);
//            GeneticAlgorithm.logResult(initA, selected.getValue1(), result1);

            varMap.clear();
            vm = new VarMap();
            vm.setCurrent("a");
            vm.setActual("c");
            varMap.add(vm);
            vm = new VarMap();
            vm.setCurrent("b");
            vm.setActual("1");
            varMap.add(vm);
            Segment result2 = GeneticAlgorithm.combine(initB, result1, opType, false, null, varMap, null, false);
//            GeneticAlgorithm.logResult(initB, result1, result2);

            opType = new OperationType();
            opType.setNesting(true);
            Segment result = GeneticAlgorithm.combine(inner, result2,
                    opType, true, conns, null, null, false);

//            GeneticAlgorithm.logResult(inner, result2, result);
            if (result != null) {
                resultPopulation.add(result);
            }
//            System.out.println("cnt: " + cnt);
            cnt++;
//            System.out.println("---------------------------------------------------");
        }
    }

}
