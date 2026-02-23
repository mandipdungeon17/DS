package org.dataStructure.problems.recursion;

public class PowerNumber {
    public int pow(int base, int exp){
        if(exp < 0) return -1;
        if(exp == 0) return 1;
        else return (int) (base*pow(base, exp -1 ));
    }

    public static void main(String[] args) {
        PowerNumber powerNumber = new PowerNumber();
        System.out.println(powerNumber.pow(2,3));
    }
}
