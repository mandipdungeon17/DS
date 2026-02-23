package org.dataStructure.problems.array;

import java.util.Arrays;

public class DuplicateNumber {

    public static int[] removeDuplicates(int[] arr) {
        int[] unique = new int[arr.length];
        unique[0] = arr[0];
        boolean flag = true;
        int count = 1;

        for(int i=1; i<arr.length; i++){
            for(int j=0; j<i; j++){
                if(arr[i] == unique[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                unique[count++]=arr[i];
//                count++;
            }
            flag = true;
        }
//        int[] un = new int[count];
//        int a = 0;
//        for (int j : unique) {
//            if (j != 0) {
//                un[a] = j;
//                a++;
//            }
//        }
        return Arrays.copyOf(unique, count);
    }

    public static void main(String[] args){
        int[] result = new int[]{1, 1, 2, 2, 3, 4, 5};
        result = removeDuplicates(result);
        System.out.print("Result " + Arrays.toString(result));
    }
}
