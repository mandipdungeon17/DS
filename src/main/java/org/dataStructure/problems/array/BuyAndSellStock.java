package org.dataStructure.problems.array;

public class BuyAndSellStock {
    public static int maxProfit(int[] prices) {

//        int diff =0;
//
//        for(int i=0; i<prices.length; i++){
//            for(int j=i+1; j<prices.length; j++){
//                if(diff < (prices[j] - prices[i])){
//                    diff = (prices[j] - prices[i]);
//                }
//            }
//        }
//        return diff;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }
    public static void main(String[] args){
        int result = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.print("Result : " + result);

    }
}
