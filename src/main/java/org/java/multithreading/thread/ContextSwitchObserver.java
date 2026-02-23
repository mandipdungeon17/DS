package org.java.multithreading.thread;
public class ContextSwitchObserver {
    public static void main(String[] args) {
        // Create threads that will cause context switching
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread-1: " + i + " [" + Thread.currentThread().getId() + "]");
                try {
                    Thread.sleep(50); // Force context switch
                } catch (InterruptedException e) {}
            }
        }, "Worker-1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread-2: " + i + " [" + Thread.currentThread().getId() + "]");
                try {
                    Thread.sleep(50); // Force context switch
                } catch (InterruptedException e) {}
            }
        }, "Worker-2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread-3: " + i + " [" + Thread.currentThread().getId() + "]");
                try {
                    Thread.sleep(50); // Force context switch
                } catch (InterruptedException e) {}
            }
        }, "Worker-3");

        System.out.println("Starting threads...");
        long startTime = System.currentTimeMillis();

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {}

        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + "ms");
    }
}
