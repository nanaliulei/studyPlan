# 力扣问题及题解笔记

## 1-100题

### 94. 二叉树的中序遍历

**题目链接：**https://leetcode-cn.com/problems/binary-tree-inorder-traversal/

```
给定一个二叉树的根节点 root ，返回它的 中序 遍历。
```

**题目分析**

中序遍历是指先遍历左子树，再遍历根节点，最后遍历右子树。每个子树也是一样的遍历方式。

* 使用递归实现二叉树中序遍历过于简单，不再介绍。此处使用迭代方式实现。

* 由于递归的底层实现也是基于栈，所以很容易想到使用栈来进行处理。

**实现思路**

1. 将根节点和所有左子节点依次压入栈中，直到没有左子节点；
2. 弹出栈中一个节点，此节点即为当前树中最靠左侧的节点，将其加入列表；同时如果该节点存在右子节点，那么它也在栈中下一个元素的左子树中，因此应该将该节点和其所有左子节点依次压入栈中（同步骤1）；
3. 重复步骤2，直到栈为空。

**JAVA代码实现**

```
	public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        pushLeft(stack, root);
        while (stack.size() > 0){
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
            pushLeft(stack, tmp.right);
        }
        return result;
    }
    //将当前节点及其左子节点迭代压入栈中
    private void pushLeft(Stack<TreeNode> stack, TreeNode root){
        while (root != null){
            stack.push(root);
            root = root.left;
        }
    }
```

### 95. 不同的二叉搜索树II

**题目链接：**https://leetcode-cn.com/problems/unique-binary-search-trees-ii/

```
给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
输入：3
输出：
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释：
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

题目要求输出所有由1-n为节点构成的二叉搜索树。构建一棵树最通用的方法是使用分治算法，将问题分解。首先，需要选取一个节点作为树的根；然后根据条件确定左右子树的节点，递归构建左子树及右子树；最后根节点指向左右子树，完成树的构建。本题要求构建二叉搜索树，因此可以很简单的确定左右子树的节点，很适合使用分治算法构建。

**实现思路**

1. 树的构建方法入参为构建树的最小和最大值；
2. 从最小值到最大值依赖遍历，作为根节点；
3. 递归创建左子树，左子树最小值为原最小值，最大值为选取的根节点值-1；
4. 递归创建右子树，右子树的最小值为选取的根节点值+1，最大值为原最大值；
5. 根节点left、right指针分别指向左右子树；
6. 终止条件为最小值>最大值，即没有可用于构建树的节点。

**JAVA代码实现**

```
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return buildTree(1, n);

    }
    
    private List<TreeNode> buildTree(int start, int end){
        List<TreeNode> treeList = new ArrayList<>();
        if (start > end){
            treeList.add(null);
            return treeList;
        }
        for (int i = start; i <= end; i++){
            List<TreeNode> leftTrees = buildTree(start, i -1);
            List<TreeNode> rightTrees = buildTree(i + 1, end);
    
            for (TreeNode leftTree : leftTrees){
                for (TreeNode rightTree : rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    treeList.add(root);
                }
            }
        }
        return treeList;
    }
}
```

### 96. 不同的二叉搜索树

**题目链接：**https://leetcode-cn.com/problems/unique-binary-search-trees/

```
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

该题与95题很像，都是基于1-n个节点构建二叉搜索树。本题要求更简单一些，只需返回所有可构建的二叉搜索树的数量。

* 本题也可以使用分治算法，构建树的方法返回值不再需要返回具体的树的列表，只需要返回可构建的树的个数；但是这种算法会超时，因为很多子树会被反复构建，而且当节点数量相同时，子树的数量是相同的，因此存在大量的重复，需要剪枝；
* 本题中n个节点构建数量，可以由1,2，... n-1个节点构建树的数量推导出来，因此可以使用动态规划算法进行求解。

**实现思路**

动态规划算法实现需要两个条件，**初始状态**和**状态转移方程**。

1. 初始状态的设置：0个节点$dp[0]=1$,1个节点$dp[1]=1$，（一个节点有一种树的构建方式，因为后续构建树的过程中涉及到乘法，因此将0个节点的初始值也设置为1）；
2. 状态转移方程：$dp[n] = dp[0] * dp[n-1] + dp[1] * dp[n-2] + ... + dp[i] * dp[n - 1 -i] ... + dp[n-1] * dp[0]$，其中以i节点为根可以构建树的数量为[1, i - 1]节点构建树的数量乘以[i+1, n]节点构建树的数量；而构建树的数量只与节点数量有关，因此以i节点为根的构建树的数量为$dp[i-1] * dp[n-i]$;

