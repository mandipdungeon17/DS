package org.dataStructure.problems.queue.questions.AnimalShelter;


import java.util.LinkedList;

public class AnimalQueue {
    LinkedList<Cat> cats = new LinkedList<Cat>();
    LinkedList<Dog> dogs = new LinkedList<Dog>();
    int order = -1;

    public void enqueue(Animal a){
        a.setOrder(++order);
        if(a instanceof Dog) dogs.addLast((Dog) a);
        else cats.addLast((Cat) a);
    }

    public Dog dequeueDogs(){
        return dogs.poll();
    }
    public Cat dequeueCats(){
        return cats.poll();
    }
    public Animal dequeueAny(){
        if(dogs.isEmpty()) return dequeueCats();
        else if(cats.isEmpty()) return dequeueDogs();
        else{
            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if(dog.isOlderThan(cat)){
                return dequeueDogs();
            }
            else return dequeueCats();
        }
    }

    public static void main(String[] args) {
        AnimalQueue animals = new AnimalQueue();
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Beji"));
        animals.enqueue(new Cat("Reki"));
        animals.enqueue(new Dog("Dexter"));

        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueDogs().name());
        System.out.println(animals.dequeueCats().name());
    }

}
