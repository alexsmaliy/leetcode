package leet.problems.leet377;

import java.util.Arrays;

public class Leet377CombinationSumIv {
    public static int combinationSum4(int[] nums, int target) {
        // Sort here so that we can terminate the for-loop early, as we will
        // know that no smaller coin will follow a coin that is too large to consider.
        Arrays.sort(nums);
        int[] ways = new int[target + 1];
        ways[0] = 1;
        for (int i = 1; i <= target; i++) {
            int way = 0;
            for (int n = 0; n < nums.length && nums[n] <= i; n++) {
                int num = nums[n];
                way += ways[i - num];
            }
            ways[i] = way;
        }
        return ways[target];
    }
}
