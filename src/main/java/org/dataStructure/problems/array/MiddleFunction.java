package org.dataStructure.problems.array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MiddleFunction {
    public static int[] middle(int[] array) {
        if(array.length <= 2)
            return new int[0];
        int[] mid = new int[array.length-2];
        for(int i=1; i<array.length-1; i++){
            mid[i-1] = array[i];
        }
        return mid;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4};
        arr = middle(arr);
        System.out.print(Arrays.toString(arr));

    }
}
