package org.dataStructure.problems.recursion;

public class GreatestCommonDivisor {

    public int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }

    public static void main(String[] args) {
        GreatestCommonDivisor divisor = new GreatestCommonDivisor();
        System.out.println(divisor.gcd(8, 12));
        System.out.println(divisor.gcd(48, 18));
    }
}