**JAVA代码实现**

```
class Solution {
    public int numTrees(int n) {
        if (n == 0){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int left = 0; left < i; left++){
                dp[i] += dp[left] * dp[i - 1 - left];
            }
        }
        return dp[n];
    }
}
```

## 101-200题

### 115. 不同的子序列-困难

**题目链接：**https://leetcode-cn.com/problems/distinct-subsequences/

```
给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。

字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

题目数据保证答案符合 32 位带符号整数范围。

 

示例 1：

输入：s = "rabbbit", t = "rabbit"
输出：3
解释：
如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)
rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
示例 2：

输入：s = "babgbag", t = "bag"
输出：5
解释：
如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
(上箭头符号 ^ 表示选取的字母)
babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^


提示：

0 <= s.length, t.length <= 1000
s 和 t 由英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/distinct-subsequences
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

题目要求计算s字符串中与t字符串相同的所有子序列数量，最容易想到的方法是从左到右以深度优先搜索遍历方式依次对比。但此种方式计算，时间复杂度极高，设s的长度为N，t的长度为M，寻找每一个与t相同的字符的需要O(N)的时间复杂度，寻找M个字符都匹配的序列则需要O(N<sup>M</sup>)的时间复杂度。这显然是不能接受的！

逐个寻找匹配字符串时间复杂度之所以如此之高，是因为其存在大量重复的匹配计算。如果能建立一个数组，来存储匹配的中间状态结果，之后每次匹配计算之后都更新新的中间状态，那么就可以大大减少时间复杂度。中间状态+状态转移，很明显我们可以使用动态规划算法来解决。我们需要做的就是明确状态、初始值以及状态转移方程（动态规划三个基本要素）。

* 确定状态：定义数组存储中间状态$dp[N][M]$，其中$dp[i][j]$表示$s.charAt(i)与t.charAt(j)匹配时，$s的前i +  1位组成的字符串包含多少个子序列与t的j + 1位相等；
* 确定初始值：当j = 0时，对于所有$s.charAt(i) == t.charAt(0)$的i值，$dp[i][j] = 1$；
* 确定状态转移方程：当遍历至$s.charAt(i),t.charAt(j)$时，如果两字符相等，则计算当前状态的值。$dp[i][j]=dp[0][j - 1] + dp[1][j - 1] + ... + dp[i - 1][j - 1]$，即所有i左侧的s子串等于0~j-1的t的子串的子序列数量之和；
* 最后计算$dp[0][M - 1] + dp[1][M - 1] + ... + dp[N - 1][M - 1]$即为所有满足条件的子序列数量。

**时间复杂度分析**

双重遍历s、t数组，时间复杂度O(NM)；

计算状态转移方程，时间复杂度O(N)；

整体时间复杂度O(MN<sup>2</sup>)。

**`java`代码如下**

```
class Solution {
    public int numDistinct(String s, String t) {
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        int sLen = sArray.length, tLen = tArray.length, result = 0;
        int[][] dp = new int[sLen][tLen];
        for (int j = 0; j < tLen; j++){
            for (int i = j; i < sLen; i++){
                if (sArray[i] == tArray[j]){
                    dp[i][j] = countSatisfied(dp, i, j);
                }
            }
        }
        for (int i = 0; i < sLen; i++){
            result += dp[i][tLen - 1];
        }
        return result;
    }

