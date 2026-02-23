package org.dataStructure.problems.recursion;

/* Given a number, return the sum of numbers from 0 to that number. */

public class RecursiveRange {
    public int recursiveRange(int num) {
        if(num == 0) return 0;
        return num + recursiveRange(num-1);
    }

    public static void main(String[] args) {
        RecursiveRange recursiveRange = new RecursiveRange();
        System.out.println(recursiveRange.recursiveRange(6));
    }
}
