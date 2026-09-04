package org.leetcode.leetcode150.intervals;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/submissions/2126940773/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(n). It took 1ms. I used a list to store the merged intervals and then converted it to an array.
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        if(intervals.length == 0){
            int[][] res = new int[1][2];
            res[0][0] = newInterval[0];
            res[0][1] = newInterval[1];
            return res;
        }
        boolean flag = false;
        List<int[]> list = new ArrayList<>();
        int index = 0;

        for(int i=0; i<intervals.length; i++){
            if(intervals[i][0] <= newInterval[0] && intervals[i][1] >= newInterval[0]){
                intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                flag = true;
                index = i;
                break;
            } else if(intervals[i][0] >= newInterval[0] && newInterval[1] >= intervals[i][0]){
                intervals[i][0] =  newInterval[0];
                intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                flag = true;
                index = i;
                break;
            }
            list.add(new int[]{intervals[i][0], intervals[i][1]});
        }
        if(!flag){
            for(int i=0; i<list.size(); i++){
                if(list.get(i)[0] > newInterval[0]){
                    list.add(i, new int[]{newInterval[0], newInterval[1]});
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list.add(new int[]{newInterval[0], newInterval[1]});
            }
        } else {
            int currStart = intervals[index][0];
            int currEnd = intervals[index][1];

            for(int i=index+1; i<intervals.length; i++){
                if(currEnd >= intervals[i][0]){
                    currEnd = Math.max(intervals[i][1], currEnd);
                } else{
                    list.add(new int[]{currStart, currEnd});
                    currStart = intervals[i][0];
                    currEnd = intervals[i][1];
                }
            }
            list.add(new int[]{currStart, currEnd});
        }

        int[][] res = new int[list.size()][2];
        for(int i=0; i<list.size(); i++){
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    public static void main(String[] args) {
        InsertIntervals insertIntervals = new InsertIntervals();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] result = insertIntervals.insert(intervals, newInterval);
        for (int[] interval : result) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }
    }
}
