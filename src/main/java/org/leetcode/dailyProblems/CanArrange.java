package org.leetcode.dailyProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/?envType=daily-question&envId=2024-10-01
public class CanArrange {
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        for(int i : arr){
            int element = ((i%k)+k)%k;
            remainderCount.merge(element, 1, Integer::sum);
        }
        System.out.println(remainderCount);
        for(int i : arr){
            int element = ((i%k)+k)%k;
            if(element == 0) {
                if (remainderCount.get(element) % 2 != 0) {
                    return false;
                }
            }
            else if(!Objects.equals(remainderCount.get(element), remainderCount.get(k-element))){
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanArrange canArrange = new CanArrange();
        int[] arr =
//                {-1,-1,-1,-1,2,2,-2,-2};
        //{1,2,3,4,5,6};
         {1,2,3,4,5,10,6,7,8,9};
        //int[] arr = {1,2,3,4,5,6};//{1,2,3,4,5,10,6,7,8,9};
        int k = //3;
        //7;
         5;
        System.out.println(canArrange.canArrange(arr, k));
    }
}
