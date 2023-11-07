# 图相关算法

## 环路问题

### 三色标记法

**算法思想**

判断是否存在环路，可以通过深度优先搜索，如果搜索路径中的下一个节点已经存在与搜索路径中，则存在环路。每个节点可以标记为3个颜色，0代表未访问，1代表节点在栈（当前路径）中，2代表节点已经完成搜索，深度优先搜索中，如果遇到颜色为1的节点则存在环。

**算法实现**

1. 初始化所有节点颜色为0

2. 深度优先搜索，只能访问颜色为0的节点，访问节点前，将其颜色置为1，访问完成后，将其颜色置为2

3. 如果下一个节点颜色值为1，则存在环

**示例： [207. 课程表](https://leetcode.cn/problems/course-schedule/)**

```
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**三色标记**

```
class Solution {
    int[] colors;
    Map<Integer, List<Integer>> map;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.colors = new int[numCourses];
        this.map = new HashMap<>();
        for (int[] pair : prerequisites) {
            int from = pair[1], to = pair[0];
            map.computeIfAbsent(from, t -> new ArrayList<>()).add(to);
        }
        for (int i = 0; i < numCourses; i++) {
            if (colors[i] == 0 && loop(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean loop(int cur) {
        if (map.containsKey(cur)) {
            for (int next : map.get(cur)) {
                if (colors[next] == 1) {
                    return true;
                }
                if (colors[next] == 0) {
                    colors[next] = 1;
                    if (loop(next)) {
                        return true;
                    }
                }
            }
        }
        colors[cur] = 2;
        return false;
    }
}
```

### 问题列表

 [207. 课程表](https://leetcode.cn/problems/course-schedule/)

 [1559. 二维网格图中探测环](https://leetcode.cn/problems/detect-cycles-in-2d-grid/)

## 拓扑排序问题

### 深度优先搜索

**算法思想**

进行深度优先搜索时，当其后所有节点遍历完成后将当前节点加入栈中，此时所有在当前节点之后的节点已在栈中。所有节点搜索完成后，依次弹出栈中节点顺序即为拓扑排序顺序。另可以使用三色标记解决存在环路情况

**算法实现**

1. 初始时标记所有节点颜色为0（未访问）

2. 从所有根节点开始进行深度优先搜索；访问节点前将其颜色标记为1（访问中）；节点搜索完成后将其颜色标记为2（已访问），并加入栈中

3. 如果要访问的下一个节点颜色为1，则存在环，不存在拓扑排序，结束搜索

**示例#### [210. 课程表 II](https://leetcode.cn/problems/course-schedule-ii/)**

```
现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2：

输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
示例 3：

输入：numCourses = 1, prerequisites = []
输出：[0]

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/course-schedule-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**深度优先**

```
class Solution {
    Map<Integer, List<Integer>> map;
    Stack<Integer> stack;
    int[] colors;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        stack = new Stack<>();
        colors = new int[numCourses];
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            int from = pair[1], to = pair[0];
            map.computeIfAbsent(from, t -> new ArrayList<>()).add(to);
            indegree[to]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0 && colors[i] == 0 && loop(i)) {
                return new int[0];
            }
        }
        if (stack.size() < numCourses) {
            return new int[0];
        }
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    private boolean loop(int cur) {
        if (map.containsKey(cur)) {
            for (int next : map.get(cur)) {
                if (colors[next] == 1) {
                    return true;
                }
                if (colors[next] == 0) {
                    colors[next] = 1;
                    if (loop(next)) {
                        return true;
                    }
                }
            }
        }
        colors[cur] = 2;
        stack.push(cur);
        return false;
    }
}
```

### 广度优先搜索

**算法思想**

当节点的入度为0时，没有指向该节点的边，因此可以将节点放在拓扑排序的第一位。利用该性质可以将所有入度为0的节点取出，之后再将剩余子图中入度为0的节点取出，直到没有入度为0的节点。节点取出的顺序即为图的一种拓扑排序。如果存在换，则取出的节点数量小于图中总的节点数量。

**算法实现**

1. 初始化图中所有节点的入度（指向该节点的边的数量），将所有入度为0的节点放入队列中

2. 从队列中取出一个节点，并将它指向节点的入度减一，如果节点新的入度为0，则加入队列中

3. 重复2，直到队列为空

****示例#### [210. 课程表 II](https://leetcode.cn/problems/course-schedule-ii/)****

```
现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。

例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：[0,1]
解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2：

输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
输出：[0,2,1,3]
解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
示例 3：

输入：numCourses = 1, prerequisites = []
输出：[0]

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/course-schedule-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**广度优先**

```
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses], result = new int[numCourses];
        int pos = 0;
        for (int[] pair : prerequisites) {
            int from = pair[1], to = pair[0];
            indegree[to]++;
            map.computeIfAbsent(from, t -> new ArrayList<>()).add(to);
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                que.offer(i);
            }
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            result[pos++] = cur;
            if (map.containsKey(cur)) {
                for (int next : map.get(cur)) {
                    if (--indegree[next] == 0) {
                        que.offer(next);
                    }
                }
            }
        }
        return pos == numCourses ? result : new int[0];
    }
}
```

## 最小生成树问题

### Kruskal算法

**算法思想**

贪心思想，对所有边按权重从小到大进行排序。遍历所有边，如果边中的两个节点未连通，则将该边加入最小生成树中；如果已连通，则跳过继续遍历下一条边。

**算法实现**

该算法主要需判断两个节点是否连通，可使用并查集解决该问题。

**示例：力扣1584. 连接所有点的最小费用**

```
给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。

