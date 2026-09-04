package org.leetcode.leetcode150.two_pointers;

import java.util.Arrays;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/?envType=study-plan-v2&envId=top-interview-150
public class TwoSum {

    //Time Complexity O(n) and Space Complexity O(1). It took 2ms.
    public int[] twoSum(int[] numbers, int target) {
        int i=0;
        int j=numbers.length-1;

        while(i<j){
            int sum = numbers[j] + numbers[i];
            if(sum > target) j--;
            else if(sum < target) i++;
            else {
                return new int[]{i+1, j+1};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{2,3,4}, 6)));
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{-1,0}, -1)));
    }
}
