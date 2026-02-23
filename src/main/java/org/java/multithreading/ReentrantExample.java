package org.java.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
* ReentrantLock is used to make the lock reentrant, which means the same thread can acquire the lock multiple times
* without any issue. But the lock should be released the same number of times it was acquired. If the lock is not released then
* the other threads will not be able to acquire the lock and the program will hang.
* The lock should be released in the finally block.*
 */
public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try{
            System.out.println("Outer Method");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    private void innerMethod() {
        lock.lock();
        try{
            Thread.sleep(1000);
            System.out.println("Inner Method");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();
        reentrantExample.innerMethod();
        reentrantExample.outerMethod();

    }
}
