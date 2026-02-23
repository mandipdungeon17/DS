package org.leetcode.dailyProblems;

import java.util.Comparator;
import java.util.PriorityQueue;

// Time complexity: O(nlogn) where n is the size of the array and space complexity: O(n)
public class MaxSumSubArray {

    public int maxSumSubArray(int[] arr, int k){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i: arr){
            priorityQueue.add(i);
        }
        int sum=0;
        for(int i=0; i<k; i++){
            sum+= priorityQueue.poll();
        }
        return sum;
    }

    public static void main(String[] args) {
        MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
        int[] arr = {100, 200, 300, 400};
        int k = 2;
        System.out.println(maxSumSubArray.maxSumSubArray(arr, k));
    }
}
