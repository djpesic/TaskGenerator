package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import com.rits.cloning.Cloner;
import complexity.ComplexityOperations;
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

public class KeepOuterComplStrategy extends Strategy {

    List<Segment> currentLevel = new ArrayList<>();
    Cloner cloner = new Cloner();

    public KeepOuterComplStrategy(Stage stage) {
        super(stage);
    }

    @Override
    public void run() {

        currentLevel = stage.getPopulation().getAll();
        resultPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());

        Segment s1 = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA,
                XmlBasedGenerator.A_IS_B);

        Segment s2 = cloner.deepClone(s1);
        Segment s3 = cloner.deepClone(s1);
        Segment s4 = cloner.deepClone(s1);
        String act1 = "" + Randomizator.getRandomInt(
                XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
        String act2 = "" + Randomizator.getRandomInt(
                XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);
        generate(s1, s2, act1, act2);
        act2 = "" + Randomizator.getRandomInt(
                XmlBasedGenerator.NUMBER_STUB_BOTTOM, XmlBasedGenerator.NUMBER_STUB_TOP);

        generate(s3, s4, act2, act2);
    }

    private void generate(Segment s1, Segment s2, String act1, String act2) {
        OperationType opType = new OperationType();
        opType.setSequence(true);
        List<VarMap> varMap1 = new ArrayList<>(), varMap2 = new ArrayList<>();
        VarMap vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(act1);
        varMap1.add(vm);
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual("c");
        varMap1.add(vm);

        vm = new VarMap();
        vm.setCurrent("b");
        vm.setActual(act2);
        varMap2.add(vm);
        vm = new VarMap();
        vm.setCurrent("a");
        vm.setActual("d");
        varMap2.add(vm);
        Segment s1s2 = GeneticAlgorithm.combine(s1, s2, opType, false, null, varMap1, varMap2, false);

        Segment s2s1 = GeneticAlgorithm.combine(s2, s1, opType, false, null, null, null, false);//already mapped
        assemble(s1s2);
        assemble(s2s1);
    }

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
            opType = new OperationType();
            opType.setNesting(true);
            Segment result = GeneticAlgorithm.combine(inner, selected.getValue1(),
                    opType, true, conns, null, null, false);
            if (result != null) {
                resultPopulation.add(result);
            }
        }
    }

}
