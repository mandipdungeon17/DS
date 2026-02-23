package org.java.generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public enum Operations {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public <T extends Number> double apply(T t1, T t2) {
        switch (this) {
            case ADD:
                return (Double) t1 + (Double) t2;
            case SUBTRACT:
                return (Double) t1 - (Double) t2;
            case MULTIPLY:
                return (Double) t1 * (Double) t2;
            case DIVIDE:
                return (Double) t1 / (Double) t2;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        List<Integer> list = new LinkedList<>();


        System.out.println(ADD.apply(10.0, 20.0));
        System.out.println(SUBTRACT.apply(10.0, 20.0));
        System.out.println(MULTIPLY.apply(10.0, 20.0));
        System.out.println(DIVIDE.apply(10.0, 20.0));
    }
}
