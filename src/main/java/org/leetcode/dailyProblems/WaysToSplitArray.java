package org.leetcode.dailyProblems;

//https://leetcode.com/problems/number-of-ways-to-split-array/submissions/1495965594/?envType=daily-question&envId=2025-01-03
public class WaysToSplitArray {

    //Time complexity O(n) and space complexity O(1). It took 2ms to execute.
    public int waysToSplitArray(int[] nums) {
        int count = 0;
        int totalSum = 0;
        int sum = 0;

        for(int i : nums)
            totalSum+=i;

        for(int i=0; i< nums.length-1;i++){
            sum+=nums[i];
            totalSum-=nums[i];
            System.out.println(sum + " " + totalSum);
            if(sum >= totalSum) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        WaysToSplitArray waysToSplitArray = new WaysToSplitArray();
        System.out.println(waysToSplitArray.waysToSplitArray(new int[]{10,4,-8,7}));
    }
}