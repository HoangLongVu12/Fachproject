#include <bits/stdc++.h>

using namespace std;

int n,m,k,res;
vector<vector<int>> graph(2005, vector<int>(2005, 0));
vector<int> adj[2005];
void input()
{
    cin >> n >> m >> k;
    int x,y;
    for (int i = 0; i < k; i++) {
        cin >> x >> y;
        y += n;
        adj[x].push_back(y);
        adj[y].push_back(x);
        graph[x][y] = 1;
        graph[0][x] = 1;
        graph[y][n+m+1] = 1;
    }
    for (int i = 1; i<=n;++i) adj[0].push_back(i);
    for (int i = n+1; i<n+m+1;++i) adj[i].push_back(n+m+1);
}

int bfs(vector<int>& parents)
{
    fill(parents.begin(), parents.end(), -1);
    parents[0] = -2;
    queue<pair<int, int>> q;
    q.push({0, INT_MAX});

    while (!q.empty()) {
        int node = q.front().first;
        int flow = q.front().second;
        q.pop();

        for (int i : adj[node]) {
            if (parents[i] == -1 && graph[node][i] > 0) {
                parents[i] = node;
                int new_flow = min(flow, graph[node][i]);
                if (i == n+m+1)
                    return new_flow;
                q.push({i,new_flow});
            }
        }
    }
    return 0;
}
int findMaxDevicesIntial() {
    //Edmonds-Karp algorithm
    vector<int> parents(2005);
    int totalFlow = 0;
    int newFlow;

    while ((newFlow = bfs(parents)) != 0) {
        totalFlow += newFlow;

        int node = n+m+1;
        while (node != 0) {
            int prevNode = parents[node];
            graph[prevNode][node] -= newFlow;
            graph[node][prevNode] += newFlow;
            node = prevNode;
        }
    }

    return totalFlow;
}
int bfs_new(int s,vector<int>& parents,vector<vector<int>> dis) {
    fill(parents.begin(), parents.end(), -1);
    parents[0] = -2;
    parents[s] = 0;
    queue<pair<int,int>> q;
    q.push({s,dis[0][s]});

    while (!q.empty()) {
        int node = q.front().first;
        int flow = q.front().second;
        q.pop();
        for (int i : adj[node]) {
            if (parents[i] == -1 && dis[node][i] > 0) {
                parents[i] = node;
                int new_flow = min(flow, dis[node][i]);
                if (i == n+m+1)
                    return new_flow;
                q.push({i,new_flow});
            }
        }
    }
    return 0;
}
int tryDevice(int s, vector<vector<int>> dis)
{
    vector<int> parents(2005);
    dis[0][s] += 2;
    int totalFlow = 0;
    int newFlow;
    while ((newFlow = bfs_new(s,parents,dis)) != 0) {
        totalFlow += newFlow;

        int node = n+m+1;
        while (node != 0) {
            int prevNode = parents[node];
            dis[prevNode][node] -= newFlow;
            dis[node][prevNode] += newFlow;
            node = prevNode;
        }
        if (totalFlow == 2) return 2;
    }

    return totalFlow;
}
void process()
{
    int maxflow = findMaxDevicesIntial();
    res = maxflow;
    for (int i = 1;i <= n;++i)
    {
        int kq = tryDevice(i,graph);
        res = max(res,maxflow+kq);
        if (res == maxflow + 2) break;
    }
    cout << res;
}
int main()
{
    ios_base::sync_with_stdio(0); cin.tie(0);
    input();
    process();
    return 0;
}
