package assemblymanager.genalg;

import assemblymanager.Population;
import assemblymanager.Stage;

public abstract class Strategy extends Thread {

    protected Stage stage;
    protected Population resultPopulation;

    public Strategy(Stage stage) {
        this.stage = stage;
    }

}
