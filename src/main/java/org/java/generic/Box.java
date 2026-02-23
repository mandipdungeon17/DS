package org.java.generic;

/*
    * In the below code, we have a class MyNumber which extends Number and implements Printable interface.
    * We have a Box class which has a generic type T which extends Number and implements Printable interface.
    * We have a Test class which creates an object of Box class and passes an object of MyNumber class to it.
    * We can see that the Box class is able to accept the object of MyNumber class
    * because MyNumber class extends Number and implements Printable interface.
    * If we remove Printable interface from the generic type of Box class, then it will give compile time error. It cannot call print() method.
    * In the generics, we can also use the '&' operator to specify that the generic type should extend a class and implement an interface.
    * Even if class is not extended first, only interface can be extended. like <T extends Interface1 & Interface2>.
    * But when class should be extended first and then interface should be implemented. like <T extends ClassType & Interface1 & Interface2>.
    * Otherwise, it will give compile time error.
 */
//<T extends ClassType & Interface1 & Interface2>
//<T extends Interface1 & Interface2>
//<T extends Interface1 & ClassType> // Compile time error
interface Printable{
    void print();
}

class MyNumber extends Number implements Printable{
    private final int value;

    MyNumber(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public void print() {
        System.out.println("MyNumber: " + value);
    }
}

public class Box<T extends Number & Printable> {
    private T item;

    public Box(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T value) {
        this.item = value;
    }

    public void display(){
        item.print();
    }
}

class Test{
    public static void main(String[] args) {
        Box<MyNumber> box = new Box<>(new MyNumber(10));
        box.display();
    }
}

