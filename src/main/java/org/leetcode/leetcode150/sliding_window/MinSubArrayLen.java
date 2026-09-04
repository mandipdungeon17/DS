package org.leetcode.leetcode150.sliding_window;

//https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
public class MinSubArrayLen {
    //Time Complexity O(n) and Space Complexity O(1). It took 1ms.
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int j = 0;
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
            while(sum >= target){
                min = Math.min(i-j+1, min);
                sum-=nums[j];
                j++;
            }
        }
        return min == Integer.MAX_VALUE ? 0:min;
    }

    public static void main(String[] args) {
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen.minSubArrayLen(target, nums));
    }
}
