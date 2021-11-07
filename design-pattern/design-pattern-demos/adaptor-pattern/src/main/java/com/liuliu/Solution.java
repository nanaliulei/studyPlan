package com.liuliu;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[][] edges = {{5,7},{15,18},{12,6},{5,1},{11,17},{3,9},{6,11},{14,7},{19,13},{13,3},{4,12},{9,15},{2,10},{18,4},{5,14},{17,5},{16,2},{7,1},{0,16},{10,19},{1,8}};
        int[] patience = {0,2,1,1,1,2,2,2,2,1,1,1,2,1,1,1,1,2,1,1};
        Solution solution = new Solution();
        solution.networkBecomesIdle(edges, patience);
    }
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int len = patience.length, result = 0;
        int[] distance = new int[len];
        boolean[] visited = new boolean[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> que = new LinkedList<>();
        for (int[] edge : edges){
            int node1 = edge[0], node2 = edge[1];
            List<Integer> list1 = map.computeIfAbsent(node1, t -> new ArrayList<>()), list2 = map.computeIfAbsent(node2, t -> new ArrayList<>());
            list1.add(node2);
            list2.add(node1);
        }
        que.offer(0);
        visited[0] = true;
        int round = 0;
        while (!que.isEmpty()){
            int size = que.size();
            for (int i = 0; i < size; i++){
                int cur = que.poll();
                distance[cur] = round;
                for (int nextPos : map.get(cur)){
                    if (!visited[nextPos]){
                        que.offer(nextPos);
                        visited[nextPos] = true;
                    }
                }
            }
            round++;
        }
        for (int i = 0; i < len; i++){
            int dis = distance[i] * 2, time = dis, pa = patience[i];
            if (patience[i] < 2){
                time += dis - 1;
            } else if (dis / patience[i] > 0){
                time += (dis / patience[i]) * patience[i];
            }
            result = Math.max(result, time);
        }
        return result + 1;
    }
}