请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**Kruskal**

```
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int len = points.length, cost = 0;
        UnionFind uf = new UnionFind(len);
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        Collections.sort(edges, (a, b) -> a.weight - b.weight);
        for (Edge edge : edges) {
            int from = edge.from, to = edge.to, weight = edge.weight;
            if (uf.findRoot(from) != uf.findRoot(to)) {
                cost += weight;
                uf.union(from, to);
            }
        }
        return cost;
    }

    class Edge {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    class UnionFind {

        int[] parents;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int findRoot(int x) {
            int origX = x;
            while (x != parents[x]) {
                x = parents[x];
            }
            parents[origX] = x;
            return x;
        }

        public void union(int x, int y) {
            int xRoot = findRoot(x), yRoot = findRoot(y);
            if (xRoot != yRoot) {
                parents[xRoot] = yRoot;
            }
        }
    }
}
```

### Prim算法

**算法思想**

贪心思想，类似Dijkstra算法，将所有节点分为两组，一组为已加入最小生成树的节点S1，另一组为还未加入的节点S2。初始时，随便选取一个节点加入S1；随后每次从S2中选取离S1距离最近的节点加入到S1，直到所有节点都被加入 S1。

**算法实现**

本算法主要需要解决如何找到距离S1最近的节点，可以使用优先队列或遍历所有节点到S1的权重来解决

**优先队列：**

1. 将任一节点加入优先队列，到S1距离为0；

2. 从优先队列中获取到S1距离最小的节点，并更新所有从当前节点出发能到达节点的距离（如果边的权重/距离 小于下一节点到S1的距离，则对该节点的距离进行更新）

3. 重复步骤2，直到所有节点都加入最小生成树

**示例（同上）：力扣1584. 连接所有点的最小费用**

**实现1：优先队列**

```
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int len = points.length, result = 0;
        boolean[] visited = new boolean[len];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.id, weight = node.weight;
            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;
            result += weight;
            for (int i = 1; i < len; i++) {
                if (!visited[i]) {
                    pq.offer(new Node(i, Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1])));
                }
            }
        }
        return result;
    }

    class Node {
        int id, weight;
        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}
```

**实现2：遍历获取最小值**

