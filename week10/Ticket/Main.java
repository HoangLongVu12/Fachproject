package week10.Ticket;

import java.util.*;

import java.util.*;

public class Main {

    private static final int UNMATCHED = -1;

    // Use ArrayList to represent the adjacency list of the graph
    private ArrayList<Integer>[] graph;
    private int[] match; // To keep track of the matched nodes in the bipartite graph
    private boolean[] visited; // To keep track of visited nodes during the search

    public Main(int numberOfCities) {
        // Initialize graph with twice the number of cities to represent in and out nodes
        graph = new ArrayList[2 * numberOfCities];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        match = new int[graph.length];
        visited = new boolean[graph.length];
        Arrays.fill(match, UNMATCHED);
    }

    private boolean findPath(int u) {
        // Depth-first search to find augmenting paths
        if (visited[u]) return false;
        visited[u] = true;

        for (int v : graph[u]) {
            if (match[v] == UNMATCHED || findPath(match[v])) {
                match[v] = u;
                match[u] = v;
                return true;
            }
        }
        return false;
    }

    public int minimumSearchers() {
        // Apply the Hopcroft-Karp algorithm to find the maximum matching
        int result = 0;
        for (int u = 0; u < graph.length / 2; u++) {
            Arrays.fill(visited, false);
            if (findPath(u)) {
                result++;
            }
        }

        // Subtract the size of the maximum matching from the total number of cities
        return (graph.length / 2) - result;
    }

    public void addEdge(int fromCity, int toCity) {
        // Convert cities to in and out nodes and add edge accordingly
        graph[fromCity + graph.length / 2].add(toCity); // from out to in
        graph[toCity].add(fromCity + graph.length / 2); // from in to out (for bipartite matching)
    }

    public static void main(String[] args) {
        // Example usage:
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // number of cities
        Main game = new Main(n);

        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt(); // number of destinations from city i
            for (int j = 0; j < k; j++) {
                int destination = scanner.nextInt();
                game.addEdge(i, destination);
            }
        }

        int minimumSearchers = game.minimumSearchers();
        System.out.println(minimumSearchers);

        scanner.close();
    }
}
