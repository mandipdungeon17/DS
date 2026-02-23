package org.dataStructure.problems.array;

import java.util.Arrays;

public class DiagonalSum2D {
    public static int sumDiagonalElements(int[][] array) {

        int i=0;
        int sum=0;
        while(i<array.length){
            sum+= array[i][i];
            i++;
        }
        return sum;
    }

    public static void main(String[] args){
        int[][] myArray2D= {{1,2,3},{4,5,6},{7,8,9}};
        int sum = sumDiagonalElements(myArray2D);
        System.out.print("The sum of diagonal is : "+sum);

    }
}
