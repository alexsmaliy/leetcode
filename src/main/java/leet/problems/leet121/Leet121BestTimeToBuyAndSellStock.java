package leet.problems.leet121;

public class Leet121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) min = prices[i];
            if (profit < prices[i] - min) profit = prices[i] - min;
        }
        return profit < 0 ? 0 : profit;
    }
}
