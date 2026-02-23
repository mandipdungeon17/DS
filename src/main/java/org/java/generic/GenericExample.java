package org.java.generic;

public class GenericExample<k, V extends Number> {
    private final k key;
    private final V value;

    public GenericExample(k key, V value) {
        this.key = key;
        this.value = value;
    }

    public k getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
        GenericExample<String, Integer> genericExample = new GenericExample<>("key", 10);
        GenericExample<String, Double> genericExample1 = new GenericExample<>("key", 10.0);
//        GenericExample<String, String> genericExample2 = new GenericExample<>("key", "10"); //Compile time error
        System.out.println(genericExample.getKey());
        System.out.println(genericExample.getValue());
        System.out.println(genericExample1.getKey());
        System.out.println(genericExample1.getValue());
    }

}
