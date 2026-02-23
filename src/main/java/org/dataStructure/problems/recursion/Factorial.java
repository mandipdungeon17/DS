package org.dataStructure.problems.recursion;

public class Factorial {
    public int fact(int n){
        if(n<0) return -1;
        if(n == 0) return 1;
        else return n * fact(n-1);
    }
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int value = 6;
        System.out.println("The factorial of " + value + " is : " + factorial.fact(value));
    }
}
