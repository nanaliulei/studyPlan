package cn.liuliu;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] flowers = {13};
        solution.maximumBeauty(flowers, 18, 15, 9, 2);
    }
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int len = flowers.length, uLen = len;
        if (flowers[0] >= target) {
            return (long)full * len;
        }
        long[] presum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            if (flowers[i] >= target) {
                uLen = i;
                break;
            }
            presum[i + 1] = presum[i] + flowers[i];
        }
        long base = (long)(len - uLen) * full, result = base;
        int pos = findPos(flowers, presum, uLen, newFlowers);
        long add = (long)(pos == 0 ? flowers[0] : Math.min(target - 1, (presum[pos] + newFlowers) / pos)) * partial;
        if (!(uLen == len && newFlowers >= (long)target * len - presum[len])) {
            result = base + add;
        }
        for (int i = uLen - 1; i >= 0; i--) {
            long total = (long)target * (uLen - i) - (presum[uLen] - presum[i]);
            if (total > newFlowers) {
                break;
            }
            long remain = newFlowers - total;
            pos = findPos(flowers, presum, i, remain);
            long cur = base + (long)(uLen - i) * full;
            cur += (long)(pos == 0 ? flowers[0] : Math.min(target - 1, (presum[pos] + remain) / pos)) * partial;
            result = Math.max(result, cur);
        }
        return result;
    }

    private int findPos(int[] flowers, long[] presum, int uLen, long remain) {
        int left = 0, right = uLen;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (mid == 0) {
                left = mid + 1;
            } else if ((long)flowers[mid - 1] * mid - presum[mid] == remain) {
                return mid;
            } else if ((long)flowers[mid - 1] * mid - presum[mid] > remain) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}