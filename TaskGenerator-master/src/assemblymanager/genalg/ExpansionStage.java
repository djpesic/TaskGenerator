/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;
import com.rits.cloning.Cloner;
import java.util.concurrent.ExecutorService;

/**
 *
 * expansion stage: each iteration adds one more loop to the loops in current
 * population, until maxloopNumber is reached.
 */
public class ExpansionStage extends Stage {

    public ExpansionStage(Population population, int maxLoopNumber) {
        super(population, maxLoopNumber);
    }

    @Override
    public Population run() {
        try {

            Strategy strategy1 = new NoDataDepsStrategy(this);
            //with data depenendencies.
            Strategy strategy2 = new KeepOuterComplStrategy(this);
            Strategy strategy3 = new NestingSumsStrategy(this);
            Strategy strategy4 = new SequenceSumsStrategy(this);
            strategy1.start();
            strategy2.start();
            strategy3.start();
            strategy4.start();

            strategy1.join();
            strategy2.join();
            strategy3.join();
            strategy4.join();
            Population resultPopulation = strategy1.resultPopulation;
            resultPopulation.addAll(strategy2.resultPopulation);
            resultPopulation.addAll(strategy3.resultPopulation);
            resultPopulation.addAll(strategy4.resultPopulation);
            return resultPopulation;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
