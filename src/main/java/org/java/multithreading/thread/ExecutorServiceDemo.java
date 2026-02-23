package org.java.multithreading.thread;


import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(10000); // 10 second task
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Interrupted";
            }
            return "Done";
        });

// Step 1: This times out after 3 seconds
        try {
            future.get(3, TimeUnit.SECONDS); // TimeoutException thrown
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Timed out!");
        }

// Step 2: Immediately call get() again without timeout
        String result = future.get(); // What happens here?
        System.out.println(result);
        executor.shutdown();
    }
}
