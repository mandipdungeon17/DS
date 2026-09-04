package org.leetcode.leetcode150.array_string;

import java.util.Arrays;
//https://leetcode.com/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150
public class RotateArray {

    //Time limit exceeded. Time complexity O(n^2)
//    public void rotate(int[] nums, int k) {
//        for(int i=0; i<k; i++){
//            for(int j=0; j<nums.length; j++){
//                int tmp = nums[j];
//                nums[j] = nums[nums.length-1];
//                nums[nums.length-1] = tmp;
//            }
//        }
//    }

    // Accepted but need O(1) space complexity. Time complexity O(n). It took 1ms
//    public void rotate(int[] nums, int k) {
//        if(k>nums.length) {
//            k = k%nums.length;
//        }
//        int[] arr =  new int[nums.length];
//        int j=0;
//        for(int i=nums.length-k; i<nums.length; i++){
//            arr[j++]=nums[i];
//        }
//
//        for(int i=0; i< nums.length-k; i++){
//            arr[j++]=nums[i];
//        }
//        System.arraycopy(arr, 0, nums, 0, nums.length);
//    }

    // Accepted with O(n) Time complexity and O(1) space complexity. But it took 4ms.
//    public void rotate(int[] nums, int k) {
//        if(k>=nums.length) {
//            k = k%nums.length;
//        }
//        int j=0;
//        for(int i=nums.length-k; i< (nums.length-k) + k/2; i++){
//            int temp=nums[i];
//            nums[i]=nums[nums.length - j - 1];
//            nums[nums.length - j - 1]=temp;
//            j++;
//        }
//
//        j=0;
//        for(int i=0; i< (nums.length-k)/2; i++){
//            int temp=nums[i];
//            nums[i]=nums[nums.length - k - j - 1];
//            nums[nums.length - k - j - 1]=temp;
//            j++;
//        }
//
//        j=0;
//        for(int i=0; i< nums.length/2; i++){
//            int temp=nums[i];
//            nums[i]=nums[nums.length - j - 1];
//            nums[nums.length - j - 1]=temp;
//            j++;
//        }
//    }

    // Accepted with O(n) Time complexity and O(1) space complexity. It took 0ms.
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);

    }

    public void reverse(int[] nums, int i, int n) {
        while(i<n){
            int temp = nums[i];
            nums[i] = nums[n];
            nums[n] = temp;
            i++;
            n--;
        }
    }
    public static void main(String[] args) {
        RotateArray ra = new RotateArray();
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//         int[] arr = {-1,-100,3,99};
//        int[] arr = {1,2};
        int[] arr = {2147483647,-2147483648,33,219,0};
        ra.rotate( arr, 4);
        System.out.println(Arrays.toString(arr));
    }
}
