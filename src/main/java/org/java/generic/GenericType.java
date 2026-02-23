package org.java.generic;

import java.util.Arrays;

public class GenericType {

    //Class is not Generic but the constructor and method is generic
    public <T> GenericType(T t){
        System.out.println(t);
    }

    //Method Overloading
    public static <T> void print(T[] t){
        System.out.println(Arrays.asList(t));
    }

    public static <T> void print(T t){
        System.out.println(t);
    }

    public static void print(int t){
        System.out.println(t);
    }

    public static void main(String[] args) {
        GenericType genericType = new GenericType(10);
        GenericType genericType1 = new GenericType("Hello");
        GenericType genericType2 = new GenericType(10.0);
        print(new Integer[]{1, 2, 3});
        print(10);
        print("Hello");
    }
}
