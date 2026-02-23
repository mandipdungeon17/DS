package org.dataStructure.problems.sorting;

import java.util.Arrays;

public class QuickSort {

    public int partition(int[] arr, int start, int end){
        int pivot = arr[end];
        int front = start;
        for(int i=start; i<end; i++){
            if(arr[i] <= pivot){
                int temp = arr[front];
                arr[front] = arr[i];
                arr[i] = temp;
                front++;
            }
        }
        int temp = arr[front];
        arr[front] = arr[end];
        arr[end] = temp;
        return front;
    }

    public int[] quickSort(int[] arr, int start, int end){
        if(start < end){
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
        return arr;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] arr = {21,34,31,4,41,100,12};
//        int[] arr = {7,2,1,6,8,5,3,4};
        System.out.println(Arrays.toString(sort.quickSort(arr, 0, arr.length-1)));

    }
}
