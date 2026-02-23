package org.leetcode.leetcode75.medium.sliding.window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int count = 0;
//        int zeros = k;
        int i = 0;
        int max;
        List<Integer> list = new ArrayList<>();
        while(i<nums.length-1){
            count++;
           if(nums[i] == 0 && nums[i+1] == 1){
               list.add(0);
               list.add(count);
               count = 0;
           }
           else if(nums[i] == 1 && nums[i+1] == 0){
                list.add(1);
                list.add(count);
                count = 0;
           }
            i++;
        }
        max = Collections.max(list);

        System.out.println(list);
        max = count;
        return max;
    }

    public static void main(String[] args) {
        LongestOnes longestOnes = new LongestOnes();
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        int ans = longestOnes.longestOnes(nums, k);
        System.out.println("Longest Ones: " + ans);
    }
}
