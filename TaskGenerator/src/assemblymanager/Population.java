/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager;

import java.util.*;
import programSegment.Segment;
import utils.Randomizator;

public class Population {

    public enum ShrinkMode {
        // Each time segmet is added, population must not exceed defined desiredSize.
        // If segment must be removed, removal index is chosen randomly.
        REALTIME,
        //Shrinking is done after complete generation process is done. Extra segments
        //are removed from random positions.
        POST_GEN_RND,
        // Classification process must be done before the removal. After classification,
        // segments with the least effect for recognized distribution are removed.
        POST_GEN_DISTRIB,
        NONE //Without shrink
    }
    private List<Segment> population = new ArrayList<>();
    private int desiredSize;
    private ShrinkMode mode;

    public Population(int desiredSize, ShrinkMode mode) {
        this.mode = mode;
        this.desiredSize = desiredSize;
    }

    public synchronized void shrink() {
        switch (mode) {
            case POST_GEN_RND:
                postgenRndShrink();
                break;
            case POST_GEN_DISTRIB:
                postgenDistribShrink();
                break;
            case REALTIME:
            case NONE:
                break;
            default:
                System.out.println("Unsupported population shrink mode");
                break;
        }
    }

    public synchronized void setMode(ShrinkMode mode) {
        this.mode = mode;
    }

    public synchronized void setDesiredSize(int desiredSize) {
        this.desiredSize = desiredSize;
    }

    public synchronized void add(Segment seg) {
        if (mode == ShrinkMode.REALTIME && population.size() >= desiredSize) {
            removeRndSeg();
        }
        population.add(seg);
    }

    public synchronized void addAll(Population otherPopulation) {
        for (int i = 0; i < otherPopulation.realSize(); i++) {
            add(otherPopulation.get(i));
        }
    }

    public synchronized void addAll(List<Segment> segs) {
        for (int i = 0; i < segs.size(); i++) {
            add(segs.get(i));
        }
    }

    public Segment get(int index) {
        return population.get(index);
    }

    public int realSize() {
        return population.size();
    }

    private void removeRndSeg() {
        int index = Randomizator.getRandomInt(0, population.size() - 1);
        population.remove(index);
    }

    private void postgenRndShrink() {
        int diff = population.size() - desiredSize - 1;
        for (int i = 0; i < diff; i++) {
            removeRndSeg();
        }
    }

    private void postgenDistribShrink() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Segment> getAll() {
        return population;
    }

    public int getDesiredSize() {
        return desiredSize;
    }

    public ShrinkMode getMode() {
        return mode;
    }

    public synchronized void clear() {
        population.clear();;
    }
}
