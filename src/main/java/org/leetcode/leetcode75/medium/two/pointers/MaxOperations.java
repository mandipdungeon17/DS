package org.leetcode.leetcode75.medium.two.pointers;

import java.util.Arrays;

public class MaxOperations {

    //Time complexity: O(n^2) and Space complexity: O(1). It took 1163 ms.
//    public int maxOperations(int[] nums, int k) {
//        int count=0;
//        if(nums.length == 1){
//            if(nums[0] == k) return 1;
//            return 0;
//        }
//        for(int i=0; i<nums.length; i++){
//            if(nums[i] == -1 || nums[i] >= k) continue;
//            for(int j=i+1; j<nums.length;j++){
//                if((nums[i] + nums[j]) == k ){
//                    nums[i] = -1;
//                    nums[j] = -1;
//                    count++;
//                    break;
//                }
//            }
//        }
//        return count;
//    }

    //Time complexity: O(nlogn) and Space complexity: O(1). It took 18 ms.
    public int maxOperations(int[] nums, int k) {
        if(nums.length == 1){
            if(nums[0] == k) return 1;
            return 0;
        }
        Arrays.sort(nums);
        int start=0;
        int end = nums.length-1;
        int count=0;
        while(start<end){
            if((nums[start]+nums[end]) == k){
                start++;
                end--;
                count++;
            }
            else if((nums[start]+nums[end]) < k){
                start++;
            }
            else{
                end--;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        MaxOperations maxOperations = new MaxOperations();
        int[] nums = {2,2,2,3,1,1,4,1}; //{1,1,1,2,2,2,3,4,}
        int k = 4;
        int ans = maxOperations.maxOperations(nums, k);
        System.out.println("Max Operations: " + ans);
    }
}
