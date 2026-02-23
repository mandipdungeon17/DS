package org.leetcode.leetcode75.medium.sliding.window;

public class LongestSubarray {
    //Time complexity: O(n) and Space complexity: O(1)
//    public int longestSubarray(int[] nums) {
//        int count = 0;
//        int max = 0;
//        boolean zero = false;
//        int index = 0;
//        for(int i=0; i<nums.length-1;i++){
//            if(nums[i] == 1){
//                count++;
//            }
//            else if(nums[i] == 0 && nums[i+1] == 0){
//                max = Math.max(count, max);
//                i=i+1;
//                count = 0;
//            }
//            else if(nums[i] == 0 && count >0){
//                if(zero){
//                    max = Math.max(count, max);
//                    i=index;
//                    count = 0;
//                    zero = false;
//                }
//                else {
//                    zero = true;
//                    index = i;
//                }
//            }
//        }
//        if(count == nums.length-1) return count;
//        if(count >= max){
//            return nums[nums.length-1] ==1 ? count+1 : count;
//        }
//        return max;
//    }

    //Time complexity: O(n) and Space complexity: O(1)
    public int longestSubarray(int[] nums) {
        int postCount = 0;
        int preCount = 0;
        int max = 0;
        int total;
        for (int num : nums) {
            if (num == 0) {
                total = preCount + postCount;
                max = Math.max(max, total);
                preCount = postCount;
                postCount = 0;
            } else postCount++;
        }
        total = preCount + postCount;
        max = Math.max(max, total);
        return max == nums.length ? max - 1:max;
    }

    public static void main(String[] args) {
        LongestSubarray longestSubarray = new LongestSubarray();
//        int[] nums = {0,1,1,1,0,0,1,1,1,0,1};
        int[] nums = {0,1,1,1,0,1,1,1,0,1,1,1,1};
//        int[] nums = {0,1,1,1,0,1,1,0,1};
//        int[] nums = {1,1,1,1};
        int ans = longestSubarray.longestSubarray(nums);
        System.out.println("Longest Subarray: " + ans);
    }
}
