package org.leetcode.leetcode150.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum/?envType=study-plan-v2&envId=top-interview-150
public class TwoSum {

    //Time Complexity O(n^2) and Space Complexity O(1). It took 45ms.
//    public int[] twoSum(int[] nums, int target) {
//        for(int i=0; i<nums.length; i++){
//            int sum;
//            for(int j=i+1; j<nums.length; j++){
//                sum = nums[i] + nums[j];
//                if(sum == target){
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[2];
//    }

    //Time Complexity O(n) and Space Complexity O(n). It took 2ms.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int num = target - nums[i];
            if(mp.containsKey(num)){
                return new int[]{mp.get(num), i};
            }
            mp.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        System.out.println(Arrays.toString(ts.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
