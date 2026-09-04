package org.leetcode.leetcode150.intervals;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(1). It took 1ms. I used a for loop to find the ranges and add them to the list.
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();

        for(int i=0;i<nums.length; i++){
            int start = nums[i];
            while(i+1<nums.length && nums[i]+1 == nums[i+1]){
                i++;
            }
            int end = nums[i];
            if(start != end) list.add(start + "->" + end);
            else list.add(String.valueOf(nums[i]));
        }
        return list;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        int[] nums = {0,1,2,4,5,7};
        List<String> list = summaryRanges.summaryRanges(nums);
        System.out.println(list);
    }
}
