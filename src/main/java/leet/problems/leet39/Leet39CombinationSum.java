package leet.problems.leet39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet39CombinationSum {
    private static final int[][] EMPTY_ARRAY = new int[0][0];

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int numCoins = candidates.length;
        int[][] ways = new int[numCoins + 1][target + 1];

        for (int i = 0; i <= numCoins; i++) {
            ways[i][0] = 1;
        }

        // Build the table of counts of combinations. Rows are coins in ascending order.
        // Columns are subamounts from 0 to target. Each cell is "how many ways are there to
        // combine all but the last r coins into subamount."
        for (int coin = numCoins - 1; coin >= 0; coin--) {
            // Coins that are larger than target should not be considered at all.
            if (candidates[coin] <= target) {
                for (int sum = 1; sum < candidates[coin]; sum++) {
                    ways[coin][sum] = ways[coin + 1][sum];
                }
            }
            for (int sum = candidates[coin]; sum <= target; sum++) {
                ways[coin][sum] = ways[coin + 1][sum] + ways[coin][sum - candidates[coin]];
            }
        }

        // Crawl the table we have built to assemble the actual combinations.
        int[][] combos = traverse(ways, candidates, 0, target, numCoins + 1, new int[0]);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < combos.length; i++) {
            int[] combo = combos[i];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < combo.length; j++) {
                list.add(combo[j]);
            }
            ret.add(list);
        }
        return ret;
    }

    // The table of ways to combine coins is arranged with subamounts 0 to target in columns,
    // and coins in rows. The last row is "no coins" and is all zeroes. First column is subamount 0
    // and is all ones.
    private static int[][] traverse(int[][] ways,
                                    int[] coins,
                                    int row,
                                    int col,
                                    int nrows,
                                    int[] prefix) {
        // Reached left edge of the table having accumulated the full target amount. The prefix is
        // now a full, valid combination, so let's return it.
        if (col == 0) {
            return new int[][] { prefix };
        }
        // Reached the bottom of the table. No more coins to consider.
        if (row == nrows - 1) {
            return EMPTY_ARRAY;
        }
        // No valid combination continues with this coin, so consider coins below.
        if (ways[row][col] == 0) {
            return traverse(ways, coins, row + 1, col, nrows, prefix);
        }
        // Normal case: some combinations continue with this coin and some do not. Return the union
        // of both.
        int[] newPrefix = Arrays.copyOf(prefix, prefix.length + 1);
        newPrefix[prefix.length] = coins[row];
        int[][] goLeft = traverse(ways, coins, row, col - coins[row], nrows, newPrefix);
        int[][] goDown = traverse(ways, coins, row + 1, col, nrows, prefix);

        int[][] ret = new int[goLeft.length + goDown.length][];
        for (int i = 0; i < goLeft.length; i++) {
            ret[i] = goLeft[i];
        }
        for (int i = 0; i < goDown.length; i++) {
            ret[i + goLeft.length] = goDown[i];
        }
        return ret;
    }
}
