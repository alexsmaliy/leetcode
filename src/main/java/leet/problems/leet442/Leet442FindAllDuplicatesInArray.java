package leet.problems.leet442;

import java.util.ArrayList;
import java.util.List;

/*
    PROBLEM STATEMENT: Given an array of integers, 1 ≤ a[i] ≤ n
    (n = size of array), some elements appear twice and others
    appear once. Find all the elements that appear twice in this
    array. Could you do it without extra space and in O(n) runtime?
 */
public class Leet442FindAllDuplicatesInArray {
    // The canonical solution is clever. If 1 ≤ a[i] ≤ n, then
    // -n ≤ a[i] - (n + 1) ≤ -1, and -2n - 1 ≤ a[i] - 2 * (n + 1) ≤ -n - 2.
    // Namely, we can tell how many times n + 1 has been subtracted from a[i],
    // and we can always recover a[i] by adding enough n + 1 to it and taking
    // the modulo remainder. For each a[i], we find the unmodified value
    // orig(a[i]) and modify a[orig(a[i]) - 1]. The -1 is to avoid going out
    // of the array bounds, as this problem runs from 1 to n, not 0 to n - 1.
    // We can then traverse the array again and find which indices have been
    // modified more than once. Given an index j, the integer that modified
    // it was j + 1 (namely, orig(a[i]) for some i).
    public static List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length < 2) return new ArrayList<>();
        int n = nums.length;
        int n1 = nums.length + 1;
        for (int i = 0; i < n; i++) {
            int orig = (nums[i] + 3 * n1) % n1;
            nums[orig - 1] -= n1;
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] < -n1) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
