package org.leetcode.leetcode150.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/submissions/2074511979/?envType=study-plan-v2&envId=top-interview-150
public class ThreeSum {
    //Time Complexity O(n^2) and Space Complexity O(1). It took 34ms.
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int indexVal1;
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            int j=i+1;
            int k=nums.length-1;
            while(j<k){
                int sum=nums[i]+nums[j]+nums[k];
                if(sum>0){
                    indexVal1=nums[k];
                    do {
                        k--;
                    } while (indexVal1 == nums[k] && j < k);
                } else if(sum<0){
                    indexVal1=nums[j];
                    do {
                        j++;
                    } while (indexVal1 == nums[j] && j < k);
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    do j++;
                    while (j < k && nums[j] == nums[j - 1]);
                    do k--;
                    while (j < k && nums[k] == nums[k + 1]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum.threeSum(new int[]{0,0,0}));
    }
}
