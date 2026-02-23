package org.dataStructure.problems.array;

import java.util.Arrays;

public class FirstSecondScores {

    public static int[] findTopTwoScores(int[] array){
        int max = array[0];
        int second = array[0];
        int[] firstSecond = new int[2];
        for(int i : array){
            if(i > max) {
                second = max;
                max = i;
            }
            else if(i>second && i<max)
                second=i;
        }
        firstSecond[0] = max;
        firstSecond[1] = second;
        return firstSecond;
    }

    public static void main(String[] args){
        int[] myArray2D= {84,85,86,87,85,90,85,83,23,45,84,1,2,0};
        int[] sum = findTopTwoScores(myArray2D);
        System.out.print("The sum of diagonal is : "+ Arrays.toString(sum));

    }
}
