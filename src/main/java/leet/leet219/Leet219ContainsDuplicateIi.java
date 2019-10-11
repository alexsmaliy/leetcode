package leet.leet219;

import java.util.Arrays;
import java.util.Comparator;

/*
    PROBLEM STATEMENT: Given an array of integers and an integer k,
    find out whether there are two distinct indices i and j in the
    array such that nums[i] = nums[j] and the absolute difference
    between i and j is at most k.
 */
public class Leet219ContainsDuplicateIi {
    private static final Comparator<int[]> C = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            int diff = a[0] - b[0];
            return diff == 0 ? a[1] - b[1] : diff;
        }
    };

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2) return false;
        int[][] n = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            n[i][0] = nums[i];
            n[i][1] = i;
        }
        Arrays.sort(n, C);
        for (int i = 1; i < n.length; i++) {
            if (n[i][0] == n[i - 1][0] && n[i][1] - n[i - 1][1] <= k) {
                return true;
            }
        }
        return false;
    }
}
