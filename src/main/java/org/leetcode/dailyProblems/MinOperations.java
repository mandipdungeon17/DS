package org.leetcode.dailyProblems;

//https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/submissions/1579198604/?envType=daily-question&envId=2025-03-19
//Time complexity O(n) and space complexity O(1). It took 6ms to run on leetcode
public class MinOperations {

//    public static int minOperations(int[] nums) {
//        int count = 0;
//        for(int i=0; i<nums.length; i++){
//            if(nums[i] == 0){
//                for(int j=i; j<i+3; j++){
//                    if(j>=nums.length) return -1;
//                    if(nums[j] == 0) nums[j] = 1;
//                    else nums[j] = 0;
//                }
//                count++;
//            }
//        }
//        for (int num : nums) {
//            if (num == 0) return -1;
//        }
//        return count;
//    }

    public static int minOperations(int[] nums) {
        int count = 0;
        for(int i=0; i<nums.length-2; i++){
            if(nums[i] == 0){
                nums[i] = 1;
                nums[i+1] = 1 - nums[i+1];
                nums[i+2] = 1 - nums[i+2];
                count++;
            }
        }
        if (nums[nums.length-2] == 0 || nums[nums.length-1] == 0) return -1;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new int[] {0,1,1,1,0,0}));
        System.out.println(minOperations(new int[] {0,1,1,1}));
    }
}
