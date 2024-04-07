package week9.christmast;

import java.util.Scanner;

import java.util.Vector;

public class Christmas {


    static void dfs(int v, boolean[] visited, Vector<Vector<Integer>> adj) {
        visited[v] = true;
        for (int u : adj.get(v)) {
            if (!visited[u])
                dfs(u, visited, adj);
        }
    }

    static void process(int[] x, int[] y, int maxN, int n) {
        Vector<Vector<Integer>> adj = new Vector<>(maxN);
        for (int i = 0; i < maxN; ++i)
            adj.add(new Vector<>());

        boolean[] visited = new boolean[maxN];
        for (int i = 1; i <= n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                if (Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]) <= 1000)
                    adj.get(i).add(j);
            }
        }

        dfs(1, visited, adj);

        if (visited[1] != visited[n])
            System.out.println("sad");
        else
            System.out.println("happy");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();  // Number of test cases
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();  // Number of stores
            int[] storeX = new int[n+2];
            int[] storeY = new int[n+2];
            for (int j = 0; j < n+2; j++) {
                storeX[j] = scanner.nextInt();
                storeY[j] = scanner.nextInt();
            }

            process(storeX, storeY, n+2,n);

        }
    }
}

