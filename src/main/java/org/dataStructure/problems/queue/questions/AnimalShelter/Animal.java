package org.dataStructure.problems.queue.questions.AnimalShelter;

public abstract class Animal {
    int order;
    String name;
    public Animal(String n){
        this.name = n;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }
    public abstract String name();
    public boolean isOlderThan(Animal a){
        return this.order < a.getOrder();
    }
}

class Dog extends Animal{
    public Dog(String n) {
        super(n);
    }

    @Override
    public String name() {
        return "Dog :" + this.name;
    }
}

class Cat extends Animal{
    public Cat(String n){
        super(n);
    }

    @Override
    public String name() {
        return "Cat :" + this.name;
    }
}
