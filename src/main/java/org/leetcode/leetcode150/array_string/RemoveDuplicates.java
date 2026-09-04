package org.leetcode.leetcode150.array_string;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        int len = nums.length;

        if(len == 1) return 1;

        while(j< nums.length) {
            if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];

            }
            j++;
        }
        return i+1;
    }

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        int length = rd.removeDuplicates(arr);
        System.out.println("Length of array after removing duplicates: " + length);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
