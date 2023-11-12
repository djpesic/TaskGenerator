/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager;

/**
 *
 * @author djordje
 */
public abstract class Stage {

    protected final Population population;
    protected int maxLoopNumber;

    public Stage(Population population, int maxLoopNumber) {
        this.population = population;
        this.maxLoopNumber = maxLoopNumber;
    }

    public abstract Population run();

    public Population getPopulation() {
        return population;
    }

    public int getMaxLoopNumber() {
        return maxLoopNumber;
    }

}
