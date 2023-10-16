package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;
import programSegment.Segment;
import rules.ConnectionType;
import rules.OperationType;
import rules.TemplateType.VarMap;
import utils.Randomizator;
import utils.XmlMarshaller;
import xmlprocessors.generators.XmlBasedGenerator;
//O(g(n)) nest in O(f(n)) -> O(f(n))

public class SequenceSumsStrategy extends Strategy {

    private List<Segment> currentLevel = new ArrayList<>();
    private Cloner cloner = new Cloner();

    Segment sIs0, sIs1, yIsS, sBody1, sBody2, sBody3, sBody4;
    String z, o, s, i;

    public SequenceSumsStrategy(Stage stage) {
        super(stage);
    }

    @Override
    public void run() {

        currentLevel = stage.getPopulation().getAll();
        resultPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());

        sIs0 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B);
        sIs1 = cloner.deepClone(sIs0);
        yIsS = cloner.deepClone(sIs0);
        z = "" + Randomizator.getRandomInt(
                XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
        o = "" + Randomizator.getRandomInt(
                XmlBasedGenerator.NUMBER_STUB_BOTTOM + 1, XmlBasedGenerator.NUMBER_STUB_TOP);
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Randomizator.banNames(names);
        s = Randomizator.getVarName();
        i = Randomizator.getVarName();

        sBody1 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B_ADD_C_MUL_D);
        sBody2 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B_MUL_C_MUL_D);
        sBody3 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B_ADD_C);
        sBody4 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B_MUL_C);

        Selector selector = new SuccessiveSelector(currentLevel);

        while (selector.hasNext()) {
            Pair<Segment, Segment> selected = selector.select();
            Segment seg0 = cloner.deepClone(selected.getValue0());
            Segment seg1 = cloner.deepClone(selected.getValue1());
            generate(selected.getValue0(), selected.getValue1());
            generate(seg1, seg0);
        }

    }

    private void generate(Segment first, Segment second) {
        Segment second1 = assembleSecond(second);
        List<Segment> firsts = new ArrayList<>();
        firsts.add(assembleFirst0(first));
        firsts.add(assembleFirst1(first));
        firsts.add(assembleFirst2(first));
        firsts.add(assembleFirst3(first));

        for (Segment seg : firsts) {
            List<ConnectionType> conns = new ArrayList<>();
            ConnectionType conn = new ConnectionType();
            conn.setInPort(s);
            conn.setOutPort(s);
            conns.add(conn);
            OperationType operation = new OperationType();
            operation.setSequence(true);

            Segment result1 = GeneticAlgorithm.combine(seg, second1, operation, true, conns, null, null, false);
//            GeneticAlgorithm.logResult(seg, second1, result1);
            if (result1 != null) {
                resultPopulation.add(result1);
            }
        }

    }

    private Segment assembleFirst0(Segment seg) {
        List<VarMap> varMap1 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("c");
        vm.setActual(o);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("d");
        vm.setActual(i);
        varMap1.add(vm);

        OperationType opType = new OperationType();
        opType.setNesting(true);
        Segment result1 = GeneticAlgorithm.combine(sBody1, seg, opType, false, null, varMap1, null, false);
//        GeneticAlgorithm.logResult(sBody1, seg, result1);

        varMap1.clear();
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual("0");
        varMap1.add(vm);
        opType = new OperationType();
        opType.setSequence(true);
        List<ConnectionType> conns = new ArrayList<>();
        ConnectionType conn = new ConnectionType();
        conn.setInPort(s);
        conn.setOutPort(s);
        conns.add(conn);
        Segment result2 = GeneticAlgorithm.combine(sIs0, result1, opType, true, conns, varMap1, null, false);
//        GeneticAlgorithm.logResult(sIs0, result1, result2);
        return result2;
    }

    private Segment assembleFirst1(Segment seg) {
        List<VarMap> varMap1 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("c");
        vm.setActual(o);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("d");
        vm.setActual(i);
        varMap1.add(vm);

        OperationType opType = new OperationType();
        opType.setNesting(true);
        Segment result1 = GeneticAlgorithm.combine(sBody2, seg, opType, false, null, varMap1, null, false);
//        GeneticAlgorithm.logResult(sBody2, seg, result1);

        varMap1.clear();
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual("1");
        varMap1.add(vm);
        opType = new OperationType();
        opType.setSequence(true);
        List<ConnectionType> conns = new ArrayList<>();
        ConnectionType conn = new ConnectionType();
        conn.setInPort(s);
        conn.setOutPort(s);
        conns.add(conn);
        Segment result2 = GeneticAlgorithm.combine(sIs1, result1, opType, true, conns, varMap1, null, false);
//        GeneticAlgorithm.logResult(sIs1, result1, result2);
        return result2;
    }

    private Segment assembleFirst2(Segment seg) {
        List<VarMap> varMap1 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("c");
        vm.setActual(z);
        varMap1.add(vm);

        OperationType opType = new OperationType();
        opType.setNesting(true);
        Segment result1 = GeneticAlgorithm.combine(sBody3, seg, opType, false, null, varMap1, null, false);
//        GeneticAlgorithm.logResult(sBody3, seg, result1);

        varMap1.clear();
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual("0");
        varMap1.add(vm);
        opType = new OperationType();
        opType.setSequence(true);
        List<ConnectionType> conns = new ArrayList<>();
        ConnectionType conn = new ConnectionType();
        conn.setInPort(s);
        conn.setOutPort(s);
        conns.add(conn);
        Segment result2 = GeneticAlgorithm.combine(sIs0, result1, opType, true, conns, varMap1, null, false);
//        GeneticAlgorithm.logResult(sIs0, result1, result2);
        return result2;
    }

    private Segment assembleFirst3(Segment seg) {
        List<VarMap> varMap1 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("c");
        vm.setActual(o);
        varMap1.add(vm);

        OperationType opType = new OperationType();
        opType.setNesting(true);
        Segment result1 = GeneticAlgorithm.combine(sBody4, seg, opType, false, null, varMap1, null, false);
//        GeneticAlgorithm.logResult(sBody4, seg, result1);

        varMap1.clear();
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual(s);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual("1");
        varMap1.add(vm);
        opType = new OperationType();
        opType.setSequence(true);
        List<ConnectionType> conns = new ArrayList<>();
        ConnectionType conn = new ConnectionType();
        conn.setInPort(s);
        conn.setOutPort(s);
        conns.add(conn);
        Segment result2 = GeneticAlgorithm.combine(sIs1, result1, opType, true, conns, varMap1, null, false);
//        GeneticAlgorithm.logResult(sIs1, result1, result2);
        return result2;
    }

    private Segment assembleSecond(Segment seg) {
        List<VarMap> varMap1 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual("d");
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(s);
        varMap1.add(vm);

        List<VarMap> varMap2 = new ArrayList<>();
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual("c");
        varMap2.add(vm);
        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual("d");
        varMap2.add(vm);

        OperationType opType = new OperationType();
        opType.setSequence(true);
        Segment result = GeneticAlgorithm.combine(yIsS, seg, opType, false, null, varMap1, varMap2, true);
//        GeneticAlgorithm.logResult(yIsS, seg, result);
        return result;
    }

}
