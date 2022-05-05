package cn.liuliu;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


class Solution {
    int max, origMax;
    List<Integer> counts, sums;

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(8);
        solution.getMaxLayerSum(root);
    }
    public int getMaxLayerSum(TreeNode root) {
        counts = getLayerCount(root);
        sums = getLayerSum(root);
        max = Integer.MIN_VALUE;
        for (int sum : sums) {
            max = Math.max(max, sum);
        }
        dfs(root, 0);
        return max;
    }

    private void dfs(TreeNode root, int dep) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && root.val < 0) {
            max = Math.max(max, sums.get(dep) - root.val);
        } else if (root.left != null && root.right != null) {
            dfs(root.left, dep + 1);
            dfs(root.right, dep + 1);
        } else if (root.left != null && counts.get(dep) > 1) {
            List<Integer> curSums = getLayerSum(root);
            getMaxWithLayerDelete(curSums, dep, root.val);
            dfs(root.left, dep + 1);
        } else if (root.right != null && counts.get(dep) > 1) {
            List<Integer> curSums = getLayerSum(root);
            getMaxWithLayerDelete(curSums, dep, root.val);
            dfs(root.right, dep + 1);
        }
    }

    private void getMaxWithLayerDelete(List<Integer> curSums, int dep, int val) {
        int size = sums.size();
        for (int i = dep; i < size; i++) {
            int cur = sums.get(i) - val + (i - dep < curSums.size() ? curSums.get(i - dep) : 0);
            max = Math.max(max, cur);
            val = curSums.get(i - dep);
        }
    }



    public List<Integer> getLayerSum(TreeNode root) {
        List<Integer> sums = new ArrayList<>();
        if (root == null) {
            return sums;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size(), sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                sum += node.val;
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
            sums.add(sum);
        }
        return sums;
    }

    public List<Integer> getLayerCount(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        if (root == null) {
            return counts;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            counts.add(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (node.left != null) {
                    que.offer(node.left);
                }
                if (node.right != null) {
                    que.offer(node.right);
                }
            }
        }
        return counts;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}