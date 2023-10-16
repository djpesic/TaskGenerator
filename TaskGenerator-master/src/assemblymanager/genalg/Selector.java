package assemblymanager.genalg;

import java.util.List;
import org.javatuples.Pair;
import programSegment.Segment;

/**
 *
 * @author djordje
 */
public abstract class Selector {

    protected List<Segment> list;

    public Selector(List<Segment> list) {
        this.list = list;
    }

    public abstract Pair<Segment, Segment> select();

    public boolean hasNext() {
        return true;
    }
}
