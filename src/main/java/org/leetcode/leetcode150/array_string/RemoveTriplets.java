package org.leetcode.leetcode150.array_string;
//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/2055698945/?envType=study-plan-v2&envId=top-interview-150
public class RemoveTriplets {

    public int removeDuplicates(int[] nums) {
        int k=0;
        for(int i=0; i<nums.length; i++){
            if(k < 2 || nums[k-2] != nums[i]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    public static void main(String[] args) {
        RemoveTriplets removeTriplets = new RemoveTriplets();
        int[] nums = {0,0,1,1,1,1,2,3,3}; //{1,1,1,1,2,2,3};
        int k = removeTriplets.removeDuplicates(nums);
        System.out.println("Length of array after removing duplicates: " + k);
        System.out.print("Modified array: ");
        for(int i=0; i<k; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
