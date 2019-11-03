package leet.problems.leet40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet40CombinationSumIi {
    private static final int[][] EMPTY_ARRAY = new int[0][0];

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int numCoins = candidates.length;
        Arrays.sort(candidates);

        // Count each coin to facilitate the recursive structure of the combination table.
        // At each row R, we want to jump by the *remaining* count of the Rth coin.
        // Example: [1, 1, 1, 3, 3] -> [[1, 3], [1, 2], [1, 1], [3, 2], [3, 1]].
        int[][] coinsAndCounts = new int[numCoins][2];
        coinsAndCounts[numCoins - 1][0] = candidates[numCoins - 1];
        coinsAndCounts[numCoins - 1][1] = 1;
        for (int i = numCoins - 2; i >= 0; i--) {
            coinsAndCounts[i][0] = candidates[i];
            coinsAndCounts[i][1] = coinsAndCounts[i + 1][0] == candidates[i]
                ? coinsAndCounts[i + 1][1] + 1
                : 1;
        }

        // Same table of combinations as in the simplest case. Each coin, including duplicates,
        // gets a row.
        int[][] ways = new int[numCoins + 1][target + 1];
        for (int i = 0; i <= numCoins; i++) {
            ways[i][0] = 1;
        }

        // The recurrence is more general than what it would be with a single unique coin.
        // Given a prefix [a, a, b, c, ...], either go down and consider [a, b, c, ...],
        // or consume the entire duplicated prefix [a, a], go down by two, and left by 2a.
        for (int c = numCoins - 1; c >= 0; c--) {
            int coin = coinsAndCounts[c][0];
            int count = coinsAndCounts[c][1];
            // Don't try to consume an entire collection of duplicate coins if the subamount
            // isn't large enough to be made up of that many copies of that coin.
            for (int sum = 1; sum < Math.min(target + 1, coin * count); sum++) {
                ways[c][sum] = ways[c + 1][sum];
            }
            // Consume `count` of the current coin value. Loop bounds make sure this isn't too much.
            for (int sum = coin * count; sum <= target; sum++) {
                ways[c][sum] = ways[c + 1][sum] + ways[c + count][sum - coin * count];
            }
        }

        // Assemble combinations.
        int[][] combos = traverse(ways, coinsAndCounts, 0, target, numCoins + 1, new int[0]);

        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < combos.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < combos[i].length; j++) {
                list.add(combos[i][j]);
            }
            ret.add(list);
        }
        return ret;
    }

    private static int[][] traverse(int[][] ways, int[][] coinsAndCounts, int r, int c, int nrow, int[] prefix) {
        if (c == 0) {
            return new int[][] { prefix };
        }
        if (r == nrow - 1) {
            return EMPTY_ARRAY;
        }

        int[] newPrefix = Arrays.copyOf(prefix, prefix.length + coinsAndCounts[r][1]);
        for (int i = prefix.length; i < newPrefix.length; i++) {
            newPrefix[i] = coinsAndCounts[r][0];
        }

        // Union of the combinations with one fewer duplicates and the combination with
        // none of the duplicates.
        int jumpLeftToColumn = c - coinsAndCounts[r][1] * coinsAndCounts[r][0];
        if (jumpLeftToColumn >= 0) {
            int[][] below = traverse(
                ways,
                coinsAndCounts,
                r + 1,
                c,
                nrow,
                prefix);
            int[][] left = traverse(
                ways,
                coinsAndCounts,
                r + coinsAndCounts[r][1],
                jumpLeftToColumn,
                nrow,
                newPrefix);

            int[][] ret = new int[left.length + below.length][];
            for (int i = 0; i < left.length; i++) {
                ret[i] = left[i];
            }
            for (int i = 0; i < below.length; i++) {
                ret[i + left.length] = below[i];
            }

            return ret;
        } else {
            return traverse(ways, coinsAndCounts, r + 1, c, nrow, prefix);
        }
    }
}
