package org.dataStructure.problems.queue.questions;

public abstract class AnimalShelterSelf {
    public void enqueue(Cat cat) {

    }

    public void enqueue(Dog dog){

    }
    public void dequeueAny(){

    }
}

class DogAndCat{
    Node first;
    Node last;
    public DogAndCat(String data){
        this.enQueue(data);
    }
    public void enQueue(String data){
        Node node = new Node(data);
        if(first == null){
            this.first = this.last = node;
        }
        else{
            this.last.next = node;
            this.last = node;
        }
        System.out.println("The DogAndCat : " + data + " is successfully inserted");
    }

    public String deQueue(){
        String data = null;
        if(this.first == null){
            System.out.println("There is no data to delete");
        }
        else{
            data = this.first.data;
            this.first = this.first.next;
        }
        return data;
    }
}

class Dog{
    Node first;
    Node last;
    DogAndCat dogAndCat;
    public Dog(String data){
        this.enQueue(data);
    }
    public void enQueue(String data){
        dogAndCat = new DogAndCat(data);
        Node node = new Node(data);
        if(first == null){
            this.first = this.last = node;
        }
        else{
            this.last.next = node;
            this.last = node;
        }
        System.out.println("The Dog : " + data + " is successfully inserted");
    }

    public String deQueue(){
        dogAndCat.deQueue();
        String data = null;
        if(this.first == null){
            System.out.println("There is no data to delete");
        }
        else{
            data = this.first.data;
            this.first = this.first.next;
        }
        return data;
    }
}

class Cat{
    Node first;
    Node last;
    DogAndCat dogAndCat;
    public Cat(String data){
        this.enQueue(data);
    }
    public void enQueue(String data){
        dogAndCat = new DogAndCat(data);
        Node node = new Node(data);
        if(first == null){
            this.first = this.last = node;
        }
        else{
            this.last.next = node;
            this.last = node;
        }
        System.out.println("The Cat : " + data + " is successfully inserted");
    }

    public String deQueue(){
        dogAndCat.deQueue();
        String data = null;
        if(this.first == null){
          System.out.println("There is no data to delete");
        }
        else{
            data = this.first.data;
            this.first = this.first.next;
        }
        return data;
    }

}

class Node{
    String data;
    Node next;

    public Node(String data){
        this.data = data;
        this.next = null;
    }
}
