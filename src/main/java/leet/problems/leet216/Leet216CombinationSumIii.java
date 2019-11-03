package leet.problems.leet216;

import java.util.ArrayList;
import java.util.List;

public class Leet216CombinationSumIii {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combos = new ArrayList<>();
        helper(new boolean[9], 0, 0, n, k, 0, combos);
        return combos;
    }

    private static void helper(boolean[] bitmask,
                               int numSet,
                               int sum,
                               int targetSum,
                               int targetLength,
                               int cursor,
                               List<List<Integer>> combos) {
        // We overshot. Stop looking here,
        if (sum > targetSum) return;
        // We have a solution. Adding more to the sum makes no sense. Stop looking.
        if (numSet == targetLength && sum == targetSum) {
            List<Integer> combo = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (bitmask[i]) combo.add(i + 1);
            }
            combos.add(combo);
            return;
        }
        // If we haven'y consumed k digits yet, try adding each of the remaining
        // digits in turn.
        if (numSet < targetLength) {
            for (int i = cursor; i < 9; i++) {
                bitmask[i] = true;
                helper(bitmask, numSet + 1, sum + i + 1, targetSum, targetLength, i + 1, combos);
                bitmask[i] = false;
            }
        }
    }
}
