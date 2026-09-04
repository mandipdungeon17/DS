package org.leetcode.leetcode150.array_string;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
public class MaxProfitTwice {

    //Time Complexity O(n) and Space Complexity O(1)
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        MaxProfitTwice m = new MaxProfitTwice();
        int[] arr = {7,1,5,3,6,4};
        int result = m.maxProfit(arr);
        System.out.println(result);
    }
}
