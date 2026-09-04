package org.leetcode.leetcode150.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://leetcode.com/problems/contains-duplicate-ii/submissions/2077192562/?envType=study-plan-v2&envId=top-interview-150
public class ContainsNearbyDuplicate {
    //Time Complexity O(n) and Space Complexity O(n). It took 58ms.
//    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        Map<Integer, List<Integer>> mp = new HashMap<>();
//        for(int i=0; i<nums.length; i++){
//            List<Integer> l;
//            if(mp.containsKey(nums[i])){
//                l = mp.get(nums[i]);
//            } else{
//                l = new ArrayList<>();
//            }
//            l.add(i);
//            mp.put(nums[i], l);
//        }
//        for(List<Integer> l : mp.values()){
//            int size = l.size();
//            if (size == 2) {
//                if(Math.abs(l.get(0) - l.get(1)) <= k) return true;
//            } else if(size > 2){
//                for(int i=1; i<l.size(); i++){
//                    if(Math.abs(l.get(i-1) - l.get(i)) <= k) return true;
//                }
//            }
//        }
//        return false;
//    }

    //Time Complexity O(n) and Space Complexity O(n). It took 27ms.
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(mp.containsKey(nums[i])){
                if(i - mp.get(nums[i]) <= k)
                    return true;
            }
            mp.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyDuplicate cnd = new ContainsNearbyDuplicate();
        System.out.println(cnd.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(cnd.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(cnd.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
