package org.dataStructure.problems.recursion;

public class DecimalToBinary {

    public int decBin(int num){
        if(num == 0) return 0;
//        else if(num == 1) return 1;
        else return num%2+10*decBin(num/2);
    }

    public static void main(String[] args) {
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        System.out.println(decimalToBinary.decBin(10));
    }
}
