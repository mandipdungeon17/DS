package org.leetcode.dailyProblems;

import java.util.Arrays;

public class CountDays {

    public static int countDays(int days, int[][] meetings) {
        int[] arr = new int[days+1];
        for (int[] meeting : meetings) {
            for (int j = meeting[0]; j <= meeting[1]; j++) {
                arr[j] = 1;
            }
        }
        System.out.println(Arrays.toString(arr));
        int count = 0;
        for(int i : arr){
            if(i == 0) count++;
        }
        return count-1;
    }

    public static void main(String[] args) {
        System.out.println(countDays(10, new int[][]{{5,7},{1,3},{9,10}}));
        System.out.println(countDays(5, new int[][]{{2,4},{1,3}}));
        System.out.println(countDays(6, new int[][]{{1,6}}));
    }
}
