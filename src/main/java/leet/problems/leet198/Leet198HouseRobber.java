package leet.problems.leet198;

public class Leet198HouseRobber {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int numHouses = nums.length;
        nums[numHouses - 2] = Math.max(
            nums[numHouses - 1],
            nums[numHouses - 2]
        );

        for (int i = nums.length - 3; i >= 0; i--) {
            nums[i] = Math.max(
                nums[i] + nums[i + 2],
                nums[i + 1]
            );
        }

        return nums[0];
    }
}
