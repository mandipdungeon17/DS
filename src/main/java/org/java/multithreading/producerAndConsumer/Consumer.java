package org.java.multithreading.producerAndConsumer;

public class Consumer implements Runnable {
    private final SharedResources sharedResources;

    Consumer(SharedResources sharedResources){
        this.sharedResources = sharedResources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = sharedResources.consume();
        }
    }
}
