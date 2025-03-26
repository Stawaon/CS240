import java.util.*;

// Class to represent a graph
class Graph {
    private Map<Integer, List<Integer>> adjList; // Adjacency list

    // Constructor
    public Graph() {
        adjList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add an edge between two vertices
    public void addEdge(int source, int destination) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(destination, new ArrayList<>()); 
        adjList.get(source).add(destination);
        adjList.get(destination).add(source); // For undirected graph
    }

    // Print the graph
    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) { // Iterate over all vertices
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) { // Print all neighbors of a vertex
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Breadth-First Search (BFS) traversal from a given source
    // BFS algorithm starts at a specified vertex and explores all of the neighbor vertices at the present depth prior to moving on to the vertices at the next depth level. Uses queue to keep track of vertices to be visited next.
    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) { // loop continues as long as there are vertices in the queue
            int currentVertex = queue.poll(); // remove the vertex at the front of the queue
            System.out.print(currentVertex + " ");

            for (int neighbor : adjList.get(currentVertex)) { // check all neighbors of the current vertex
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor); // add the neighbor to the queue
                    visited.add(neighbor); // mark the neighbor as visited
                }
            }
        }
        System.out.println();
    }

    // Depth-First Search (DFS) traversal from a given source
    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>(); // Set to keep track of visited vertices
        System.out.print("DFS Traversal: ");
        dfsRecursive(startVertex, visited); // Call the recursive helper method
        System.out.println();
    }

    // Helper method for DFS
    private void dfsRecursive(int vertex, Set<Integer> visited) {
        visited.add(vertex); // Mark the current vertex as visited
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) { // Visit all neighbors of the current vertex
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited); // Recursive call to visit the neighbor
            }
        }
    }

    // Dijkstra's algorithm to find the shortest path from a source vertex
    public Map<Integer, Integer> dijkstra(int source, int destination) {
        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) { // Check if source and destination vertices are in the graph
            throw new IllegalArgumentException("Source or destination vertex not found in the graph.");
        }
        
        // Priority queue to store vertices based on their distance from the source
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Assigns priority based on the distance from source
        Map<Integer, Integer> distances = new HashMap<>(); // Tracks the shortest distance from source to each vertex
        Map<Integer, Integer> previous = new HashMap<>(); // Tracks the previous vertex in the shortest path to reconstruct later
        Set<Integer> visited = new HashSet<>();

        // Initialize distances to infinity and add source to the queue
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0); // Distance from source to itself is 0
        pq.add(new int[]{source, 0});  // Add source to the priority queue with distance 0

        while (!pq.isEmpty()) { // while pq is not empty, remove the vertex with the shortest distance from the queue and retrieve its distance
            int[] current = pq.poll();
            int currentVertex = current[0];
            int currentDistance = current[1];

            if (visited.contains(currentVertex)) {
                continue; // Skip if already visited
            }
            visited.add(currentVertex);

            // If we reach the destination, we can stop shortest distance has been found
            if (currentVertex == destination) {
                break;
            }

            for (int neighbor : adjList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    int newDistance = currentDistance + 1; // increase the distance by 1 for each edge
                    if (newDistance < distances.get(neighbor)) { // If the new distance is less than the current distance, update distances and previous
                        distances.put(neighbor, newDistance); 
                        previous.put(neighbor, currentVertex); // Update the previous vertex for the neighbor
                        pq.add(new int[]{neighbor, newDistance}); 
                    }
                }
            }
        }

        // Handle the case where there is no path between source and destination
        if (!distances.containsKey(destination) || distances.get(destination) == Integer.MAX_VALUE) {
            System.out.println("No path exists between source and destination");
        } else { // Print the shortest path and distance
            System.out.println("Shortest path distance : " + distances.get(destination));
            System.out.print("Path: ");
            printPath(previous, destination);
        }
        return distances; // Return distances from source to all vertices
    }

    // Helper method to print the path recursively, prints the vertices in the order they appear in the path
    private void printPath(Map<Integer, Integer> previous, int destination) {
        if (!previous.containsKey(destination)) { // Base case: reached the source vertex
            System.out.print(destination + " ");
            return;
        }
        printPath(previous, previous.get(destination)); 
        System.out.print(destination + " ");

    }
    
}

