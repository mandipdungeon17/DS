package org.leetcode.leetcode75.easy.hash.map.set;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindDifference {
    //Time complexity: O(n) and Space complexity: O(n). It took 14 ms.
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer, Integer> diff = new LinkedHashMap<>();
        for(int i : nums1){
            diff.put(i, 1);
        }
        System.out.println("Diff : " + diff);
        int size1 = diff.size();
        Set<Integer> set = new LinkedHashSet<>();
        for(int i : nums2){
            set.add(i);
        }
        System.out.println("Set : " + set);
        for(int i : set){
            diff.put(i, diff.getOrDefault(i, 0)+1);
        }
        System.out.println("Map : " + diff);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        for(int i : diff.keySet()){
            if(diff.get(i) == 1){
                integers.add(i);
            }
            size1--;
            if(size1 == 0){
                list.add(integers);
                integers = new ArrayList<>();
            }
        }
        list.add(integers);
        return list;
    }

    public static void main(String[] args) {
        FindDifference findDifference = new FindDifference();
        List<List<Integer>> ans = findDifference.findDifference(new int[]{-68,-80,-19,-94,82,21,-43}, new int[]{-63});
        System.out.println("Find Difference: " + ans);
    }
}
