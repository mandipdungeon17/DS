package org.java.multithreading.producerAndConsumer;

public class ProducerConsumerMain {

    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();
        Producer producer = new Producer(sharedResources);
        Consumer consumer = new Consumer(sharedResources);

        Thread thread = new Thread(producer);
        Thread thread1 = new Thread(consumer);
        thread.start();
        thread1.start();
    }
}