    private int countSatisfied(int[][] dp, int i, int j){
        if (j == 0){
            return 1;
        }
        int count = 0;
        for (int pos = 0; pos < i; pos++){
            count += dp[pos][j - 1];
        }
        return count;
    }
}
```

**算法优化**

以上算法为了便于理解，$dp[i][j]$表示的是i,j进行匹配时，子序列的数量，因此状态转移方程需要计算所有i之前与j-1进行匹配的子串数量。其实此处状态转移方程时间复杂度可以简化为O(1)。只需要去掉i,j进行匹配的限制。

* 状态：用$dp[i][j]$表示s的前i+1个字符能与t的前j+1个字符匹配的所有子序列数量，不要求$s.charAt(i) == t.charAt(j)$。这样我们算法的整体时间复杂度可以优化为O(MN)。
* 初始值：$dp$数组初始值全部为0；
* 状态转移方程：如果$s.charAt(i) != s.charAt(j)$，那么s的当前字符不会增加匹配的可能性，s的0~i子串与0~i-1子串包含的与t的0~j子串相同的子序列数量相同，$dp[i][j] = dp[i - 1][j]$；如果$s.charAt(i) == s.charAt(j)$，那么s的当前字符增加了匹配的可能性，增加的可能性数量为s的0~i-1个字符所有与t的0~j-1个字符相同的子序列数量，$dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]$。
* 返回值：$dp[N][M]$即为最终的满足条件的子序列数量。

**`java`代码如下**

注：为了不判断i = 0和j = 0的临界条件，将$dp数组的大小设置为dp[N + 1][M + 1]$。因为计算t的第0个字符时，如果s的当前字符匹配增加的数量为1，因此将$dp[i][0]初始化为1$

```
class Solution {
    public int numDistinct(String s, String t) {
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        int sLen = sArray.length, tLen = tArray.length, result = 0;
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i <= sLen; i++){
            dp[i][0] = 1;
        }
        for (int j = 0; j < tLen; j++){
            for (int i = j; i < sLen; i++){
                dp[i + 1][j + 1] = dp[i][j + 1];
                if (sArray[i] == tArray[j]){
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }
        return dp[sLen][tLen];
    }
}
```



## 701-800题

###  724. 寻找数组的中心索引-简单

**题目链接：**https://leetcode-cn.com/problems/find-pivot-index/

```
给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
示例 1：
输入：
nums = [1, 7, 3, 6, 5, 6]
输出：3
解释：
索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
同时, 3 也是第一个符合要求的中心索引。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-pivot-index
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

中心索引是指左右元素和相同的点，很容易可以想到先计算数组所有元素和，然后从左到右依次遍历计算左侧元素和，如果为**左侧元素和** * 2 = **所有元素和** - **当前元素**，那么当前位置就是中心索引。

**时间复杂度分析**

遍历两次，时间复杂度为O(n)。

**JAVA代码如下**

```
	public int pivotIndex(int[] nums) {
        int sum = 0, preSum = 0, len = nums.length;
        for (int i = 0; i < len; i++){
            sum += nums[i];
        }
        for (int i = 0; i < len; i++){
            int sumWithoutCurrent = sum - nums[i];
            preSum += i > 0 ? nums[i - 1] : 0;
            if (preSum * 2 == sumWithoutCurrent){
                return i;
            }
        }
        return -1;
    }
```

## 901-1000题

### 915. 分割数组

**题目链接：**https://leetcode-cn.com/problems/partition-array-into-disjoint-intervals/

```
给定一个数组 A，将其划分为两个连续子数组 left 和 right， 使得：

left 中的每个元素都小于或等于 right 中的每个元素。
left 和 right 都是非空的。
left 的长度要尽可能小。
在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。

示例 1：

输入：[5,0,3,8,6]
输出：3
解释：left = [5,0,3]，right = [8,6]
示例 2：

输入：[1,1,1,0,6,12]
输出：4
解释：left = [1,1,1,0]，right = [6,12]

提示：

2 <= A.length <= 30000
0 <= A[i] <= 10^6
可以保证至少有一种方法能够按题目所描述的那样对 A 进行划分

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-array-into-disjoint-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

​		题目要求将数组分割成两部分，左侧所有值都小于等于右侧所有值。那么只需要左侧最大值小于等于右侧最小值即可。如果从左到右依次分割计算左右两侧的最值，那么最坏的情况下时间复杂度将为O(N<sup>2</sup>)，显然对于本题中提示的量级是不合适的。

​		本题中，从左到右遍历，左侧的最大值可以很容易利用O(1)时间复杂度计算出来，关键在于计算右侧数组的最小值。从左到右遍历数组，计算右侧区间最值可以考虑使用栈来处理。

1. 从右到左遍历数组，如果当前值小于栈顶元素，则将其压入栈中，栈顶元素值即为右侧数组最小值；

2. 从左到右遍历数组（当前元素及左侧为左侧数组）
   * 如果当前元素大于原左侧最大值，则更新最大值；
   * 如果当前元素值等于栈顶元素值，则出栈；
   * 如果左侧元素最大值小于等于栈顶元素的值，则数组切分完成，返回结果。

**时间复杂度分析**

从右向左遍历数组压入最小栈操作，时间复杂度O(N)；从左到右遍历数组，比较左右数组最大最小值操作，时间复杂度O(N)。综合时间复杂度O(N)。

**`java`代码**

```
class Solution {
    public int partitionDisjoint(int[] A) {
        Stack<Integer> stack = new Stack<>();
        int len = A.length, leftMax = 0;
        stack.push(len - 1);
        for (int i = len - 2; i > 0; i--){
            if (A[i] < A[stack.peek()]){
                stack.push(i);
            }
        }
        for (int i = 0; i < len; i++){
            leftMax = Math.max(leftMax, A[i]);
            if (i == stack.peek()){
                stack.pop();
            }
            if (leftMax <= A[stack.peek()]){
                return i + 1;
            }
        }
        return len;
    }
}
```

**算法优化**

虽然以上算法已经实现了O(N)的实现复杂度，但是对数组进行了两次遍历，并使用了栈操作，相对耗时较长。可以考虑不使用栈来实现同样的算法。

1. 创建两个变量，max为全局最大值，$leftMax$为左侧数组最大值，初始值都为A[0]；
2. 创建一个变量boundary存放左侧数组的右边界，初始值为0；
3. 从左到右遍历数组
   * 如果当前元素小于左侧最大值，那么当前元素必然在左侧数组，boundary更新为当前位置，左侧最大值更新为当前全局最大值max；
   * 如果当前元素大于max，则更新max；
4. 最后得到的boundary即为左侧数组的右边界，因为右侧已经没有元素比以boundary为界的左侧最大值小了。

**`java`代码如下**

```
class Solution {
    public int partitionDisjoint(int[] A) {
        int max = A[0], leftMax = A[0], boundary = 0;
        for (int i = 1; i < A.length; i++){
            max = Math.max(max, A[i]);
            if (A[i] < leftMax){
                leftMax = max;
                boundary = i;
            }
        }
        return boundary + 1;
    }
}
```



## 1001-1100题

### 1026. 节点与其祖先之间的最大差值

**题目链接：**https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/

```
给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）

提示：
树中的节点数在 2 到 5000 之间。
0 <= Node.val <= 105
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

题目要求可以理解为计算所有从根节点到叶子结点路径中，节点差值的最大值。因此可以使用`dfs`对每条路径进行遍历，记录最大值和最小值，当遍历到叶子节点后计算差值，并更新最大差值。

**时间复杂度分析**

所有节点都遍历一遍，时间复杂度$O(N)$。

**`java`代码如下**

```
class Solution {
    int result = 0;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root, root.val, root.val);
        return result;
    }

    private void dfs(TreeNode root, int max, int min){
        if (root == null){
            result = Math.max(result, max - min);
            return;
        }
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        dfs(root.left, max, min);
        dfs(root.right, max, min);
    }
}
```



### 1052. 爱生气的书店老板

**题目链接：**https://leetcode-cn.com/problems/grumpy-bookstore-owner/

```
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

请你返回这一天营业下来，最多有多少客户能够感到满意的数量。


示例：

输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.


提示：

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

在不考虑老板秘密技巧的情况下，满意的顾客数是固定的；影响满意顾客数的因素为老板抑制情绪的时间内，不满意的顾客的数量。所以，要想让满意的顾客数最大，只需要使老板抑制情绪时间内，不满意的顾客数量最大。计算固定大小范围内的最大值，使用滑动窗口。

解题步骤如下：

* 计算不使用秘密技巧的情况下，满意顾客的数量（base）；
* 设置left=0，right=x-1的滑动窗口，计算窗口内不满意的客户数量；
* 窗口每次一位向右滑动，直到$right=customers.length-1$，计算滑动窗口内出现的最大值（$maxAppend$)；
* $base + maxAppend$即为最大的满意顾客数量。

**时间复杂度分析**

计算基础满意顾客数遍历一次，滑动窗口计算最大不满意顾客数再遍历一次，时间复杂度为O(N)。

**Java代码如下**

```
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int base = 0, len = customers.length, left = 0, right = 0, append = 0, maxAppend = 0;
        for (int i = 0; i < len; i++){
            base += customers[i] * (grumpy[i] ^ 1);
        }
        while (right < X){
            append += customers[right] * grumpy[right++];
        }
        maxAppend = append;
        while (right < len){
            append -= customers[left] * grumpy[left++];
            append += customers[right] * grumpy[right++];
            maxAppend = Math.max(maxAppend, append);
        }
        return base + maxAppend;
    }
