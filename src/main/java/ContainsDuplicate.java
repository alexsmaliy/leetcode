import java.util.Arrays;

public class ContainsDuplicate {
    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i-1]) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsDuplicate(new int[] {1, 2, 3, 4, 5}));
    }
}
