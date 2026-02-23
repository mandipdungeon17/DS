package org.leetcode.dailyProblems;

import java.util.Arrays;

//https://leetcode.com/problems/divide-array-into-equal-pairs/submissions/1576489627/?envType=daily-question&envId=2025-03-17
//Time complexity O(nlogn) and space complexity O(1)
public class DivideArray {

    public static boolean divideArray(int[] nums) {
        Arrays.sort(nums); //O(nlogn) -> quick sort
        for(int i=1; i<nums.length; i+=2){
            if(nums[i] != nums[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(divideArray(new int[]{3,2,3,2,2,2}));
        System.out.println(divideArray(new int[]{1,2,3,4}));
    }
}
