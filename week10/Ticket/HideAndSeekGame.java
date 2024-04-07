package week10.Ticket;

import java.util.*;

public class HideAndSeekGame {
    private static int minSeekersNeeded(List<List<Integer>> graph) {
        int[] inDegree = new int[graph.size()];
        // Calculate in-degree of each node
        for (List<Integer> edges : graph) {
            for (int edge : edges) {
                inDegree[edge]++;
            }
        }
        // Count nodes with in-degree 0 as they need a new seeker to start from
        int seekersNeeded = 0;
        for (int degree : inDegree) {
            if (degree == 0) {
                seekersNeeded++;
            }
        }
        return seekersNeeded;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                int destination = scanner.nextInt();
                graph.get(i).add(destination);
            }
        }
        scanner.close();
        
        System.out.println(minSeekersNeeded(graph));
    }
}
