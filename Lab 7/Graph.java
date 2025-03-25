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
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Breadth-First Search (BFS) traversal from a given source
    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Depth-First Search (DFS) traversal from a given source
    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    // Helper method for DFS
    private void dfsRecursive(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    // Dijkstra's algorithm to find the shortest path from a source vertex
    public Map<Integer, Integer> dijkstra(int source, int destination) {
        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination vertex not found in the graph.");
        }
        
        // Priority queue to store vertices based on their distance from the source
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>(); // To reconstruct the path
        Set<Integer> visited = new HashSet<>();

        // Initialize distances to infinity and add source to the queue
        for (int vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.add(new int[]{source, 0}); 

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentVertex = current[0];
            int currentDistance = current[1];

            if (visited.contains(currentVertex)) {
                continue; // Skip if already visited
            }
            visited.add(currentVertex);

            // If we reach the destination, we can stop
            if (currentVertex == destination) {
                break;
            }

            for (int neighbor : adjList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    int newDistance = currentDistance + 1; // Assuming all edges have weight 1
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previous.put(neighbor, currentVertex);
                        pq.add(new int[]{neighbor, newDistance});
                    }
                }
            }
        }

        // If you want to return the path to the destination, you can reconstruct it here
        if (!distances.containsKey(destination) || distances.get(destination) == Integer.MAX_VALUE) {
            System.out.println("No path exists between source and destination");
        } else {
            System.out.println("Shortest path distance : " + distances.get(destination));
            System.out.print("Path: ");
            printPath(previous, destination);
        }
        return distances; // Return distances from source to all vertices
    }

    // Helper method to print the path
    private void printPath(Map<Integer, Integer> previous, int destination) {
        if (!previous.containsKey(destination)) {
            System.out.print(destination + " ");
            return;
        }
        printPath(previous, previous.get(destination));
        System.out.print(destination + " ");

    }
    
}

