package org.leetcode.leetcode150.array_string;

import java.util.Arrays;
//https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
public class MergeSortedArray {

    // Time Complexity : O(n) & Space Complexity: O(n)
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int[] arr = new int[m+n];
//
//        int i=0, j=0, k=0;
//
//        while(i<m && j<n) {
//            if(nums1[i] <= nums2[j]) {
//                arr[k] = nums1[i];
//                i++;
//            } else {
//                arr[k] = nums2[j];
//                j++;
//            }
//            k++;
//        }
//
//        if(i<m) {
//            while(i<m){
//                arr[k] = nums1[i];
//                k++;
//                i++;
//            }
//        } else if(j<n){
//            while(j<n){
//                arr[k] = nums2[j];
//                k++;
//                j++;
//            }
//        }
//
////        for(int a =0; a<arr.length; a++) {
////            nums1[a] = arr[a];
////        }
//        System.arraycopy(arr, 0, nums1, 0, arr.length);
//    }

// Time Complexity : O(n) & Space Complexity: O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for nums1
        int j = n - 1; // Pointer for nums2
        int k = m + n - 1;

        while(i>=0 && j>=0) {
            if(nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while(j>=0) {
            nums1[k--] = nums2[j--];
        }
    }
    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        MergeSortedArray merger = new MergeSortedArray();
        merger.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
