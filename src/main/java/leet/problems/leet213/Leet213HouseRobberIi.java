package leet.problems.leet213;

import java.util.Arrays;

public class Leet213HouseRobberIi {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int numHouses = nums.length;

        int[] bestStartingFrom = new int[numHouses + 2];
        for (int i = numHouses - 1; i >= 0; i--) {
            bestStartingFrom[i] = Math.max(
                nums[i] + bestStartingFrom[i + 2],
                bestStartingFrom[i + 1]
            );
        }
        int bestWithoutFirst = bestStartingFrom[1];

        Arrays.fill(bestStartingFrom, 0);
        for (int i = numHouses - 2; i >= 0; i--) {
            bestStartingFrom[i] = Math.max(
                nums[i] + bestStartingFrom[i + 2],
                bestStartingFrom[i + 1]
            );
        }
        int bestWithFirst = bestStartingFrom[0];

        return Math.max(bestWithFirst, bestWithoutFirst);
    }
}