```

### 1081. 不同字符的最小子序列

**题目链接：**https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/

```
返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。

注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同

示例 1：
输入：s = "bcabc"
输出："abc"

示例 2：
输入：s = "cbacdcbc"
输出："acdb"

提示：
1 <= s.length <= 1000
s 由小写英文字母组成

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

要获得最小的字典序子序列，需要小的字母尽量靠前。可以考虑使用一个栈来存储目标子序列，依次遍历字符串中的字母：

1. 如果当前字符在栈中已存在，那么跳过进行下一个字符的处理；
2. 如果栈为空或者栈顶的字符小于当前字符，直接入栈；
3. 如果栈顶的字符大于当前字符，且后续字符串中还存在栈顶字符，则将栈顶字符弹出，之后重复2,3操作，直到当前字符入栈；

**时间复杂度分析**

可以维护一个字母剩余数量的数组来判断后续字符串还有没有栈顶字符。首先遍历字符串，计算所有字符的数量。遍历字符串时，将当前字符数量减一即可。此操作时间复杂度为O(N)。

遍历字符串入栈时，会涉及到栈弹出操作，最差的情况是栈内所有元素全部弹出。设栈的深度为M，遍历字符串操作的时间复杂度为O(MN)，因为字符串由小写字母组成，因此栈的深度最大为26。本题时间复杂度为O(N)。

