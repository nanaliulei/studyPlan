package cn.liuliu;

import java.util.*;

class Solution {

    SegNode root;

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0};
        int lower = 0, upper = 0;
        solution.countRangeSum(nums, lower, upper);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] presum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }
        Set<Long> set = new HashSet<>();
        for (long num : presum) {
            set.add(num);
            set.add(num - lower);
            set.add(num - upper);
        }
        List<Long> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Long, Integer> map = new HashMap<>();
        int n = list.size(), result = 0;
        for (int i = 0; i < n; i++) {
            map.put(list.get(i), i);
        }
        root = build(1, n);
        for (int i = 0; i <= len; i++) {
            long cur = presum[i];
            int low = map.get(cur - upper), high = map.get(cur - lower);
            result += getSum(root, low, high);
            insert(root, map.get(cur));
        }
        return result;
    }

    private int getSum(SegNode root, int low, int high) {
        if (root == null || root.low > high || root.high < low) {
            return 0;
        }
        if (low <= root.low && high >= root.high) {
            return root.count;
        }
        int mid = (root.low + root.high) >> 1, sum = 0;
        if (low <= mid) {
            sum += getSum(root.left, low, high);
        }
        if (high > mid) {
            sum += getSum(root.right, low, high);
        }
        return sum;
    }

    private void insert(SegNode root, int pos) {
        if (root == null || pos < root.low || pos > root.high) {
            return;
        }
        root.count++;
        int mid = (root.low + root.high) >> 1;
        if (pos <= mid) {
            insert(root.left, pos);
        } else {
            insert(root.right, pos);
        }
    }

    private SegNode build(int low, int high) {
        SegNode root = new SegNode(low, high);
        if (low == high) {
            return root;
        }
        int mid = (low + high) >> 1;
        root.left = build(low, mid);
        root.right = build(mid + 1, high);
        return root;
    }
    class SegNode {
        int low, high, count;
        SegNode left, right;
        public SegNode(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
}