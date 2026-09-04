package org.leetcode.leetcode150.array_string;

import java.util.Arrays;

/*
238. Product of Array Except Self
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 */
//https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150
public class ProductExceptSelf {

    //Time complexity O(n) and space complexity O(n). It took 2ms.
    public int[] productExceptSelf(int[] nums) {
        int multi = 1;
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            multi*=nums[i];
            prefix[i] = multi;
        }
        multi = 1;
        for(int j=nums.length-1; j>=0; j--){
            multi*=nums[j];
            suffix[j] = multi;
        }

        nums[0] = suffix[1];
        nums[nums.length-1] = prefix[prefix.length-2];
        for(int i=1; i<nums.length-1; i++){
            nums[i] = prefix[i-1]*suffix[i+1];
        }
        return nums;
    }

    public static void main(String[] args) {
        ProductExceptSelf p = new ProductExceptSelf();
        int[] arr = {1,2,3,4};
        System.out.println(Arrays.toString(p.productExceptSelf(arr)));
    }
}
