package org.java.multithreading.executor;

import java.util.concurrent.*;

public class Factorial {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        Factorial factorial = new Factorial();
        //Using ExecutorService to execute the threads
        ExecutorService executor = Executors.newFixedThreadPool(9);
        for(int i=0; i<10; i++){
            int finalI = i;
            Runnable runnable = () -> { int fact = factorial.fact(finalI);
                System.out.println("Factorial of " + finalI + " is: " + fact);};
            executor.submit(runnable); //Runnable interface is used to execute the threads. It doesn't return any value.
        }
        executor.shutdown();
        //awaitTermination() method is used to wait for the executor to terminate.
        //After the executor is terminated, it will return true and the main thread will continue.
        //The shutdown() method will not wait for the executor to terminate and then start the main thread,
        // the main thread will continue its operation regardless of the executor.
        //But the awaitTermination() will wait for the executor to terminate and then start the main thread.
        try{
            while(!executor.awaitTermination(1, TimeUnit.SECONDS)){
                System.out.println("Waiting for the executor to terminate");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Time taken to execute the program: " + endTime);

        //Returning a value from a thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> 42;
        Future<Integer> future = executorService.submit(callable); //Callable interface is used to return a value from a thread.
        System.out.println("Future: " + future.get());
        executorService.shutdown();

        //Returning a value from a thread
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        //Runnable interface is used to execute the threads. It returns the value of the second argument.
        Future<?> future1 = threadExecutor.submit(() -> System.out.println("Hello World"), "Hello");
        System.out.println("Future1: " + future1.get());
        threadExecutor.shutdown();

        //CachedThreadPool. It creates a new thread dynamically if the thread is not available in the pool.
        //It should be used when the number of threads is not known and the tasks are short-lived.
        // Otherwise, it will create a lot of threads and the performance will degrade.
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

    public int fact(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(n == 0) return 1;
        else return n * fact(n-1);
    }
}