**`java`代码如下**

```
class Solution {
    public String smallestSubsequence(String s) {
    	//最多有26个字符，使用长度为26的数组模拟栈
        char[] que = new char[26], chars = s.toCharArray();
        int top = 0;
        int[] counts = new int[26];
        for (int i = 0; i < chars.length; i++){
            counts[chars[i] - 'a']++;
        }
        for (int i = 0; i < chars.length; i++){
            char c = chars[i];
            counts[c - 'a']--;
            if (contains(que, top, c)){
                continue;
            }
            if (top == 0 || c > que[top - 1]){
                que[top++] = c;
            }else if (c < que[top - 1]){
                while (top > 0 && c < que[top - 1] && counts[que[top - 1] - 'a'] > 0){
                    top--;
                }
                que[top++] = c;
            }
        }
        return new String(que, 0, top);
    }

    private boolean contains(char[] que, int top, char c){
        for (int i = 0; i < top; i++){
            if (que[i] == c){
                return true;
            }
        }
        return false;
    }
}
```



## 1101-1200题

### 1128. 等价多米诺骨牌的数量-简单

**题目链接：**https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/

```
给你一个由一些多米诺骨牌组成的列表 dominoes。
如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
示例：
输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1
提示：
1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

计算等价骨牌对的数量可以有两种方式：

* 最简单的是遍历所有骨牌对，判断是否符合条件；
* 还可以计算出每个骨牌等价的骨牌数量，利用C(n,2)计算等价骨牌对的数量。

根据题目给定条件$1 <= dominoes[i][j] <= 9$可以简化问题：

* 建立一个行和列都是10的二维数组存放骨牌的数量，$flag[i][j]$代表第一个值为i，第二个值位j的骨牌的数量。根据等价条件，由i、j组成的骨牌和j、i组成的骨牌是等价的，因此可以只遍历flag数组的左上部分，除i、i节点外，每个节点的等价骨牌数量为$flag[i][j] + flag[j][i]$。再根据公式计算每个节点等价骨牌对数量，将其相加。

**JAVA代码如下：**

```
public int numEquivDominoPairs(int[][] dominoes) {
        int len = dominoes.length, result = 0;
        //存储值为i、j的骨牌数量
        int[][] flag = new int[10][10];
        for (int i = 0; i < len; i++){
            flag[dominoes[i][0]][dominoes[i][1]]++;
        }
        for (int i = 1; i < 10; i++){
            //对角线节点等价骨牌对计算
            result +=flag[i][i] * (flag[i][i] - 1) / 2;
            for (int j = i + 1; j < 10; j++){
                //非对角线节点等价骨牌对计算
                int count = flag[i][j] + flag[j][i];
                result += count * (count - 1) / 2;
            }
        }
        return result;
    }
```

## 1501-1600题

### 1535. 找出数组游戏的赢家

**题目链接：**https://leetcode-cn.com/problems/find-the-winner-of-an-array-game/

```
给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。

每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
返回赢得比赛的整数。
题目数据 保证 游戏存在赢家。

示例 1：
输入：arr = [2,1,3,5,4,6,7], k = 2
输出：5
解释：一起看一下本场游戏每回合的情况：
因此将进行 4 回合比赛，其中 5 是赢家，因为它连胜 2 回合。
示例 2：
输入：arr = [3,2,1], k = 10
输出：3
解释：3 将会在前 10 个回合中连续获胜。
示例 3：
输入：arr = [1,9,8,2,3,7,6,4,5], k = 7
输出：9
示例 4：
输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
输出：99

