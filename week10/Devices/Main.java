package week10.Devices;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

        static int n, m, k, res;
        static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        @SuppressWarnings("unchecked")
        static ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[2005];
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            // Initialize graph and adj
            for (int i = 0; i < 2005; i++) {
                graph.add(new ArrayList<>());
                for (int j = 0; j < 2005; j++) {
                    graph.get(i).add(0);
                }
                adj[i] = new ArrayList<>();
            }

            input();
            process();
        }

    static void input() {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        int x, y;
        for (int i = 0; i < k; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            y += n;
            adj[x].add(y);
            adj[y].add(x);
            graph.get(x).set(y, 1);
            graph.get(0).set(x, 1);
            graph.get(y).set(n + m + 1, 1);
        }
        for (int i = 1; i <= n; i++) adj[0].add(i);
        for (int i = n + 1; i < n + m + 1; i++) adj[i].add(n + m + 1);
    }

    static int bfs(ArrayList<Integer> parents) {
        parents.clear();
        for (int i = 0; i < 2005; i++) parents.add(-1);
        parents.set(0, -2);
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, Integer.MAX_VALUE));

        while (!q.isEmpty()) {
            Pair front = q.poll();
            int node = front.first;
            int flow = front.second;

            for (int i : adj[node]) {
                if (parents.get(i) == -1 && graph.get(node).get(i) > 0) {
                    parents.set(i, node);
                    int newFlow = Math.min(flow, graph.get(node).get(i));
                    if (i == n + m + 1) return newFlow;
                    q.add(new Pair(i, newFlow));
                }
            }
        }
        return 0;
    }

    static int findMaxDevicesInitial() {
        ArrayList<Integer> parents = new ArrayList<>();
        for (int i = 0; i < 2005; i++) parents.add(-1);
        int totalFlow = 0;

        int newFlow;
        while ((newFlow = bfs(parents)) != 0) {
            totalFlow += newFlow;
            int node = n + m + 1;
            while (node != 0) {
                int prevNode = parents.get(node);
                int currentValue = graph.get(prevNode).get(node);
                graph.get(prevNode).set(node, currentValue - newFlow);
                currentValue = graph.get(node).get(prevNode);
                graph.get(node).set(prevNode, currentValue + newFlow);
                node = prevNode;
            }
        }
        return totalFlow;
    }

    static int bfs_new(int s, ArrayList<Integer> parents, ArrayList<ArrayList<Integer>> dis) {
        parents.clear();
        for (int i = 0; i < 2005; i++) parents.add(-1);
        parents.set(0, -2);
        parents.set(s, 0);
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(s, dis.get(0).get(s)));

        while (!q.isEmpty()) {
            Pair front = q.poll();
            int node = front.first;
            int flow = front.second;

            for (int i : adj[node]) {
                if (parents.get(i) == -1 && dis.get(node).get(i) > 0) {
                    parents.set(i, node);
                    int newFlow = Math.min(flow, dis.get(node).get(i));
                    if (i == n + m + 1) return newFlow;
                    q.add(new Pair(i, newFlow));
                }
            }
        }
        return 0;
    }

    static int tryDevice(int s, ArrayList<ArrayList<Integer>> dis) {
        ArrayList<Integer> parents = new ArrayList<>();
        for (int i = 0; i < 2005; i++) parents.add(-1);
        dis.get(0).set(s, dis.get(0).get(s) + 2);
        int totalFlow = 0;

        int newFlow;
        while ((newFlow = bfs_new(s, parents, dis)) != 0) {
            totalFlow += newFlow;
            int node = n + m + 1;
            while (node != 0) {
                int prevNode = parents.get(node);
                int currentValue = dis.get(prevNode).get(node);
                dis.get(prevNode).set(node, currentValue - newFlow);
                currentValue = dis.get(node).get(prevNode);
                dis.get(node).set(prevNode, currentValue + newFlow);
                node = prevNode;
            }
            if (totalFlow == 2) return 2;
        }
        return totalFlow;
    }

    static void process() {
        int maxflow = findMaxDevicesInitial();
        res = maxflow;
        for (int i = 1; i <= n; i++) {
            int kq = tryDevice(i, graph);
            res = Math.max(res, maxflow + kq);
            if (res == maxflow + 2) break;
        }
        System.out.println(res);
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}

