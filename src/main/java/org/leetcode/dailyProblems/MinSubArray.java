package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.List;

public class MinSubArray {
    //The below logic is to get subArray which is in sequence with nums array and not random.
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for(int i: nums){
            sum+=i;
        }
        System.out.println(sum);
        if(sum%p == 0) return 0;
        else{
            long diff = sum%p;
            System.out.println(diff);
            List<Integer> subArr = new ArrayList<>();
            int sumSubArr = 0;
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num == diff) return 1;
                else if (num > diff) {
                    subArr.clear();
                    sumSubArr = 0;
                    continue;
                } else if (subArr.isEmpty() || subArr.size() == 1) {
                    subArr.add(num);
                    sumSubArr += num;
                } else {
                    if (sumSubArr > diff) {
                        int removedElement = subArr.remove(0);
                        subArr.add(num);
                        sumSubArr = sumSubArr - removedElement + num;
                    } else {
                        subArr.add(num);
                        sumSubArr += num;
                    }
                }
                if (sumSubArr == diff) {
                    min = Math.min(min, subArr.size());
                    subArr.clear();
                    sumSubArr = 0;
                }
            }
            if(min == Integer.MAX_VALUE || min == nums.length) return -1;
            return min;
        }
    }

    public static void main(String[] args) {
        MinSubArray minSubarray = new MinSubArray();
        int[] nums = {26,19,11,14,18,4,7,1,30,23,19,8,10,6,26,3}; // 4,7,1,8,10,6,3 = 39
        int p = 26;
        System.out.println(minSubarray.minSubarray(nums, p));
    }
}
