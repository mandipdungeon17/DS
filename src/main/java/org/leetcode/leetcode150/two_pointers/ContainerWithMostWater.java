package org.leetcode.leetcode150.two_pointers;

//https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150
public class ContainerWithMostWater {
    //Time Complexity O(n^2) and Space Complexity O(1). Solution TLE.
//    public int maxArea(int[] height) {
//        int max = -1;
//        for(int i=0; i<height.length; i++){
//            for(int j=i+1; j<height.length; j++){
//                int val = Math.min(height[j], height[i]);
//                max = Math.max(max, val*(j-i));
//            }
//        }
//        return max;
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 3ms.
    public int maxArea(int[] height) {
        int max=0;
        int i=0;
        int j=height.length-1;
        while(i<j){
            if(height[i]<height[j]){
                max = Math.max(max, height[i]*(j-i));
                i++;
            }
            else if(height[i]>height[j]){
                max = Math.max(max, height[j]*(j-i));
                j--;
            } else {
                max = Math.max(max, height[i]*(j-i));
                i++;
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        System.out.println(containerWithMostWater.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
