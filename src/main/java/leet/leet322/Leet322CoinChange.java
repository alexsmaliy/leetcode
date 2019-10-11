package leet.leet322;

import java.util.HashMap;
import java.util.Map;

// You are given coins of different denominations and
// a total amount of money amount. Write a function to
// compute the fewest number of coins that you need to
// make up that amount. If that amount of money cannot
// be made up by any combination of the coins, return
// -1. You may assume that you have an infinite number
// of each kind of coin.
public class Leet322CoinChange {
    static class Solution {

        // The bottom-up solution to the perfect knapsack is a lot
        // like bottom-up Fibonacci, except instead of a particular
        // pair of predecessor, we pick the best available ones.
        // On the way to computing the optimal number of coins for
        // the given amount, we compute the optimal number of coins
        // for each integer n up to the amount. If n is equal to the
        // value of one of the coins, the optimum for n is clearly 1
        // (namely, 1 of that coin). Thus, we preseed the optimal
        // solutions to each of those amounts. Then, as we iterate
        // from 1 to amount, we check how many coins it took to make
        // optimal change for each remainder `n - coins[i]` and choose
        // the best. For some values, there is no way to make change
        // with the available coins, so we leave those amounts set
        // to the sentinel value, 0.
        public int coinChangeBottomUp(int[] coins, int amount) {
            // Input validation nonsense.
            if (coins == null && coins.length == 0) return -1;
            if (amount == 0) return 0;

            // Array of intermediate values 0 to amount, whose values
            // we will compute. (Zero is unnecessary, but makes OBOEs
            // less likely.)
            int[] memo = new int[amount + 1];

            int numCoins = coins.length;

            // Seed the intermediate values with amounts for which
            // the optimal solution is a single coin.
            for (int coinIndex = 0; coinIndex < numCoins; coinIndex++) {
                // The check is a precaution against coins whose value
                // is too large to use them to make change.
                if (coins[coinIndex] <= amount) {
                    int ithCoin = coins[coinIndex];
                    memo[ithCoin] = 1;
                }
            }

            // Compute solutions for each amount between 1 and the one
            // we care about.
            for (int subamount = 1; subamount <= amount; subamount++) {
                // Nothing left to do for values we seeded earlier.
                if (memo[subamount] == 1) continue;
                // Set the worst possible starting value as we look
                // for the optimum number of coins for the current amount.
                int optimum = Integer.MAX_VALUE;
                // Take turns subtracting each of the available coins from
                // the currently selected amount.
                for (int coinIndex = 0; coinIndex < numCoins; coinIndex++) {
                    int ithCoin = coins[coinIndex];
                    int remainder = subamount - ithCoin;
                    // If the selected coin is larger than the remaining amount,
                    // it cannot be a part of the solution.
                    if (remainder >= 0) {
                        int lookup = memo[remainder];
                        // Assuming positive target amounts and non-negative coins,
                        // the remainder is always smaller than the current amount,
                        // and we can reasonably check if we computed it in an earlier
                        // iteration. If lookup is still set to the sentinel value,
                        // we were unable to make change for it, and it is not part
                        // of the solution.
                        if (lookup != 0) {
                            // Keep choosing the better option.
                            optimum = lookup < optimum ? lookup : optimum;
                        }
                    }
                }
                // If we weren't able to make change for this amount at all, keep
                // the value set at 0. Otherwise, the optimum number of coins to
                // make change for the current amount is whatever it took to make
                // change for the most favorable remainder, plus one more coin.
                memo[subamount] = optimum == Integer.MAX_VALUE ? 0 : optimum + 1;
            }

            int answer = memo[amount];

            // Signal value -1 means "unable to make change."
            return answer == 0 ? -1 : answer;
        }

        // The top-down recursive solution is slower, but easier to reason about.
        // The optimum number of coins to make change is one more than the optimum
        // number of coins to make change for some remainder `amount - coins[i]`.
        // We recursively check every possible remainder and choose the best one.
        // We avoid computing redundant partial solutions by memoizing values in
        // a hashmap.
        public int coinChangeTopDown(int[] coins, int amount) {
            // Input validation nonsense.
            if (coins == null && coins.length == 0) return -1;
            if (amount == 0) return 0;
            // Initialize the memoizer and run.
            Map<Integer, Integer> memo = new HashMap<>();
            int optimum = coinChange(coins, amount, memo);
            return optimum == Integer.MAX_VALUE ? -1 : optimum;
        }

        private static int coinChange(int[] coins, int amount, Map<Integer, Integer> memo) {
            // Retrieve a stored value, if we have already computed it before.
            if (memo.containsKey(amount)) {
                return memo.get(amount);
            }
            else {
                // Stop recursion: we found a solution.
                if (amount == 0) return 0;

                // Stop recursion: the preceding sequence of coins was not a solution.
                if (amount < 0) {
                    return Integer.MAX_VALUE;
                }

                // Try making change for each possible value of `amount - coins[i]`.
                // Choose the best candidate.
                int childOptimum = Integer.MAX_VALUE;
                for (int i = 0; i < coins.length; i++) {
                    int candidate = coinChange(coins, amount - coins[i], memo);
                    childOptimum = candidate < childOptimum ? candidate : childOptimum;
                }

                // Memoize the best partial solution. (We have to avoid integer
                // overflow, because we are using MAX_VALUE as the sentinel value.
                int optimum = childOptimum == Integer.MAX_VALUE ? childOptimum : childOptimum + 1;
                memo.put(amount, optimum);
                return optimum;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChangeBottomUp(new int[] {1,2,5}, 111));
    }
}
