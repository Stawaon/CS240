import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleHashProgram {
    public static void main(String[] args) {
        
        // Create a HashMap to store key-value pairs
        Map<Integer, String> hash = new HashMap<Integer, String>();
        
        // Add key-value pairs to HashMap
        hash.put(1, "dog");
        hash.put(2, "cat");
        hash.put(3, "bird");
        hash.put(4, "fish");
        hash.put(5, "rabbit");

        // Print the HashMap
        System.out.println("HashMap: " + hash);

        // User input for searching the HashMap
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the key to search: ");
        System.out.println();
        int key = in.nextInt();
        System.out.println("The animal corresponder to key " + key + " is: " + hash.get(key));

        // Remove a key-value pair from HashMap
        System.out.print("Enter the key to remove: ");
        System.out.println();
        int removeKey = in.nextInt();
        hash.remove(removeKey);
        System.out.println("HashMap after removing key " + removeKey + ": " + hash);

    }
}