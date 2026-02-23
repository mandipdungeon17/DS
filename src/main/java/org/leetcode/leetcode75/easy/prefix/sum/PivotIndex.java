package org.leetcode.leetcode75.easy.prefix.sum;

public class PivotIndex {
    //Time complexity: O(n^2) and Space complexity: O(1). It took 314 ms.
//    public int pivotIndex(int[] nums) {
//        for(int i=0; i<nums.length; i++){
//            int sum = sumLeft(i, nums) - sumRight(i+1, nums);
//            if(sum == 0) return i;
//        }
//        return -1;
//    }
//
//    //Time complexity: O(n) and Space complexity: O(1).
//    public int sumLeft(int i, int[] nums){
//        int sum = 0;
//        for(int j=0; j<i; j++){
//            sum+=nums[j];
//        }
//        return sum;
//    }
//
//    //Time complexity: O(n) and Space complexity: O(1).
//    public int sumRight(int i, int[] nums){
//        int sum = 0;
//        for(int j=i; j<nums.length; j++){
//            sum+=nums[j];
//        }
//        return sum;
//    }

    public int pivotIndex(int[] nums){
        int leftSum = 0; int rightSum = 0;
        for(int i=1; i<nums.length; i++){
            rightSum+=nums[i];
        }
        if(leftSum == rightSum) return 0;
        for(int i=1; i<nums.length;i++){
            leftSum+=nums[i-1];
            rightSum-=nums[i];
            if(leftSum == rightSum) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex pivotIndex = new PivotIndex();
        int ans = pivotIndex.pivotIndex(new int[]{1,7,3,6,5,6});
        System.out.println("Pivot Index: " + ans);
    }
}
