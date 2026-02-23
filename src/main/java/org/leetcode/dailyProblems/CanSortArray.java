package org.leetcode.dailyProblems;

import java.util.Arrays;
//https://leetcode.com/problems/find-if-array-can-be-sorted/submissions/1444741294/?envType=daily-question&envId=2024-11-06
public class CanSortArray {
    //Time complexity: O(n^2) and Space complexity: O(1). It took 4 ms to execute.
    public boolean canSortArray(int[] nums) {
        for(int i=0; i<nums.length; i++){
            for(int j=1; j< nums.length-i; j++){
                if(nums[j] < nums[j-1]){
                    if(checkSetBits(nums[j], nums[j-1])) {
                        int swap = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = swap;
                    }
                    else return false;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return true;
    }

    private boolean checkSetBits(int num, int num1) {
        int setBitsNum = Integer.bitCount(num);
        int setBitsNum1 = Integer.bitCount(num1);
        System.out.println(setBitsNum1 + " " + setBitsNum);
        return setBitsNum == setBitsNum1;
    }

    public static void main(String[] args) {
        CanSortArray canSortArray = new CanSortArray();
        boolean result = canSortArray.canSortArray(new int[]{8,4,2,30,15});
        System.out.println(result);
    }
}
