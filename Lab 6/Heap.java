import java.util.PriorityQueue;
import java.util.Queue;


public class Heap {
    private Queue<Integer> heap;

    public Heap() {
        heap = new PriorityQueue<>();
    }

    public void add(int value) {
        heap.add(value);
    }

    public int remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.poll();
    }

    public int peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.peek();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public Queue<Integer> getHeap() {
        return heap;
    }
}
