package org.dataStructure.problems.sorting;

import java.util.Arrays;

public class BubbleSort {

    public int[] sort(int[] arr){
        /* Compare each pair of adjacent items and swap them. The end elements keep getting sorted */
        for(int i=0; i< arr.length-1; i++){ //-------------------- > O(N)
            for(int j=0; j< arr.length-i-1; j++){ //---------------> O(N) = Total time complexity -> O(N^2) ; Space Complexity -> O(1)
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = {21,34,31,4,41,100,12};
        System.out.println(Arrays.toString(bubbleSort.sort(arr)));

    }
}