```
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int len = points.length, result = 0;
        boolean[] visited = new boolean[len];
        int[] distance = new int[len];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        for (int i = 0; i < len; i++) {
            int minPos = -1;
            for (int j = 0; j < len; j++) {
                if (!visited[j] && (minPos == -1 || distance[j] < distance[minPos])) {
                    minPos = j;
                }
            }
            result += distance[minPos];
            visited[minPos] = true;
            for (int j = 0; j < len; j++) {
                if (!visited[j]) {
                    distance[j] = Math.min(distance[j], Math.abs(points[minPos][0] - points[j][0]) + Math.abs(points[minPos][1] - points[j][1]));
                }
            }
        }
        return result;
    }

    class Node {
        int id, weight;
        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }
}
```

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

2. 对所有x出发的边进行松弛

第2部操作实现固定，主要考虑对第1部实现进行区别处理

1. 遍历S2，选出路径最短的节点，复杂度为O(n)，适合用于稠密图

2. 使用优先队列存放S2，每次从队列头取出最短路径节点，复杂度为O(logn)，适用于稀疏图。当图比较稠密时，优先队列大小可达N2级别，效率反而降低（使用斐波那契堆可以解决普通二叉堆问题，但比较复杂，后续补充）

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

**实现1：优先队列**

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

**实现2：遍历**

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

## 所有节点对之间的最短路径问题

### Floyd算法

**算法思想**

动态规划，初始时任意两点之间的距离为两点之间直接路径的权重；之后依次将n个节点加入，每加入一个节点k时，对所有节点对(i, j)进行判断，如果以k为中间节点能够使(i, j)之间的距离变短，则对(i, j)之间的距离进行更新。

**算法实现**

1. 初始化，dp[i][j] = wij，如果没有边初始化为max，另dp[i][i] = 0；

2. 依次加入节点1 - n，每加入一个节点k，执行3步骤

3. 对所有节点对(i, j)，执行dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j])

**证明**

后续补充

**示例同上：力扣1334. 阈值距离内邻居最少的城市**

```
class Solution {
    int max = 1_000_000;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] distances = init(n);
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            distances[from][to] = weight;
            distances[to][from] = weight;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        int minCount = max, minPos = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (distances[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                minPos = i;
            }
        }
        return minPos;
    }

    private int[][] init(int n) {
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], max);
            distances[i][i] = 0;
        }
        return distances;
    }
}
```

## 最小环问题

判断一个图中的最小环路，可以通过对所有边进行遍历。如果去除当前边(u, v)后，u v联通，且距离为x， 则存在环路距离为x + dis(u, v)。最小环路即为所有环路距离的最小值。

如果边有权值，且不都为1，则可使用迪杰斯特拉算法，求uv间的最短距离。

如果边没有权值，或权值都为1，则使用广度有限遍历求uv间的最短距离。

**示例：力扣2608. 图中的最短环**

```
现有一个含 n 个顶点的 双向 图，每个顶点按从 0 到 n - 1 标记。图中的边由二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和 vi 之间存在一条边。每对顶点最多通过一条边连接，并且不存在与自身相连的顶点。

返回图中 最短 环的长度。如果不存在环，则返回 -1 。

环 是指以同一节点开始和结束，并且路径中的每条边仅使用一次。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/shortest-cycle-in-a-graph
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**实现1：**

```
int findShortestCycle(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);
        for (auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
        int ans = -1;
        for (auto& edge : edges) {
            int dis = getLoopDistance(graph, edge[0], edge[1]);
            if (dis > -1 && (ans == -1 || dis < ans)) {
                ans = dis;
            }
        }
        return ans;
    }

    int getLoopDistance(vector<vector<int>>& graph, int from, int to) {
        int dis = 1, n = graph.size();
        queue<int> que;
        que.push(from);
        vector<bool> visited(n);
        visited[from] = true;
        while (!que.empty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int cur = que.front();
                que.pop();
                for (int next : graph[cur]) {
                    if (visited[next] || (cur == from && next == to)) {
                        continue;
                    }
                    if (next == to) {
                        return dis + 1;
                    }
                    visited[next] = true;
                    que.push(next);
                }
            }
            dis++;
        }
        return -1;
    }
