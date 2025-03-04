import java.util.Scanner;

public class SimpleHeapProgram {
    public static void main(String[] args) {
        Heap heap = new Heap();

        // Insert elements into the heap
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.add(20);
        heap.add(25);

        // Display the heap
        System.out.println("Heap: ");
        for (int value : heap.getHeap()) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Display smallest element in the heap
        System.out.println("Smallest element in the heap: " + heap.peek());
        
        // Enter numbers to insert into the heap
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of elements to insert: ");
        int n = in.nextInt();
        System.out.println("Enter the elements to insert: ");
        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            heap.add(value);
        }

        // Display heap's contents and remove elements in min-heap order
        System.out.println("Heap: ");
        for (int value : heap.getHeap()) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        System.out.println("Elements removed from the heap: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.remove() + " ");
        }
        System.out.println();
               
    }
}
