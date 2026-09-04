package org.leetcode.leetcode150.array_string;

import java.util.Arrays;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150
public class MaxProfit {

    // Brute Force. Time Complexity O(n^2), space complexity O(n)
//    public int maxProfit(int[] prices) {
//        int diff = 0;
//        for(int i=0; i<prices.length; i++){
//            for(int j=i+1; j<prices.length; j++){
//                if(prices[j] > prices[i]) {
//                    int diff1 = prices[j] - prices[i];
//                    if(diff1 > diff) {
//                        diff = diff1;
//                    }
//                }
//            }
//        }
//        return diff;
//    }

    // Optimized approach. Time Complexity O(n), space complexity O(1)
    public int maxProfit(int[] prices) {
        int mini = prices[0];
        int profit = 0;
        for(int i=1; i<prices.length; i++){
            int cost = prices[i] - mini;
            profit = Math.max(cost, profit);
            mini = Math.min(mini, prices[i]);
        }
        return profit;
    }

    public static void main(String[] args) {
        MaxProfit m = new MaxProfit();
        int[] arr = {7,1,5,3,6,4};
        int result = m.maxProfit(arr);
        System.out.println(result);
    }
}
