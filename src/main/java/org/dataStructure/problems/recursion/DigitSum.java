package org.dataStructure.problems.recursion;

public class DigitSum {

    public int digitSum(int num){
        if(num == 0 || num < 0) return 0;
        else return num%10 + digitSum(num/10);
    }

    public static void main(String[] args) {
        DigitSum sum = new DigitSum();
        System.out.println(sum.digitSum(3));
    }
}
