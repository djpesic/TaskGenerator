/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import programSegment.InPortType;
import programSegment.InPortType.Port;
import programSegment.OutPortType;
import programSegment.Segment;

/**
 *
 * @author djordje places ports from the first segment into port lists in the
 * second segment
 */
public class PortAdjuster extends XmlProcessorAdapter {

    protected Segment first, second;

    public PortAdjuster(Segment first, Segment second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void startProcessing() {
        try {
            Set<String> names = collectNames(first);
            adaptInheritance(names, second);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void adaptInheritance(Set<String> names, Segment segment) {
        //insert collected ports into  segment and set them inherited, if not present. If present, set them inherited
        JXPathContext context = JXPathContext.newContext(segment);
        context.setLenient(true);
        for (Iterator<Pointer> iter = context.iterate("//inPorts"); iter.hasNext();) {
            InPortType inp = (InPortType) iter.next();
            Set<String> usedNames = new HashSet<>();
            for (Port p : inp.getPort()) {
                if (names.contains(p.getName())) {
                    p.setInherited(true);
                    usedNames.add(p.getName());
                }
            }
            for (String name : names) {
                if (!usedNames.contains(name)) {
                    Port p = new Port();
                    p.setName(name);
                    p.setInherited(true);
                    inp.getPort().add(p);
                }
            }
        }
        for (Iterator<Pointer> iter = context.iterate("//outPorts"); iter.hasNext();) {
            OutPortType outP = (OutPortType) iter.next();
            Set<String> usedNames = new HashSet<>();
            for (Port p : outP.getPort()) {
                if (names.contains(p.getName())) {
                    p.setInherited(true);
                    usedNames.add(p.getName());
                }
            }
            if (outP.getIter() != null) {
                if (names.contains(outP.getIter().getName())) {
                    usedNames.add(outP.getIter().getName());
                }
            }
            for (String name : names) {
                if (!usedNames.contains(name)) {
                    Port p = new Port();
                    p.setName(name);
                    p.setInherited(true);
                    outP.getPort().add(p);
                }
            }
        }
    }

    protected Set<String> collectNames(Segment segment) {
        Set<String> names = new HashSet<>();
        //collect port names from the  segment
        JXPathContext context = JXPathContext.newContext(segment);
        context.setLenient(true);
        for (Iterator<String> iter = context.iterate("//port/@name | //iter/name"); iter.hasNext();) {
            names.add(iter.next());
        }
        return names;
    }

}
