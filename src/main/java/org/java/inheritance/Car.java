package org.java.inheritance;

public interface Car {
    void start();
    void engine();
    void stop();
}

class Maruti implements Car{

    @Override
    public void start() {
        System.out.println("Maruti Start");
    }

    @Override
    public void engine() {
        System.out.println("Maruti Engine");
    }

    @Override
    public void stop() {
        System.out.println("Maruti Stop");
    }

    public void marutiMethod(){
        System.out.println("Maruti Method");
    }
}

class Honda extends Maruti{

    @Override
    public void start() {
        System.out.println("Honda Start");
    }

    @Override
    public void engine() {
        System.out.println("Honda Engine");
    }

    @Override
    public void stop() {
        System.out.println("Honda Stop");
    }

    public void hondaMethod(){
        System.out.println("Honda Method");
    }
}

class Test{
    public static void main(String[] args) {
        Car car = new Honda();
        Car car1 = new Maruti();
        car.start();
        car.engine();
        car.stop();
        //car.hondaMethod(); //Compile time error
        ((Maruti) car1).marutiMethod();
        Honda honda = new Honda();
        honda.marutiMethod();
        honda.hondaMethod();
    }
}
