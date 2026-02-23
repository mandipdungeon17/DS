package org.dataStructure.problems.array;

import java.util.Arrays;

public class TwoSumTarget {
    public static int[] twoSum(int[] nums, int target) {


        for(int i=0; i<nums.length; i++){
            for(int j=1; j<nums.length; j++){
                if((nums[i] + nums[j]) == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    public static void main(String[] args){
        int[] result = new int[2];
        result = twoSum(new int[]{2,7,11,15}, 9);
        System.out.print("Result : " + Arrays.toString(result));

    }
}
