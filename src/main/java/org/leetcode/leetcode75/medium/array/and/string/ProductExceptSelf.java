package org.leetcode.leetcode75.medium.array.and.string;

import java.util.Arrays;

public class ProductExceptSelf {

    //Time complexity: O(n) and Space complexity: O(n)
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] ans = new int[n];
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = nums[0];
        suffix[n-1] = nums[n-1];
        for(int i=1; i<n; i++){
            prefix[i] = prefix[i-1]*nums[i];
        }
        for(int i=n-2; i>=0; i--){
            suffix[i] = suffix[i+1]*nums[i];
        }
        ans[0] = suffix[1];
        for(int i=1; i<n-1; i++){
            int sum = prefix[i-1]*suffix[i+1];
            ans[i] = sum;
        }
        ans[n-1] = prefix[n-2];
        return ans;
    }

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        int[] nums = {1,2,3,4};
        int[] ans = productExceptSelf.productExceptSelf(nums);
        System.out.println("Product Except Self: " + Arrays.toString(ans));
    }
}
