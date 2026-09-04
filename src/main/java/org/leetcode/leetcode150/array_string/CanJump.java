package org.leetcode.leetcode150.array_string;

public class CanJump {

    // Test cases Failed
//    public boolean canJump(int[] nums) {
//
//        if(nums[0] == 0 && nums.length == 1) return true;
//
//        int i=nums[0]-1;
//        if(i == nums.length-1)
//            return true;
//
//        while(i<nums.length && i>=0){
//            if(i == nums.length-1)
//                return true;
//            if(nums[i] == 0) return false;
//
//            i+=nums[i];
//        }
//
//        return false;
//    }

    //Time Complexity O(n), Space Complexity O(1)
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for(int i=0; i<nums.length; i++){
            if(i > farthest) return false;
            farthest = Math.max(farthest, nums[i]+i);
            if(farthest >= nums.length-1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CanJump c = new CanJump();
        //int[] arr = {2,3,1,1,4};
//        int[] arr = {3,2,1,0,4};
        int[] arr = {1,2};
        boolean result = c.canJump(arr);
        System.out.println(result);
    }
}
