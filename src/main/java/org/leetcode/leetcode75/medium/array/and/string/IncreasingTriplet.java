package org.leetcode.leetcode75.medium.array.and.string;

public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int count =0;
        int max = Integer.MIN_VALUE;
        int counter = 0;
        for(int i=0; i<nums.length; i++){
            if(max < nums[i]){
                max = nums[i];
                count++;
                if(count == 3) return true;
            }
            if(i == nums.length-1 && counter < nums.length){
                i = counter++;
                count = 0;
                max = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IncreasingTriplet increasingTriplet = new IncreasingTriplet();
        int[] nums = {1,5,0,4,1,3};
        boolean ans = increasingTriplet.increasingTriplet(nums);
        System.out.println("Increasing Triplet: " + ans);
    }
}
