package org.java.multithreading.thread;

public class ContextSwitchExample {
    public static void main(String[] args) {
        // Create two threads
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(10000); // Triggers context switch
                } catch (InterruptedException ignored) {}
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);
                try {
                    Thread.sleep(10000); // Triggers context switch
                } catch (InterruptedException ignored) {}
            }
        });

        thread1.start();
        thread2.start();
    }
}