提示：

2 <= arr.length <= 10^5
1 <= arr[i] <= 10^6
arr 所含的整数 各不相同 。
1 <= k <= 10^9

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-winner-of-an-array-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**题目分析**

题目要求找出连续k轮的胜者，最直接的做法是逐个比较，找出连续k轮的胜者。时间复杂度为O(k)。但是题中给出，k的取值非常大，该算法显然不合适。

题目中还给了一个提示，数组长度最大为10000，如果比较k次，可能会遍历数组10000次甚至更多，而这显然是无意义的。因为将数组中元素比较一遍之后，当前待比较的元素一定是数组中的最大值，因此无需再进行比较了，直接返回最大值即可。



**时间复杂度分析**

时间复杂度为O(min(k,n))

**$java$代码如下**

```
class Solution {
    public int getWinner(int[] arr, int k) {
        int len = arr.length, winPos = 0, winRount = 0;
        for (int i = 1; i < len; i++){
            if (arr[winPos] > arr[i]){
                winRount++;
            }else{
                winPos = i;
                winRount = 1;
            }
            if (winRount == k){
                break;
            }
        }
        return arr[winPos];
    }
}
```

### 1584. 连接所有点的最小费用-中等

**题目链接：**https://leetcode-cn.com/problems/min-cost-to-connect-all-points/

```
给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。

连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
示例 1：
输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
输出：20
解释：
我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
注意到任意两个点之间只有唯一一条路径互相到达。
提示：
1 <= points.length <= 1000
-106 <= xi, yi <= 106
所有点 (xi, yi) 两两不同。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

![img](https://assets.leetcode.com/uploads/2020/08/26/c.png)

**题目分析**

​       要把所有点连通起来，首先能想到的方法是建立一个集合来存储所有已连通的点，然后依次将集合之外的点加入到集合中来。

* 首先会想到计算最小费用的一种方式是：
  1. 选择任意一个点作为集合里的初始节点，
  2. 计算集合外每个节点到集合内所有节点的最近距离
  3. 将与集合距离最近的那个节点加入到集合中，并将该节点加入费用计入总费用；
  4. 重复2-3，直到所有节点都加入到集合中。

**时间复杂度分析**

上述算法

1. 每次计算节点与集合内节点的最小距离的时间复杂度为O(n)；

2. 每加入一个节点需要将所有未加入节点遍历一次，因此加入一个节点的时间复杂度为O(n<sup>2</sup>)；
3. 将所有节点加入集合的时间复杂度为**O(n<sup>3</sup>)**。

节点的最大数量为1000，这个时间复杂度是不能接受的。

重新观察时间复杂度的计算过程，2和3都不太能简化，但是1计算每个节点与集合内的最小距离是可以简化的，因为每一轮加入一个节点的时候，都会计算所有节点的到集合内节点的最小距离；下一轮计算时又将节点到集合内所有节点的距离计算一遍，这显然是重复的。

**算法优化**

因此可以采用空间换时间的思想，每一轮插入节点时，将当前节点到集合内所有节点的最短距离记录下来；下一轮插入节点时，计算当前节点到集合的最小距离只需要计算当前节点到上一轮集合的最小距离和当前节点到上一轮加入节点的最小值即可。这样计算节点到集合的最小距离的时间复杂度降为O(1)，整体时间复杂度O(n<sup>2</sup>)。

**JAVA代码如下**

```
class Solution {
    public int minCostConnectPoints(int[][] points) {
        List<int[]> connectedPoints = new ArrayList<>();
        int size = points.length, result = 0, lastestPoint = 0;
        //标识节点是否已加入连通图
        boolean[] added = new boolean[size];
        //存储节点到连通图中的最短曼哈顿距离
        int[] minDis = new int[size];
        connectedPoints.add(points[0]);
        added[0] = true;
        Arrays.fill(minDis, Integer.MAX_VALUE);
        while (connectedPoints.size() < size){
            int minPos = 0;
            for (int i = 1; i < size; i++){
                if (!added[i]){
                    minDis[i] = Math.min(minDis[i], calDistance(points[lastestPoint], points[i]));
                    minPos = minDis[i] > minDis[minPos] ? minPos : i;
                }
            }
            connectedPoints.add(points[minPos]);
            added[minPos] = true;
            result += minDis[minPos];
            lastestPoint = minPos;
        }
        return result;
    }

    private int calDistance(int[] point1, int[] point2){
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
}
```

