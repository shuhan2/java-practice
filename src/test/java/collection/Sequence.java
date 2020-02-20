package collection;

import java.util.Iterator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Sequence implements Iterable<Integer> {
    private final Integer start;
    private final Integer end;

    public Sequence(Integer start, Integer end) {
        if (start >= end) { throw new IllegalArgumentException("Start must be smaller than End."); }
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SequenceIterator(start, end);
    }
}

class SequenceIterator implements Iterator<Integer> {
    private Integer current;
    private final Integer end;

    SequenceIterator(Integer start, Integer end) {
        // <--start
        this.current = start;
        this.end = end;
        // --end-->
    }

    @Override
    public boolean hasNext() {
        // <--start
        return current < end;
        // --end-->
    }

    @Override
    public Integer next() {
        // <--start
        return current++;
        // --end-->
    }
}
