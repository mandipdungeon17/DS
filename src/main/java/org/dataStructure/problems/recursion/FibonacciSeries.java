package org.dataStructure.problems.recursion;

public class FibonacciSeries {

    public static void main(String[] args) {
        FibonacciSeries fibonacciSeries = new FibonacciSeries();
        int value = 6;
        System.out.println("The Fibonacci of " + value + " is : " + fibonacciSeries.fibo(value));
    }

    private int fibo(int n) {
        if(n < 0) return -1;
        if(n == 0 || n == 1) return n;
        else return fibo(n-1) + fibo(n-2);
    }
}
