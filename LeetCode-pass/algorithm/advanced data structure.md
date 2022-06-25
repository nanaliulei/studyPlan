# 高级数据结构

## 线段树

线段树是用于维护区间信息的数据结构，能够以lgn的复杂度进行 单点更新，区间更新，区间查询操作。

图片摘自oiwiki.com<img title="线段树" src="file:///C:/Users/leiliu1/AppData/Roaming/marktext/images/2022-06-06-21-38-32-1654522698610.png" alt="" data-align="center" width="400" />

线段树以树来表示区间信息，每个节点信息包括区间起始位置、终止位置、区间统计值（最大值、最小值、区间和等）、左孩子、右孩子等信息。当low<right时，左孩子的区间为[low,mid]，右孩子的区间为[mid + 1, high]。

```
class SegNode {
    int low, high, max;
    SegNode left, right;
    public SegNode(int low, int high) {
        this.low = low;
        this.high = high;
    }
}
```

### 基本操作

常规的线段树主要包括三种操作，建树、更新、查询。

示例场景：求区间最大值，更新区间[low,high]最大值，查询区间[low, high]最大值

**建树**

```
public SegNode build(int low, int high) {
    SegNode root = new SegNode(low, high);
    if (low == high) {
        return root;
    }
    int mid = (low + high) >> 1;
    root.left = build(low, mid);
    root.right = build(mid + 1, high);
    return root;
}
```

**更新**

```
public void update(SegNode root, int low, int high, int newMax) {
    if (low > root.high || high < root.low ) {
        return;
    }
    if (low <= root.low && high >= root.high) {
        root.max = Math.max(root.max, newMax);
        return;
    }
    int mid = (root.low + root.high) >> 1;
    if (low <= mid) {
        update(root.left, low, high, newMax);
    }
    if (high > mid) {
        update(root.right, low, high, newMax);
    }
    root.max = Math.max(root.max, Math.max(root.left.max, root.right.max);
}
```

**查询**

```
public int query(SegNode root, int low, int high) {
    if (low > root.high || high < root.low) {
        return Integer.MIN_VALUE;
    }
    if (low <= root.low && high >= root.high) {
        return root.max;
    }
    int max = Integer.MIN_VALUE, mid = (root.low + root.high) >> 1;
    if (left <= mid) {
        max = Math.max(max, query(root.left, low, high);
    }
    if (right > mid) {
        max = Math.max(max, query(root.right, low, high);
    }
    return max;
}
```

### 进阶操作

**动态开点**

当区间特别大时，建树的时间复杂度，空间复杂度均为O(N)，会超出时间或内存限制，此时可以不在开始就把所有节点建好。只在需要时才新建节点。

**懒惰标记**

当区间更新较多，且区间更新范围较大时，时间复杂度也会较大。此时可以不更新所有节点，只对大区间范围进行更新，然后进行标记，此处状态需要向下更新。只有当需要更新或查询子区间信息时，才将结果向下一层更新。

## 树状数组

树状数组用于高效的(**lgn**复杂度)动态更新和获取数组的前缀和。

**预备知识**

lowbit函数（获取x的i，并返回1<<i）：

```
// 引自oiwiki.com
int lowbit(int x) {
  // x 的二进制表示中，最低位的 1 的位置。
  // lowbit(0b10110000) == 0b00010000
  //          ~~~^~~~~  返回值16
  // lowbit(0b11100100) == 0b00000100
  //          ~~~~~^~~  返回值4
  return x & -x;
}
```

**动态前缀和**

计算静态数组的前缀和时，通常采取前缀和数组，并以O(n)复杂度初始化，之后便可以以o(1)的时间复杂度获取数组某一位置的前缀和。

当数组会动态变化时，如果再使用前缀和数组，则需要O(n)时间复杂度对前缀和数组进行更新。如果想要降低时间复杂度，则需要前缀和数组进行修改，以实现lgn时间复杂度的更新。

原数组为a[1~n]，定义数组c[1~n]，其中c[i]代表a[i - lowbit(i) + 1] + ... + a[i]的和。如c[6] = c[5] + c[6]，lowbit(6)=2

在对原数组a进行更新时，如a[i] += 1，则需要对所有包含a[i]的c[i]进行更新:

1. c[i] += 1

2. i += lowbit(i)

在查询前缀和时，sum[i] = a[1] + ... + a[i]，需要将适当的c[j]相加得到sum[i]

1. sum += c[i]

2. i -= lowbit(i)

