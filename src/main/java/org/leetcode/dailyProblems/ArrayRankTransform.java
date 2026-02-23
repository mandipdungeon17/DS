package org.leetcode.dailyProblems;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
//Time Complexity: O(nlogn) where n is the length of the array
//https://leetcode.com/problems/rank-transform-of-an-array/submissions/1409156982/?envType=daily-question&envId=2024-10-02
public class ArrayRankTransform {
    public int[] arrayRankTransform(int[] arr) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int count = 0;
        for(int i : arr){
            treeMap.put(i, count);
        }
        for(Map.Entry<Integer, Integer> entry : treeMap.entrySet()){
            entry.setValue(++count);
        }
        for (int i=0; i<arr.length; i++){
            arr[i] = treeMap.get(arr[i]);
        }
        return arr;
    }

//    class Solution {
//
//        public int[] arrayRankTransform(int[] arr) {
//            // Store the rank for each number in arr
//            HashMap<Integer, Integer> numToRank = new HashMap<>();
//            int[] sortedArr = Arrays.copyOf(arr, arr.length);
//            Arrays.sort(sortedArr);
//            int rank = 1;
//            for (int i = 0; i < sortedArr.length; i++) {
//                if (i > 0 && sortedArr[i] > sortedArr[i - 1]) {
//                    rank++;
//                }
//                numToRank.put(sortedArr[i], rank);
//            }
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = numToRank.get(arr[i]);
//            }
//            return arr;
//        }
//    }
}


class TreeMapExample {
    public static void main(String[] args) {
        int[] arr = {37,12,28,9,100,56,80,5,12};
        ArrayRankTransform arrayRankTransform = new ArrayRankTransform();
        int[] result = arrayRankTransform.arrayRankTransform(arr);
        System.out.println(Arrays.toString(result));
    }
}

