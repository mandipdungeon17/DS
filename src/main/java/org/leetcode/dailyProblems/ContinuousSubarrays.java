package org.leetcode.dailyProblems;

public class ContinuousSubarrays {
    public static long continuousSubarrays(int[] nums) {
        int res = nums.length;
        for(int i=2; i<=nums.length; i++){
            int[] arr = new int[i];
            int count = 0;
            for(int j=0; j<nums.length; j++){
                arr[count++] = nums[j];
                if(count == arr.length){
                    if(Math.abs(arr[arr.length-1] - arr[0]) <=2 && Math.abs(arr[arr.length-1] - arr[0]) >=0){
                        res++;
                    }
                    arr = new int[i];
                    count=0;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(continuousSubarrays(new int[]{5,4,2,4}));
    }
}
