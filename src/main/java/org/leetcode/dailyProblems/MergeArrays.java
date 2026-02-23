package org.leetcode.dailyProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MergeArrays {

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int minLen = Math.min(nums1.length, nums2.length);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0; i<minLen; i++){
            if(nums1[i][0] == nums2[i][0]){
                map.put(nums1[i][0], nums1[i][1]+nums2[i][1]);
            }
            else{
                if(null == map.get(nums1[i][0]) && null == map.get(nums2[i][0])){
                    map.put(nums1[i][0], nums1[i][1]);
                    map.put(nums2[i][0], nums2[i][1]);
                } else if (null == map.get(nums1[i][0])){
                    map.put(nums1[i][0], nums1[i][1]);
                    map.put(nums2[i][0], map.get(nums2[i][0])+nums2[i][1]);
                }
                else if (null == map.get(nums2[i][0])){
                    map.put(nums2[i][0], nums2[i][1]);
                    map.put(nums1[i][0], map.get(nums1[i][0])+nums1[i][1]);
                }
            }
        }
        if(nums1.length > nums2.length){
            for(int i=minLen; i< nums1.length; i++){
                if (null == map.get(nums1[i][0])){
                    map.put(nums1[i][0], nums1[i][1]);
                }
                else{
                    map.put(nums1[i][0], map.get(nums1[i][0])+nums1[i][1]);
                }
            }
        }
        else{
            for(int i=minLen; i< nums2.length; i++){
                if (null == map.get(nums2[i][0])){
                    map.put(nums2[i][0], nums2[i][1]);
                }
                else{
                    map.put(nums2[i][0], map.get(nums2[i][0])+nums2[i][1]);
                }
            }
        }
        int[][] result = new int[map.size()][2];
        int count = 0;
        for(Map.Entry<Integer, Integer> m : map.entrySet()){
            result[count][0] = m.getKey();
            result[count++][1] = m.getValue();
        }
        return result;
    }

    public static void main(String[] args) {
        MergeArrays mergeArrays = new MergeArrays();
//        int[][] nums1 = {{1, 2}, {2, 3}, {4, 5}};
//        int[][] nums2 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] nums1 = {{2, 4}, {3, 6}, {5, 5}};
        int[][] nums2 = {{1, 3}, {4, 3}};
        int[][] result = mergeArrays.mergeArrays(nums1, nums2);
        System.out.println("Result: " + Arrays.deepToString(result));
    }
}
