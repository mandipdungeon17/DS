package org.leetcode.leetcode150.intervals;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/submissions/2128287466/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(nlogn) and Space Complexity O(n). It took 50ms.
public class FindMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt((int[] a) -> a[0]));

        int currEnd = points[0][1];
        int res = points.length;

        for(int i=1; i<points.length; i++){
            if(points[i][0] <= currEnd){
                res--;
                currEnd = Math.min(currEnd, points[i][1]);
            } else{
                currEnd = points[i][1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindMinArrowShots f = new FindMinArrowShots();
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        System.out.println(f.findMinArrowShots(points));

        int[][] points1 = new int[][]{{1,2},{3,4},{5,6},{7,8}};
        System.out.println(f.findMinArrowShots(points1));
    }
}
