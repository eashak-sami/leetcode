package slidingwindow;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int buyingPrice = Integer.MAX_VALUE;
        int profit = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < buyingPrice) {
                buyingPrice = prices[i];
            }
            profit = Math.max(profit, prices[i] - buyingPrice);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        System.out.println(maxProfit(arr));
    }
}
