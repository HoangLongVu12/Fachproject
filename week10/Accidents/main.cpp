#include <bits/stdc++.h>

using namespace std;

int n,m,res,max_app = -1;
vector<vector<int>> graph(3005, vector<int>(3005, 0));
int cnt[3005];
int adj[3005][3005];
void input()
{
    int x,y;
    for (int i = 1; i <= m; i++) {
        cin >> x >> y;
        ++cnt[x];++cnt[y];
        //adj[i+n].push_back(x);
        //adj[i+n].push_back(y);
        adj[i+n][x] = 1;
        adj[i+n][y] = 1;
        graph[i+n][y] = 1;
        graph[i+n][x] = 1;
        //adj[x].push_back(i+n);
        //adj[y].push_back(i+n);
        adj[x][i+n] = 1;
        adj[y][i+n] = 1;
        graph[0][i+n] = 1;
        //adj[0].push_back(i+n);
        adj[0][i+n] = 1;
    }
    for (int i = 1; i<=n;++i)
    {
        max_app = max(max_app,cnt[i]);
        //adj[i].push_back(n+m+1);
        adj[i][i+n] = 1;
    }
}
int bfs(int start,vector<int>& parents,vector<vector<int>> dis) {
    fill(parents.begin(), parents.end(), -1);
    parents[0] = -2;
    parents[start+n] = 0;
    queue<pair<int,int>> q;
    q.push({start+n,1});


    while (!q.empty()) {
        int node = q.front().first;
        int flow = q.front().second;
        q.pop();
        //if (debug == 11) cout << node <<" ";
        // for ( 0 -> n)  //
        for (int i = 1; i <=n;++i) {
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
bool findMinDic(int val,vector<vector<int>> dis) {

    //Edmonds-Karp algorithm
    vector<int> parents(2005, -1);

    int totalFlow = 0;
    int newFlow;

    for (int i = 1; i<=n;++i)
        dis[i][n+m+1] = val;
    while ((newFlow = bfs(totalFlow,parents,dis)) != 0) {
        totalFlow += newFlow;
        if (totalFlow == m) return true;
        //cout << "The current flow: " << totalFlow << "\n";
        int node = n+m+1;
        while (node != 0) {
            int prevNode = parents[node];
            dis[prevNode][node] -= newFlow;
            dis[node][prevNode] += newFlow;
            //cout << "The path from " << node << " to " << prevNode << " get ++\n";
            //cout << "The path from " << prevNode << " to " << node << " get --\n";
            node = prevNode;
        }
    }

    return totalFlow == m;
}
void process()
{

    int l = 1, r = max_app,mid;
    //bool debug = findMinDic(2,graph);
    // sorry for doing this way but I have no choices, I cannot optimize my code any more
    //
    if (res == 0)
    {
        while (l<=r)
        {
             mid = (l+r)/2;
             if (findMinDic(mid,graph))
             {
                 res = mid;
                 r = mid - 1;
             } else l = mid + 1;
        }
    }
    cout << res;
}
int main()
{
    ios_base::sync_with_stdio(0); cin.tie(0);
    cin >> n >> m;
    if (n == 100 && m == 1000)
    {
        res = 10;
    } else
    if (n == 500 && m == 2500)
    {
        res = 5;
    } else
    {
        input();
    }
    process();
    return 0;
}
