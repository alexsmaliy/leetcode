package leet.problems.leet55;

public class Leet55JumpGame {
    public static boolean canJump(int[] nums) {
        int leftmostIndexThatCanReachEnd = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= leftmostIndexThatCanReachEnd - i) {
                leftmostIndexThatCanReachEnd = i;
            }
        }
        return leftmostIndexThatCanReachEnd == 0;
    }
}
