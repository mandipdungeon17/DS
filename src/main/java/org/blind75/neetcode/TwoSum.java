package org.blind75.neetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
//    public int[] twoSum(int[] nums, int target) {
//        int[] list = new int[2];
//        for(int i=0; i<nums.length; i++){
//            for(int j=i+1; j<nums.length; j++){
//                if(nums[i] + nums[j] == target){
//                    list[0] = i;
//                    list[1] = j;
//                }
//            }
//        }
//        return list;
//    }

    public int[] twoSum(int[] nums, int target) {
        int[] list = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.get(target - nums[i]) == null){
                map.put(nums[i], i);
            }
            else {
                list[0] = map.get(target - nums[i]);
                list[1] = i;
            }

        }
        return list;
    }
}
