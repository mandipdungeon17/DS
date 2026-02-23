package org.dataStructure.problems.recursion;

import java.util.Arrays;

public class SomeRecursive {
//    public boolean someRecursive(int[] arr, OddFunction odd) {
//        if (arr.length == 0 ) {
//            return false;
//        } else if (odd.run(arr[0]) == false) {
//            return someRecursive(Arrays.copyOfRange(arr, 1, arr.length), odd);
//        } else {
//            return true;
//        }
//    }

    public boolean someRecursive(int[] arr, OddFunction odd) {
        if(arr.length < 1) return false;
        else if(odd.run(arr[0])) return true;
        return someRecursive(Arrays.copyOfRange(arr, 1, arr.length), odd);
    }

    public static void main(String[] args) {
        SomeRecursive someRecursive = new SomeRecursive();
        int[] arr = {2, 4, 6, 8, 10};
        OddFunction oddFunction = new OddFunction();
        System.out.println(someRecursive.someRecursive(arr, oddFunction));

    }

}

 class OddFunction {
    boolean run(int num) {
        return num % 2 != 0;
    }
}
