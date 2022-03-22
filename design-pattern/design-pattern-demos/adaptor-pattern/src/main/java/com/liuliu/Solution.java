package com.liuliu;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        solution.minimumTotal(triangle);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] sum = new int[len];
        Arrays.fill(sum, Integer.MAX_VALUE);
        sum[0] = 0;
        for (List<Integer> list : triangle) {
            int curLen = list.size();
            for (int i = curLen - 1; i > 0; i--) {
                sum[i] = Math.min(sum[i], sum[i - 1]) + list.get(i);
            }
            sum[0] += list.get(0);
        }
        return sum[len - 1];
    }
}