package week9.kay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int DFS(int u,boolean[] visited,int res,int[] sum,int[][] val,List<List<Integer>> adj) {
        visited[u] = true;
        int s;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                s = val[u][v] + DFS(v,visited,res,sum,val,adj);
            } else {
                s = val[u][v] + sum[v];
            }
            sum[u] = Math.max(sum[u], s);
        }

        return sum[u];
    }

    static void process(boolean[] visited,int n,int res,int[] sum,int[][] val,List<List<Integer>> adj) {
        for (int i = 1; i <= n; ++i) {
            if (!visited[i]) {
                res = Math.max(res, DFS(i,visited,res,sum,val,adj));
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int maxN = 1005;
        int res = 0;
        boolean[] visited = new boolean[maxN];
        int[] sum = new int[maxN];
        int[][] val = new int[maxN][maxN];
        List<List<Integer>> adj = new ArrayList<>(maxN);
        for (int i = 0; i <= n; ++i) {
            adj.add(new ArrayList<>());
        }

        int s, t, c;
        for (int i = 0; i < m; ++i) {
            s = scanner.nextInt();
            t = scanner.nextInt();
            c = scanner.nextInt();
            adj.get(s).add(t);
            val[s][t] = Math.max(val[s][t], c);
        }
        process(visited,n,res,sum,val,adj);
    }
}