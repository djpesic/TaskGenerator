/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author djordje
 */
public class ProgramPortSet implements Iterable<ProgramPort> {

    private Set<ProgramPort> set = new HashSet<>();

    //if set contains multiple ports with the same name, names with inherited==true is kept
    private void adjust() {
        Stream<ProgramPort> nonInherited = set.stream().filter((ProgramPort port)
                -> !port.isInherited());
        Stream<ProgramPort> inherited = set.stream().filter((ProgramPort port)
                -> port.isInherited());
        Set<ProgramPort> newSet = inherited.collect(Collectors.toSet());
        nonInherited.forEach((ProgramPort port) -> {
            if (newSet.stream().map((ProgramPort port1) -> port1.getName())
                    .noneMatch((String name) -> name.equals(port.getName()))) {
                newSet.add(port);
            }
        });
        set = newSet;
    }

    public List<ProgramPort> toList() {
        return set.stream().collect(Collectors.toList());
    }

    public void addAll(ProgramPortSet toAdd) {
        set.addAll(toAdd.set);
        adjust();
    }

    public void add(ProgramPort outPort) {
        set.add(outPort);
        adjust();
    }

    public void removeAll(ProgramPortSet toRemove) {
        toRemove.forEach((ProgramPort port) -> set.remove(port));
    }

    public void remove(ProgramPort port) {
        for (Iterator<ProgramPort> it = set.iterator(); it.hasNext();) {
            if (it.next().getName().equals(port.getName())) {
                it.remove();
            } else {
                it.next();
            }
        }
    }

    @Override
    public Iterator<ProgramPort> iterator() {
        return set.iterator();
    }

}
