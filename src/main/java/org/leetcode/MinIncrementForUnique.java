package org.leetcode;

import java.util.HashSet;
import java.util.Set;

public class MinIncrementForUnique {

    //Time Complexity: O(n^2) & Space Complexity: O(n)
    public int minIncrementForUnique(int[] nums){
        Set<Integer> list = new HashSet<>();
        int count = 0;
        for(int num : nums){
            if(!list.isEmpty() && list.contains(num)){
                while(list.contains(num)){
                    num++;
                    ++count;
                }
                list.add(num);
            }
            else{
                list.add(num);
            }
        }
        System.out.println(list);
        return count;
    }

    //Time Complexity: O(n) & Space Complexity: O(n)
//    public int minIncrementForUnique(int[] nums){
//        int[] arr = new int[100000];
//        int count = 0;
//
//        for(int a : nums){
//            while(arr[a] == 1){
//                a++;
//                count++;
//            }
//            ++arr[a];
//        }
//        return count;
//    }

    public static void main(String[] args) {
        MinIncrementForUnique incrementForUnique = new MinIncrementForUnique();
        int[] nums = {3,2,1,2,1,7};
        int count = incrementForUnique.minIncrementForUnique(nums);
        System.out.println("Minimum number of moves: " + count);
    }
}