**示例 [315. 计算右侧小于当前元素的个数](https://leetcode.cn/problems/count-of-smaller-numbers-after-self/)**

```
给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例 1：

输入：nums = [5,2,6,1]
输出：[2,1,1,0] 
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/count-of-smaller-numbers-after-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**树状数组java解法**

```
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Integer, Integer> map = new HashMap<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            map.put(list.get(i), i);
        }
        TA ta = new TA(size);
        int len = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int x = map.get(nums[i]);
            ans.add(ta.querySum(x));
            ta.update(x + 1, 1);
        }
        Collections.reverse(ans);
        return ans;
    }

    class TA {
        int[] c;
        int n;
        public TA(int n) {
            this.c = new int[n + 1];
            this.n = n;
        }
        private int lowbit(int x) {
            return x & (-x);
        }
        public int querySum(int x) {
            int sum = 0;
            while (x > 0) {
                sum += c[x];
                x -= lowbit(x);
            }
            return sum;
        }
        public void update(int x, int val) {
            while (x <= n) {
                c[x] += val;
                x += lowbit(x);
            }
        }
    }
}
```

## 并查集

并查集是一种树形数据结构，用于解决连通性问题和分组问题。主要包含两种操作，查询节点的根和合并两个连通子树。

****

定义parents数组，初始时parents[i]=i，即每个节点的父节点为自己，各节点互不连通

**查询节点的根**

findRoot(x)

* 如果parents[i]=i，则i的根节点为i

* 否则，i的根节点为parents[i]的根节点

**合并**

union(x, y)

* 计算两个节点x，y的根节点

* 如果x，y的根节点相同，则两个节点已经连通

* 如果x，y的根节点不同，则将包含x，y的两棵子树连通，parents[xRoot]=yRoot|parents[yRoot]=xRoot

****

**问题1：**

在将两棵子树合并时，如果随意选择一颗子树的根节点作为根，很容易导致子树的深度过深。极端情况下，如果树的所有节点都只包含一个孩子，则树收缩为链表，查询根节点的时间复杂度为O(n)。

**优化1：路径压缩**

为解决问题1，可以考虑将所有子节点都直接连接到根节点上，这样单纯查询的时间复杂度为O(1)，综合路径压缩，整体复杂度为O(lgn)。

路径压缩一般在findRoot操作中实现：

```
findRoot(int x) {
    if (parents[x] != x) {
        parents[x] = findRoot(parents[x]);
    }
    return parents[x];
}
```

**优化2：按子树大小或深度合并**

为解决问题1，也可以通过修改两棵子树的合并规则来优化。记录每个节点为根的子树的大小或深度，在进行合并时，将大小或深度较小的树合并到另一棵子树上，树的深度更小，整棵树更平衡，查询时间复杂度可以优化为O(lgn)。如果结合路径压缩会有更好的表现。另外，在一些需要保留树原始结构等不适用路径压缩的情况（本人菜，未遇到），可以只使用此优化。

```
        public void union(int x, int y) {
            int xRoot = findRoot(x), yRoot = findRoot(y);
            if (xRoot == yRoot) {
                return;
            }
            if (size[xRoot] < size[yRoot]) {
                parents[xRoot] = yRoot;
                size[yRoot] += size[xRoot];
            } else {
                parents[yRoot] = xRoot;
                size[xRoot] += size[yRoot];
            }
        }
```

****

**示例#### [924. 尽量减少恶意软件的传播](https://leetcode.cn/problems/minimize-malware-spread/)**

```
给出了一个由 n 个节点组成的网络，用 n × n 个邻接矩阵图 graph 表示。在节点网络中，当 graph[i][j] = 1 时，表示节点 i 能够直接连接到另一个节点 j。 
一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
如果从 initial 中移除某一节点能够最小化 M(initial)， 返回该节点。如果有多个节点满足条件，就返回索引最小的节点。
请注意，如果某个节点已从受感染节点的列表 initial 中删除，它以后仍有可能因恶意软件传播而受到感染。
示例 1：

输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
输出：0

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/minimize-malware-spread
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**并查集解法**

```
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int len = graph.length;
        UF uf = new UF(len);
        for(int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (graph[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        int[] counts = new int[len];
        Arrays.sort(initial);
        for (int num : initial) {
            counts[uf.findRoot(num)]++;
        }
        int ans = initial[0], remove = 0;
        for (int num : initial) {
            int root = uf.findRoot(num), curRemove = counts[root] == 1 ? uf.getSize(root) : 0;
            if (curRemove > remove) {
                remove = curRemove;
                ans = num;
            }
        }
        return ans;
    }

    class UF {

        int[] parents, size;

        public UF(int n) {
            this.parents = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 1; i < n; i++) {
                parents[i] = i;
            }
        }

        public int findRoot(int x) {
            if (x != parents[x]) {
                parents[x] = findRoot(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int xRoot = findRoot(x), yRoot = findRoot(y);
            if (xRoot == yRoot) {
                return;
            }
            if (size[xRoot] < size[yRoot]) {
                parents[xRoot] = yRoot;
                size[yRoot] += size[xRoot];
            } else {
                parents[yRoot] = xRoot;
                size[xRoot] += size[yRoot];
            }
        }

        public boolean isRoot(int x) {
            return x == parents[x];
        }

        public int getSize(int x) {
            return size[x];
        }
    }
}
```

## 字典树
