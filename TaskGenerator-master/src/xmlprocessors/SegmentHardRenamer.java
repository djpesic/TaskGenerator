/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import programSegment.Segment;

/**
 *
 * @author djordje
 */
// Renames all variables in whole according to the map, without any other conditions
public class SegmentHardRenamer extends SegmentRenamer {

    public SegmentHardRenamer(Segment segResult) {
        super(segResult, null);
    }

}
