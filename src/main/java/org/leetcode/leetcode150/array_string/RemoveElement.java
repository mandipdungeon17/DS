package org.leetcode.leetcode150.array_string;
//https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
public class RemoveElement {

    //Time Complexity O(n) and Space Complexity O(1)
    public int removeElement(int[] nums, int val) {
        int i = 0, j = 0;
        int len = nums.length;

        while(i<len && j<len) {
            if(nums[i] == val && nums[j] != val && i!=j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            } else if( nums[j] != val) {
                i++;
            }
            j++;
        }

        return i;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] nums = {3,2,2,3};
        int val = 3;
        int len = removeElement.removeElement(nums, val);
        System.out.println("Length of the array after removing the element: " + len);
        System.out.print("Modified array: ");
        for(int i=0; i<len; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
