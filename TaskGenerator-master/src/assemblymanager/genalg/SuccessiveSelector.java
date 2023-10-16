/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.genalg;

import com.rits.cloning.Cloner;
import java.util.Collections;
import java.util.List;
import org.javatuples.Pair;
import programSegment.Segment;

/**
 *
 * Shuffles list of segments, and then selects two by two, until the list is
 * exhausted
 */
public class SuccessiveSelector extends Selector {

    private int currentIndex = 0;

    public SuccessiveSelector(List<Segment> list) {
        super(list);
//        Collections.shuffle(list);
    }

    @Override
    public Pair<Segment, Segment> select() {
        Segment s1 = list.get(currentIndex);
        Segment s2 = list.get(currentIndex + 1);
        currentIndex++;
        Cloner cloner = new Cloner();
        s1 = cloner.deepClone(s1);
        s2 = cloner.deepClone(s2);
        Pair<Segment, Segment> result = new Pair<>(s1, s2);
        return result;
    }

    @Override
    public boolean hasNext() {
        return (currentIndex < list.size() - 1);
    }

}
