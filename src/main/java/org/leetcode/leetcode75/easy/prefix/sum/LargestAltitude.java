package org.leetcode.leetcode75.easy.prefix.sum;

public class LargestAltitude {

    public int largestAltitude(int[] gain) {
        if(gain.length == 1) return Math.max(gain[0], 0);
        int max = -101;
        int sum = 0;
        for (int j : gain) {
            sum += j;
            if (max < sum) max = sum;
        }
        return Math.max(max, 0);
    }

    public static void main(String[] args) {
        LargestAltitude altitude = new LargestAltitude();
        int ans = altitude.largestAltitude(new int[]{-5,1,5,0,-7});
        System.out.println("Largest Altitude: " + ans);
    }
}
