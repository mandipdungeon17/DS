package org.leetcode.leetcode75.easy.two.pointers;

import java.util.Arrays;

public class MoveZeroes {
    //Time complexity: O(n) and Space complexity: O(1). It took 4 ms.
//    public void moveZeroes(int[] nums) {
//        int i=0;
//        int j=0;
//        if(nums.length == 1) return;
//        while(i<nums.length && j<nums.length){
//            if(nums[i] == 0 && nums[j] == 0){
//                j++;
//            }
//            else if(nums[j] != 0 && nums[i] == 0){
//                nums[i] = nums[j];
//                nums[j] = 0;
//                i++;
//            }
//            else{
//                i++;
//                j++;
//            }
//        }
//    }

    //Time complexity: O(n) and Space complexity: O(1). It took 2 ms.
//    public void moveZeroes(int[] nums) {
//        int i=0;
//        int j=0;
//        while(j<nums.length){
//            if(nums[j] != 0){
//                nums[i] = nums[j];
//                if(i != j){
//                    nums[j] = 0;
//                }
//                i++;
//            }
//            j++;
//        }
//    }

    //Time complexity: O(n) and Space complexity: O(1). It took 2 ms.
    public void moveZeroes(int[] nums) {
        int index = -1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                nums[++index] = nums[i];
            }
        }
        for(int i=index+1; i<nums.length; i++){
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] nums = {0,1,0,3,12};
        moveZeroes.moveZeroes(nums);
        System.out.println("Move Zeroes: " + Arrays.toString(nums));
    }
}
