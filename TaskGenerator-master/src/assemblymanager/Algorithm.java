/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager;

import java.util.List;

/**
 *
 * @author djordje
 */
public abstract class Algorithm {

    protected int populationSize;
    protected int maxLoopNumber;

    public Algorithm(int maxLoopNumber, int populationSize) {
        this.populationSize = populationSize;
        this.maxLoopNumber = maxLoopNumber;
    }

    /**
     *
     * @param segments List of loaded templates.
     *
     * Method runs assembling algorithm on the segments list. Generated segments
     * are saved to files.
     * @return list of generated segment
     */
    public Population start(List<String> segments) {
        return null;
    }

    public Population start() {
        return null;
    }

}
