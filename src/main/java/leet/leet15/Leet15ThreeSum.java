package leet.leet15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet15ThreeSum {
    public static void main(String[] args) {
        System.out.println(new Leet15ThreeSum().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int count = nums.length;
        if (count < 3) return new ArrayList<>();
        Arrays.sort(nums);
        int prevI = nums[0];
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < count - 2; i++) {
            if (i > 0) {
                prevI = nums[i - 1];
                if (nums[i] == prevI) continue;
            }
            int j = i + 1;
            int k = count - 1;
            int target = -nums[i];
            if (target > nums[k] * 2) continue;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    triplets.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    int prevJ = nums[j];
                    while (nums[j] == prevJ && j < k) {
                        j++;
                    }
                    int prevK = nums[k];
                    while(nums[k] == prevK && j < k) {
                        k--;
                    }
                } else if (sum < target) {
                    int prev = nums[j];
                    while (nums[j] == prev && j < k) {
                        j++;
                    }
                } else {
                    int prev = nums[k];
                    while(nums[k] == prev && j < k) {
                        k--;
                    }
                }
            }
        }
        return triplets;
    }
}
