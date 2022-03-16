# 图相关算法

## 单源最短路径问题

### Bellman-ford 算法

**算法思想**

有n个节点图，从一个初始节点src到任一节点的最长路径长度为n-1。

1. 对于所有边权重都为非负值得情况，如果执行n-1次对所有边的松弛操作，那么对任一节点x，其必然已按照最短路径的顺序进行了松弛，并得到最优解；

2. 对于存在边权重为负值的情况，如果存在负环，则不存在最短路径，无法得到最优解。判断条件为执行n-1次松弛操作之后，存在distance[u] + w(u, v) < distance[v]

**算法实现**

1. 初始化src到所有节点的路径长度为max，即distance[x] = max，distance[src] = 0;

2. 对所有边进行松弛操作；

3. 重复执行步骤2，共n-1次

**示例：力扣1334. 阈值距离内邻居最少的城市**

```
有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。

返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。

注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。

示例 1：
输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
输出：3
解释：城市分布图如上。
每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
城市 0 -> [城市 1, 城市 2] 
城市 1 -> [城市 0, 城市 2, 城市 3] 
城市 2 -> [城市 0, 城市 1, 城市 3] 
城市 3 -> [城市 1, 城市 2] 
城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

```
class Solution {
    int[][] edges;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        this.edges = edges;
        int min = Integer.MAX_VALUE, ans = -1;
        for (int i = 0; i < n; i++) {
            int count = findNeighbor(n, i, distanceThreshold);
            if (count <= min) {
                min = count;
                ans = i;
            }
        }
        return ans;
    }
    // 所有边的权重为正，无需考虑负环问题
    private int findNeighbor(int n, int src, int threshold) {
        int count = 0, max = 1_000_000;
        int[] distances = new int[n];
        Arrays.fill(distances, max);
        distances[src] = 0;
        for (int i = 1; i < n; i++) {
            for (int[] edge : edges) {
                int from = edge[0], to = edge[1], dis =  edge[2];
                if (distances[from] + dis < distances[to]) {
                    distances[to] = distances[from] + dis;
                }
                if (distances[to] + dis < distances[from]) {
                    distances[from] = distances[to] + dis;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (distances[i] <= threshold) {
                count++;
            }
        }
        return  count;
    }
}
```

### 迪杰斯特拉算法（Dijkstra）