```

## 连通性问题

### 强联通分量

### 割点和桥

对于一个无向图，如果去掉一个点i之后，最大连通分量的数量增加了，则点i为割点。通俗来讲，图G为连通图，如果去掉点i，其他点构成的图不再为连通图，则点i为割点；

对于一个无向图，如果去掉一个边（u,v)后，最大联通分量的数量增加了，则边(u,v)为桥。通俗来讲，图G为连通图，如果去掉边(u,v)，图G不再为连通图，则边(u,v)为桥。

#### tarjan算法

**算法思想**

从任一点开始，对图进行深度优先搜索，按照搜索顺序记录每个节点i的序号为seq[i]，另维护一个数组low，low[i]为节点i不经过父节点能到达的节点的最小seq。

如果节点u的下一个节点v不经过父节点u所能到达的最小序列的节点(low[u])大于等于u的序号(seq[u])，且u不是根节点（遍历起点），则u为割点。因为v及其后节点能到达的最小序列节点为u，去掉u之后，v及其子序列与u的祖先不连通。

如果节点u的下一个节点v不经过父节点u所能到达的最小序列的节点(low[u])大于u的序号(seq[u])，则边(u,v)为桥。因为v及其子序列能到达的最小节点序号为u，去掉(u,v)之后，v及其子序列不能到达u及其祖先，原连通分量不再连通。

**算法实现**

1. 初始化全局变量index；

2. 对图进行深度优先搜索，设置当前节点的seq[i]=index++,low[i]=seq[i];
   3- 如果当前节点的后续节点已遍历，且不为父节点，则更新low[i]=min(low[i],seq[next])

3. 如果当前节点存在未遍历的后续节点，则先对后续节点进行遍历
   
   * 更新low[i]=min(low[i], low[next])
   
   * 如果low[next] > seq[i]，边(i, next)为桥
   
   * 如果low[next] >= seq[i]，且i不为根节点，则i为割点
   
   * 如果i为根节点，且有两个以上子节点（非直接意义上的子节点，而是指需要回溯多次才能遍历完所有子节点。例如，i存在边分别指向两个节点next1，next2，如果next1进行深度优先搜索时遍历到了next2，则记i只有一个子节点next1；如果next1搜索时没有遍历到next2，则i有两个子节点next1，next2），则i为割点

**示例#### [1192. 查找集群内的「关键连接」](https://leetcode.cn/problems/critical-connections-in-a-network/)**

```
力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。

「关键连接」 是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。

请你以任意顺序返回该集群内的所有 「关键连接」。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/critical-connections-in-a-network
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**tarjan**

```
class Solution {
    Map<Integer, List<Integer>> map;
    Node[] nodes;
    int index;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        map = new HashMap<>();
        nodes = new Node[n];
        index = 1;
        for (List<Integer> conn : connections) {
            int from = conn.get(0), to = conn.get(1);
            map.computeIfAbsent(from, t -> new ArrayList<>()).add(to);
            map.computeIfAbsent(to, t -> new ArrayList<>()).add(from);
        }
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        tarjan(0, -1);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].bridge) {
                List<Integer> list = new ArrayList<>();
                list.add(nodes[i].parent);
                list.add(i);
                ans.add(list);
            }
        }
        return ans;
    }

    public void tarjan(int cur, int parent) {
        nodes[cur].parent = parent;
        nodes[cur].seq = index++;
        nodes[cur].low = nodes[cur].seq;
        for (int next : map.get(cur)) {
            if (next == parent) {
                continue;
            }
            if (nodes[next].seq > 0) {
                nodes[cur].low = Math.min(nodes[cur].low, nodes[next].seq);
            } else {
                tarjan(next, cur);
                nodes[cur].low = Math.min(nodes[cur].low, nodes[next].low);
                if (nodes[next].low > nodes[cur].seq) {
                    nodes[next].bridge = true;
                }
            }
        }
    }

    class Node {
        int id, parent, seq, low;
        boolean bridge;
        public Node(int id) {
            this.id = id;
        }
    }
}
```
