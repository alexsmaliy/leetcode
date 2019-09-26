package lc;

public class FindMinimumInRotatedSortedArray {
    static class Solution {
        public int findMin(int[] nums) {
            int length = nums.length;
            if (length == 1) return nums[0];
            if (nums[0] < nums[length - 1]) return nums[0];
            return divideAndConquer(
                nums,
                0,
                (length - 1) / 2,
                (length - 1) / 2 + 1,
                length - 1)[0];
        }

        private static int[] divideAndConquer(int[] nums, int start1, int end1, int start2, int end2) {
            if (nums[end1] > nums[start2]) {
                return new int[] { nums[start2] };
            }
            if (nums[start1] > nums[end1]) {
                return divideAndConquer(
                    nums,
                    start1,
                    start1 + (end1 - start1) / 2,
                    start1 + (end1 - start1) / 2 + 1,
                    end1);
            }
            return divideAndConquer(
                nums,
                start2,
                start2 + (end2 - start2) / 2,
                start2 + (end2 - start2) / 2 + 1,
                end2);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[] {4, 0, 1, 2, 3}));
    }
}
