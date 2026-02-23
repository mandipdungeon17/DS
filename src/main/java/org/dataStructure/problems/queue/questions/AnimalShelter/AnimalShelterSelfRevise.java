package org.dataStructure.problems.queue.questions.AnimalShelter;

import java.util.LinkedList;

public class AnimalShelterSelfRevise {
    LinkedList<Bird> Birds = new LinkedList<>();
    LinkedList<Rabbit> Rabbits = new LinkedList<>();
    int order = -1;
    public void enqueue(AnimalShelter a){
        a.setOrder(++this.order);
        if(a instanceof Rabbit) Rabbits.addLast((Rabbit) a);
        else Birds.addLast((Bird) a);
    }

    public Rabbit dequeueRabbits(){
        return Rabbits.poll();
    }
    public Bird dequeueBirds(){
        return Birds.poll();
    }
    public AnimalShelter dequeueAny(){
        if(Rabbits.isEmpty()) return dequeueBirds();
        else if(Birds.isEmpty()) return dequeueRabbits();
        Rabbit Rabbit = Rabbits.peek();
        Bird Bird = Birds.peek();
        if(Rabbit.isOlderThan(Bird)) return dequeueRabbits();
        else return dequeueBirds();
    }

    public static void main(String[] args) {
        AnimalShelterSelfRevise animals = new AnimalShelterSelfRevise();
        animals.enqueue(new Bird("Kiki"));
        animals.enqueue(new Bird("Kari"));
        animals.enqueue(new Rabbit("Beji"));
        animals.enqueue(new Bird("Reki"));
        animals.enqueue(new Rabbit("Dexter"));

        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueRabbits().name());
        System.out.println(animals.dequeueBirds().name());
        System.out.println(animals.dequeueBirds().name());
        System.out.println(animals.dequeueAny().name());
    }
}

 abstract class AnimalShelter{
    String name;
    int order;
    public AnimalShelter(String name){ this.name = name;}
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public abstract String name();
    public boolean isOlderThan(AnimalShelter a){
        return this.order < a.getOrder();
    }
}

class Bird extends AnimalShelter{

    public Bird(String name) {
        super(name);
    }

    @Override
    public String name() {
        return "Bird : " + this.name;
    }
}

class Rabbit extends AnimalShelter{

    public Rabbit(String name) {
        super(name);
    }

    @Override
    public String name() {
        return "Rabbit : " + this.name;
    }
}


