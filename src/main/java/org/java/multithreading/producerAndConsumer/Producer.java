package org.java.multithreading.producerAndConsumer;

public class Producer implements Runnable{
    private final SharedResources sharedResources;

    Producer(SharedResources sharedResources){
        this.sharedResources = sharedResources;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            this.sharedResources.produce(i);
        }
    }
}
