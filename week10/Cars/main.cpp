
#include <bits/stdc++.h>
using namespace std;

int bfs(const vector<vector<int>>& graph, vector<int>& parents) {
    int n = graph.size();
    vector<bool> visited(n, false);
    visited[0] = true;
    vector<int> flow(n, 0);
    flow[0] = INT_MAX;

    queue<int> q;
    q.push(0);

    while (!q.empty()) {
        int node = q.front();
        q.pop();

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[node][i] > 0) {
                parents[i] = node;
                flow[i] = min(flow[node], graph[node][i]);
                if (i == n - 1)
                    return flow[i];
                q.push(i);
                visited[i] = true;
            }
        }
    }

    return 0;
}

int findMinBoats(int n, int m, const vector<vector<int>>& roads) {
    // Initialize the adjacency matrix/list
    vector<vector<int>> graph(n, vector<int>(n, 0));


    // Update adjacency matrix/list
    for (const auto& road : roads) {
        int i = road[0] - 1;
        int j = road[1] - 1;
        int c = road[2];
        graph[i][j] += c;
        graph[j][i] += c;
    }

    //Edmonds-Karp algorithm
    vector<int> parents(n, -1);

    int totalFlow = 0;
    int newFlow;

    while ((newFlow = bfs(graph, parents)) != 0) {
        totalFlow += newFlow;

        int node = n - 1;
        while (node != 0) {
            int prevNode = parents[node];
            graph[prevNode][node] -= newFlow;
            graph[node][prevNode] += newFlow;
            node = prevNode;
        }
    }

    return totalFlow;
}

int main() {
    ios_base::sync_with_stdio(0);cin.tie(0);
    int n, m;
    cin >> n >> m;

    vector<vector<int>> roads(m, vector<int>(3));
    for (int i = 0; i < m; i++) {
        cin >> roads[i][0] >> roads[i][1] >> roads[i][2];
    }

    int result = findMinBoats(n, m, roads);
    cout << result;

    return 0;
}
