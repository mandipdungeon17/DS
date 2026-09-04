package org.leetcode.leetcode150.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(nlogn) and Space Complexity O(n). It took 11ms. I used sorting and then merged the intervals.
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));

        List<int[]> list = new ArrayList<>();

        int currStart = intervals[0][0];
        int currEnd = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] <= currEnd){
                currEnd = Math.max(currEnd, intervals[i][1]);
            } else {
                list.add(new int[]{currStart, currEnd});
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            }
        }
        list.add(new int[]{currStart, currEnd});
        System.out.println(Arrays.deepToString(list.toArray()));
        int[][] res = new int[list.size()][2];
        for(int i=0; i<list.size(); i++){
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] intervals = {{4, 7}, {1, 4}};
        int[][] result = mergeIntervals.merge(intervals);
        for (int[] interval : result) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }
    }
}
