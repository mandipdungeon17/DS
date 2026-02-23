package org.leetcode;

import java.util.Arrays;

public class RelativeSortArray {
    //Time Complexity: O(n^2) & Space Complexity: O(1)
//    public int[] relativeSortArray(int[] arr1, int[] arr2) {
//        int index2 = 0;
//        int i = 0;
//        while(i < arr1.length && index2 < arr2.length){
//            int num = arr2[index2++];
//            int index1 = -1;
//            for(int j=i; j<arr1.length; j++){
//                if(num == arr1[j]){
//                    if(index1 != -1){
//                        arr1[j] = arr1[index1];
//                        arr1[index1] = num;
//                        index1++;
//                    }
//                    i++;
//                }
//                else if(index1 == -1){
//                    index1 = j;
//                }
//            }
//        }
//        for(int j=i; j<arr1.length; j++){
//            for(int k=j+1; k<arr1.length; k++){
//                if(arr1[j] > arr1[k]){
//                    int swapNum = arr1[j];
//                    arr1[j] = arr1[k];
//                    arr1[k] = swapNum;
//                }
//            }
//        }
//        return arr1;
//    }

    //Time Complexity: O(n) & Space Complexity: O(n)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length];
        int[] count = new int[1001];
        int i = 0;

        for(int a : arr1)
            ++count[a];

        for(int a : arr2){
            while(count[a]-- >0)
                ans[i++] = a;
        }
        for(int j=0; j<1001; j++){
            while(count[j]-- >0)
                ans[i++] = j;
        }
        return ans;
    }


    public static void main(String[] args) {
        RelativeSortArray relativeSortArray = new RelativeSortArray();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] arr = relativeSortArray.relativeSortArray(arr1, arr2);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}
