package org.java.multithreading.threadSafeKeywords;

import java.util.concurrent.atomic.AtomicInteger;

/* Below code is an example of how to make a variable atomic.
    * In the below code, we have a counter variable which is being accessed by two threads.
    * If we don't make the counter variable atomic, then the output will be varying each time we run the code.(176, 200, 198, etc.)
    * But if we make the counter variable atomic, then the output will be 200 each time we run the code.
    * This is because the atomic variable is thread safe and it will not allow two threads to access the variable at the same time.
    * So, the output will be 200 each time we run the code.
 */
public class Atomicity {
//    private int counter = 0;
//
//    public void setCounter(){
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        counter++;
//    }
//
//    public int getCounter(){
//        return counter;
//    }

    private AtomicInteger atomic = new AtomicInteger();

    public void setCounter(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        atomic.incrementAndGet();
    }

    public int getCounter(){
        return atomic.get();
    }

    public static void main(String[] args) {
        Atomicity atomicity = new Atomicity();
        Thread thread1 = new Thread(()->
        {
            for(int i=0; i<100; i++){
                atomicity.setCounter();
            }
        });
        Thread thread2 = new Thread(()->
        {
            for(int i=0; i<100; i++){
                atomicity.setCounter();
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicity.getCounter());
    }
}
