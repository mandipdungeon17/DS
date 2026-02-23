package org.dataStructure.problems.sorting;

import java.util.Arrays;

public class InsertionSort {

    public int[] sort(int[] arr){
        /* int[] newArr = new int[arr.length];
        for(int i=0; i< arr.length; i++){
            int temp = arr[i];
            int k = 0;
            for(int j=0; j<i; j++){
                if(temp < newArr[j]){
                    temp = newArr[j];
                    newArr[j] = arr[i];
                }
                k=j+1;
                arr[i]=temp;
            }
            newArr[k] = temp;
        }
        return newArr; */
        /* Divide the array into 2 part; Left side sorted array, right side unsorted array */
        for(int i=1; i<arr.length; i++){ //-------------------- > O(N)
            int temp = arr[i], j=i-1;
            while(j>=0 && arr[j] > temp){ //---------------> O(N) = Total time complexity -> O(N^2) ; Space Complexity -> O(1)
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] arr = {21,34,31,4,41,100,12};
        System.out.println(Arrays.toString(insertionSort.sort(arr)));
    }
}
