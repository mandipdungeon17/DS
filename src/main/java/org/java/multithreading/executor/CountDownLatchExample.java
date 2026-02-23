package org.java.multithreading.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks*2);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
//        Future<String> submit = executorService.submit(new WorkerThread(countDownLatch));
//        Future<String> submit1 = executorService.submit(new WorkerThread(countDownLatch));
//        Future<String> submit2 = executorService.submit(new WorkerThread(countDownLatch));
//
         //It waits for the given time, and then it will continue the execution of the main thread
        // and the worker threads will be running in the background
//        countDownLatch.await(5, TimeUnit.SECONDS);

        //Below code is the same as above code, but it is using invokeAll method using list of callable
        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(new WorkerThread(countDownLatch));
        callableList.add(new WorkerThread(countDownLatch));
        callableList.add(new WorkerThread(countDownLatch));

        try {
            List<Future<String>> futures = executorService.invokeAll(callableList);
//            countDownLatch.await(5, TimeUnit.SECONDS);
//            CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks*2);
            //if the countDownLatch count is more than the worker threads and if it's not decremented to zero then the main thread will wait indefinitely
            // whereas if .await(5, TimeUnit.SECONDS) is used then it will wait for 5 seconds, and then it will continue the execution of the main thread
            countDownLatch.await();
            for(Future<String> future: futures){
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            executorService.shutdown();
        }
        System.out.println(countDownLatch.getCount());
        System.out.println("All the threads are completed");
//        executorService.shutdown();
    }
}

class WorkerThread implements Callable<String>{
    private final CountDownLatch countDownLatch;

    WorkerThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        try{
            Thread.sleep(8000);
            System.out.println("Thread is completed: " + Thread.currentThread().getName());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            countDownLatch.countDown();
            System.out.println("Count down latch: " + countDownLatch.getCount());
        }
        return "Hello";
    }
}
