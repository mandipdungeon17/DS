package org.leetcode.dailyProblems;

import java.util.HashSet;
import java.util.Set;

public class MaximumSubarraySum {

    //Time Complexity: O(n^2)
//    public long maximumSubarraySum(int[] nums, int k) {
//        Set<Integer> set = new HashSet<>();
//        int sum = 0;
//        int max = 0;
//        for(int i=0; i<nums.length; i++){
//            for(int j=i; j< nums.length && j< i+k; j++){
//                set.add(nums[j]);
//                sum+=nums[j];
//            }
//            if(set.size() == k)
//                max = Math.max(sum, max);
//            sum=0;
//            set.clear();
//        }
//        return max;
//    }

    public long maximumSubarraySum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        long sum = 0;
        long max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= k && set.size() >=k) {
                sum -= nums[i - k];
                set.remove(nums[i - k]);
            }
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
                set.clear();
                sum = 0;
                set.add(nums[i]);
            }
            sum+=nums[i];
            if(set.size() == k) max = Math.max(sum, max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSubarraySum maximumSubarraySum = new MaximumSubarraySum();
        int[] nums = {1,1,1,7,8,9};
        int k = 3;
        System.out.println(maximumSubarraySum.maximumSubarraySum(nums, k));
    }
}
