# 高级数据结构

## 线段树

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

## 字典树
