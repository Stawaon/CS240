public class GraphExample {
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        // Populate graph with 6 vertices
        // and add edges between them
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 5);
        
        // Print the graph
        System.out.println("Graph adjacency list:");
        graph.printGraph();
        
        // Perform BFS and DFS traversals
        graph.bfs(1); // Start BFS from vertex 1
        graph.dfs(1); // Start DFS from vertex 1
        graph.dijkstra(1, 4); // Start Dijkstra's algorithm from vertex 0 to vertex 5
    }
}
