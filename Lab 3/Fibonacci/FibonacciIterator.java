import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {
    private int limit;
    private int current = 0;
    private int next = 1;

    public FibonacciIterator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return current <= limit;
    }

    @Override
    public Integer next() {
        int temp = current;
        current = next;
        next = temp + next;
        return temp;
    }
}