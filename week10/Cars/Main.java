package week10.Cars;

import java.util.*;

public class Main {

    static int bfs(List<List<Integer>> graph, int[] parents) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int[] flow = new int[n];
        flow[0] = Integer.MAX_VALUE;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph.get(node).get(i) > 0) {
                    parents[i] = node;
                    flow[i] = Math.min(flow[node], graph.get(node).get(i));
                    if (i == n - 1)
                        return flow[i];
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

        return 0;
    }

    static int findMinBoats(int n, int m, List<List<Integer>> roads) {
        // Initialize the adjacency matrix/list
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }

        // Update adjacency matrix/list
        for (List<Integer> road : roads) {
            int i = road.get(0) - 1;
            int j = road.get(1) - 1;
            int c = road.get(2);
            graph.get(i).set(j, graph.get(i).get(j) + c);
            graph.get(j).set(i, graph.get(j).get(i) + c);
        }

        // Edmonds-Karp algorithm
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        int totalFlow = 0;
        int newFlow;

        while ((newFlow = bfs(graph, parents)) != 0) {
            totalFlow += newFlow;

            int node = n - 1;
            while (node != 0) {
                int prevNode = parents[node];
                graph.get(prevNode).set(node, graph.get(prevNode).get(node) - newFlow);
                graph.get(node).set(prevNode, graph.get(node).get(prevNode) + newFlow);
                node = prevNode;
            }
        }

        return totalFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<List<Integer>> roads = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            roads.add(new ArrayList<>(Arrays.asList(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())));
        }

        int result = findMinBoats(n, m, roads);
        float a = (result*24)%1000;

        int b = (result*24)/1000;

        if( a > 0) {
            System.out.println(b+1);
        } else {
            System.out.println(b);
        }

    }
}
