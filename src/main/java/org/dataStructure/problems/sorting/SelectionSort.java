package org.dataStructure.problems.sorting;

import java.util.Arrays;

public class SelectionSort {

    public int[] sort(int[] arr){
        /* In this case, we repeatedly find the minimum element and move it to the sorted part of arr (Left side) to make unsorted part sorted */
        // Incorrect logic
//        for(int i=0; i<arr.length; i++){ //-------------------- > O(N)
//            for (int j = i+1; j < arr.length; j++) { //---------------> O(N) = Total time complexity -> O(N^2) ; Space Complexity -> O(1)
//                //Increasing Order
//                if(arr[i] > arr[j]){
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//                //Decreasing Order
//               /* if(arr[i] < arr[j]){
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }*/
//            }
//        }
        for(int i=0; i<arr.length-1; i++){
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[minIndex] ){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] arr = {21,34,31,4,41,8,12};
        System.out.println(Arrays.toString(selectionSort.sort(arr)));

    }
}
