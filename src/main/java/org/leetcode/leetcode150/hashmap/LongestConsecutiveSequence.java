package org.leetcode.leetcode150.hashmap;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
public class LongestConsecutiveSequence {

    //Time Complexity O(nlogn) and Space Complexity O(1). It took 22ms.
//    public int longestConsecutive(int[] nums) {
//        if(nums.length == 0) return 0;
//        Arrays.sort(nums);
//        int max=0;
//        int count=1;
//        for(int i=1; i<nums.length; i++){
//            if(nums[i-1] == nums[i]) continue;
//            else if(nums[i-1]+1 == nums[i]) count++;
//            else{
//                max = Math.max(count, max);
//                count=1;
//            }
//        }
//        return Math.max(count, max);
//    }
    //Time Complexity O(n) and Space Complexity O(n). It took 29ms.
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            mp.put(nums[i], i);
        }
        int max = 1;
        for(int i: mp.keySet()){
            if(!mp.containsKey(i-1)){
                int j=i+1;
                int count=1;
                while(mp.containsKey(j)){
                    count++;
                    j++;
                }
                max = Math.max(count, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        System.out.println(lcs.longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(lcs.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println(lcs.longestConsecutive(new int[]{1,0,-1}));
    }
}
