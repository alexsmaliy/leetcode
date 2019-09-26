public class MaximumSubarray {
    public static void main(String[] args) {
        new Solution().maxSubArray(new int[] {-1,-2,-3,0,-1});
    }
    static class Solution {
        public int maxSubArray(int[] nums) {
            int best = nums[0];
            int sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum = Math.max(nums[i], sum + nums[i]);
                best = Math.max(best, sum);
            }
            return best;
        }
    }
}
