package org.leetcode.leetcode75.medium.sliding.window;

import java.util.Arrays;

public class LongestSubarrayOfMaxLength {

    // Time complexity: O(n) and Space complexity: O(1)
    static int[] longestSubarray(int[] arr, int sum){
        int maxLength = 0;
        int j = 0;
        long currentSum = 0;
        int startIndex = 0;
        for(int i=0; i< arr.length; i++){
            currentSum+=arr[i];

            while(j <= i && currentSum > sum) {
                currentSum -= arr[j];
                j++;
            }

            // After shrinking, check if current valid window is longer
            if(currentSum <= sum && i - j + 1 > maxLength) {
                maxLength = i - j + 1;
                startIndex = j;
            }

            // To print only the length of the maximum subarray
//            if(currentSum > sum){
//                currentSum-= arr[j];
//                j++;
//            }
        }
        // Return the longest subarray as an array
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = arr[startIndex + i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(longestSubarray(new int[]{24, 34, 54, 56, 12, 27, 23, 45, 21, 53}, 120)));
    }
}
