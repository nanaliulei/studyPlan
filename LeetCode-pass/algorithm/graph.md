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

**算法思想**

贪心思想，

1. 将所有节点（n个）分为两组，一组是所有已经得到最优解的节点S1，另一组是尚未得到最优解的节点S2；

2. 每次从S2中选出路径最短的节点x（该节点已为最优解），放入S1，并对x能到达的所有边进行松弛；

3. 重复执行步骤2，直到S2为空，所有节点均得到最优解。

**证明**

已知：S1中每个节点已得到最优解，初始时，第一个进入S1节点为源节点src

假设，从S2中选出的路径最短节点x不为最优解，最短路径为src ... ... x;

假设最短路径中x的前一个节点为y

1. 如果y在S1中，则已经使用对y-x的边进行了松弛，x已为最优解，假设不成立；

2. 如果y在S2中，则y的当前路径长度应小于x，即dis[y] < dis[x]，但x为S2中路径最小值，dis[x] <= dis[y]，假设不成立

即不存在前置节点y使得x的路径更小，即x已为最优解

**两种实现**

该算法主要实现包括两部分

1. 从S2中选出最短路径节点x

2. 对所有s出发的边进行松弛

第2部操作实现固定，主要考虑对第1部实现进行区别处理

1. 遍历S2，选出路径最短的节点，复杂度为O(n)，适合用于稠密图

2. 使用优先队列存放S2，每次从队列头取出最短路径节点，复杂度为O(logn)，适用于稀疏图。当图比较稠密时，优先队列大小可达N2级别，效率反而降低

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

    Map<Integer, List<Node>> childrenMap;
    int max = 1_000_000;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        childrenMap = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], dis = edge[2];
            List<Node> nodes1 = childrenMap.computeIfAbsent(from, t -> new ArrayList<>());
            List<Node> nodes2 = childrenMap.computeIfAbsent(to, t -> new ArrayList<>());
            nodes1.add(new Node(to, dis));
            nodes2.add(new Node(from, dis));
        }
        int min = max, minPos = 0;
        for (int i = 0; i < n; i++) {
            int count = getNeighbor(n, i, distanceThreshold);
            if (count <= min) {
                min = count;
                minPos = i;
            }
        }
        return minPos;
    }

    private int getNeighbor(int n, int src, int distanceThreshold) {
        //实现1示例
        int[] distances = new int[n];
        Arrays.fill(distances, max);
        distances[src] = 0;
        boolean[] visited = new boolean[n];
        for (int i = 1; i < n; i++) {
            int minPos = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (minPos == -1 || distances[j] < distances[minPos])) {
                    minPos = j;
                }
            }
            visited[minPos] = true;
            if (childrenMap.containsKey(minPos)) {
                for (Node next : childrenMap.get(minPos)) {
                    distances[next.id] = Math.min(distances[next.id], distances[minPos] + next.dis);
                }
            }
        }
        int count = 0;
        for (int distance : distances) {
            if (distance <= distanceThreshold) {
                count++;
            }
        }
        return count;
    }

    class Node {
        int id;
        int dis;
        public Node(int id, int dis) {
            this.id = id;
            this.dis = dis;
        }
    }
}
```

```
class Solution {
    Map<Integer, List<int[]>> map;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        map = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], cost = edge[2];
            List<int[]> list1 = map.computeIfAbsent(from, t -> new ArrayList<>());
            List<int[]> list2 = map.computeIfAbsent(to, t -> new ArrayList<>());
            list1.add(new int[]{to, cost});
            list2.add(new int[]{from, cost});
        }
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
    // 实现2·
    private int findNeighbor(int n, int src, int threshold) {
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Node(src, 0));
        boolean[] visited = new boolean[n];
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.id]) {
                continue;
            }
            visited[node.id] = true;
            if (map.containsKey(node.id)) {
                for (int[] pair : map.get(node.id)) {
                    int next = pair[0], newCost = pair[1] + dis[node.id];
                    if (!visited[next] && newCost < dis[next]) {
                        dis[next] = newCost;
                        pq.offer(new Node(next, newCost));
                    }
                }
            }
        }
        int count = 0;
        for (int num : dis) {
            if (num <= threshold) {
                count++;
            }
        }
        return count;
    }

    class Node {
        int id;
        int cost;
        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}
```
