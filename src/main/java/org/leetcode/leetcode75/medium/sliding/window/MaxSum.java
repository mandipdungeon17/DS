package org.leetcode.leetcode75.medium.sliding.window;

// Given an array of integers and an integer k,
// find the maximum sum of any contiguous subarray of size k.
public class MaxSum {

    static long maxSum(int[] arr, int k){
        long maxSum =Integer.MIN_VALUE;
        long sum = 0;
        int j=0;
        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
            if(i >= k-1){
                maxSum = Math.max(maxSum, sum);
                sum-=arr[j];
                j++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{-1, 1, 2, -2, 3, 4, 9, -2, 5, -3}, 4));
    }
}
