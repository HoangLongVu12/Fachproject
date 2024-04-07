package week9.party;
import java.util.*;

import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[] color;
    static boolean possible;

    static void dfs(int node, int c) {
        color[node] = c;
        for (int child : graph[node]) {
            if (color[child] == 0) {
                dfs(child, 3 - c);
            } else if (color[child] == color[node]) {
                possible = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int p = sc.nextInt();
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                graph[u].add(v);
                graph[v].add(u);
            }
            color = new int[n];
            possible = true;
            for (int i = 0; i < n; i++) {
                if (color[i] == 0) {
                    dfs(i, 1);
                }
            }
            if (!possible) {
                System.out.println("impossible");
            } else {
                System.out.println((int) Math.pow(2, n) % p);
            }
        }
        sc.close();
    }
}
