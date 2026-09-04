package org.leetcode.leetcode150.array_string;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150
public class MajorityElement {

    //Time complexity O(nlogn) for sorted array, O(n) for unsorted array, Space complexity O(n)
//    public int majorityElement(int[] nums) {
//        Map<Integer, Integer> mp = new HashMap<>();
//
//        for (int num : nums) {
//            mp.put(num, mp.getOrDefault(num, 0) + 1);
//        }
//
//        for(Map.Entry<Integer, Integer> entry: mp.entrySet()){
//            int val  = entry.getValue();
//            if(val > nums.length / 2){
//                return entry.getKey();
//            }
//        }
//
//        return -1;
//    }

    //Time complexity O(n), space complexity O(1). Moore's Voting Algorithm
    public int majorityElement(int[] nums) {
        int count = 0;
        int element = 0;

        for (int num : nums) {
            if (count == 0) {
                element = num;
                count++;
            } else if (num == element)
                count++;
            else
                count--;
        }
        return element;
    }
    public static void main(String[] args) {

        MajorityElement majorityElement = new MajorityElement();
        int[] nums = {3,2,3};
        System.out.println(majorityElement.majorityElement(nums));
    }
}
