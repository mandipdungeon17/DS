package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/partition-array-according-to-given-pivot/description/?envType=daily-question&envId=2025-03-03
public class PivotArray {
    //Time complexity O(n) and space complexity O(n). It took 13ms to run on leetcode
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> greaterList = new ArrayList<>();
        List<Integer> lesserList = new ArrayList<>();
        int pivotCount = 0;

        for(int i : nums){
            if(i < pivot){
                lesserList.add(i);
            } else if (i > pivot) {
                greaterList.add(i);
            }
            else pivotCount++;
        }
        int[] result = new int[nums.length];
        int i = 0;
        int n = lesserList.size() + pivotCount;
        while(i < nums.length ){
           if(i < lesserList.size()) {
               result[i] = lesserList.get(i);
           } else if (i >= n) {
               result[i] = greaterList.get(i-n);
           }
           else{
               result[i] = pivot;
           }
           i++;
        }
        return result;
    }

    public static void main(String[] args) {
        PivotArray pa = new PivotArray();
        int[] nums = {9,12,5,10,14,3,10};
        int pivot = 10;
        int[] result = pa.pivotArray(nums, pivot);
        for (int j : result) {
            System.out.print(j + " ");
        }
    }
}
