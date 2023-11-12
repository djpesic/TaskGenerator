/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import org.javatuples.Pair;
import programSegment.Segment;
import utils.Randomizator;

public class NoDataDepsStrategy extends Strategy {

    public NoDataDepsStrategy(Stage stage) {
        super(stage);
    }

    @Override
    public void run() {
        Population currentPopulation;
        Population newPopulation;
        Population twoLoopsPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());
        Population finalPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());

        //create population of segments with two loops
        Selector selector = new SuccessiveSelector(stage.getPopulation().getAll());
        while (selector.hasNext()) {
            Pair<Segment, Segment> selected = selector.select();
            Segment result = GeneticAlgorithm.combine(
                    selected.getValue0(), selected.getValue1(), null, false);
//                GeneticAlgorithm.logResult(s1, s2, result);
            if (result == null) {
                continue;
            }
            twoLoopsPopulation.add(result);
        }
        if (stage.getMaxLoopNumber() == 2) {
            resultPopulation = twoLoopsPopulation;
        }
        currentPopulation = twoLoopsPopulation;

        //select each segment from init population and apply to current population
        for (int i = 0; i < stage.getMaxLoopNumber() - 2; i++) {
            int index = Randomizator.getRandomInt(0, stage.getPopulation().realSize() - 1);
            Segment seg1 = stage.getPopulation().get(index);
            newPopulation = new Population(stage.getPopulation().getDesiredSize(), stage.getPopulation().getMode());
            for (int j = 0; j < currentPopulation.realSize(); j++) {
                Segment seg2 = currentPopulation.get(j);
                Segment result = GeneticAlgorithm.combine(seg2, seg1, null, false);
                if (result != null) {
                    newPopulation.add(result);
                }
            }
            finalPopulation.addAll(newPopulation);
            currentPopulation = newPopulation;

        }

        resultPopulation = finalPopulation;
    }

}
