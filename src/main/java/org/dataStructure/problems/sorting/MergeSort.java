package org.dataStructure.problems.sorting;

import java.util.Arrays;

public class MergeSort {

    public void merge(int[] arr, int[] left, int[] right) {
        int i=0, j=0, k=0;
        while(i<left.length && j<right.length){
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            }
            else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while(i<left.length){
            arr[k] = left[i];
            i++;
            k++;
        }
        while(j<right.length){
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    public int[] mergeSort(int[] arr){
        if(arr.length < 2) return arr;
        int mid = arr.length/2;
        int[] left = new int[mid];
        int[] right = new int[arr.length-mid];

        System.arraycopy(arr, 0, left, 0, mid);
//        for(int i=0; i< mid; i++){
//            left[i] = arr[i];
//        }

        if (arr.length - mid >= 0) System.arraycopy(arr, mid, right, 0, arr.length - mid);
//        for(int j=mid; j< arr.length; j++){
//            right[j-mid] = arr[j];
//        }
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
        return arr;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {21,34,31,4,41,100,12};
        arr = mergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
}
