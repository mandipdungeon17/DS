package org.dataStructure.problems.recursion;

public class MaxValueInArray {

    public int maxValue(int[] arr, int n){
        if(n == 0) return arr[0];
        return Math.max(arr[n-1], maxValue(arr, n-1));
    }

    public static void main(String[] args) {
        MaxValueInArray maxValueInArray = new MaxValueInArray();
        int[] value = {11, 4, 12, 16, 5, 20};
        System.out.println("The Max number in the array is : " + maxValueInArray.maxValue(value, 6));
    }
}
