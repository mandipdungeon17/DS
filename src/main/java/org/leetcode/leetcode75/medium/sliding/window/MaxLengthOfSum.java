package org.leetcode.leetcode75.medium.sliding.window;

public class MaxLengthOfSum {
    /**
     * This method finds the maximum length of a contiguous subarray whose sum is less than or equal to a given value.
     *
     * @param arr The input array of integers.
     * @param sum The maximum allowed sum for the subarray.
     * @return The length of the longest subarray with a sum less than or equal to the specified value.
     */
    // Time complexity: O(n) and Space complexity: O(1)
    static int maxLength(int[] arr, int sum){
        int maxLength = 0;
        int j = 0;
        long currentSum = 0;
        for(int i=0; i< arr.length; i++){
            currentSum+=arr[i];

            // Keep shrinking window from left until sum is valid
            // Below while loop is required if we want to print the subarray of maximum length
//            while(j <= i && currentSum > sum) {
//                currentSum-=arr[j];
//                j++;
//            }

            // To print only the length of the maximum subarray
            if(currentSum > sum){
                currentSum-= arr[j];
                j++;
            }

            if(currentSum <= sum){
                maxLength = Math.max(maxLength, i+1-j);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{24,34,54,56,12,27,23,45,21,53}, 12));
    }
}
