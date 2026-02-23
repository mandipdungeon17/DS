package org.java.multithreading.thread;

public class WaitNotifyLockExample {
    private static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitingThread = new Thread(() -> {
            synchronized (monitor) {
                System.out.println("Waiting thread: About to wait");
                try {
                    monitor.wait();
                    System.out.println("Waiting thread: Resumed after wait!");
                } catch (InterruptedException e) {
                    System.out.println("Waiting thread: Interrupted!");
                }
            }
        });

        Thread notifyingThread = new Thread(() -> {
            synchronized (monitor) {
                System.out.println("Notifying thread: Got the lock");
                System.out.println("Notifying thread: About to notify");
                monitor.notify(); // notify will not release lock.
                System.out.println("Notifying thread: Called notify()");
                try {
                    Thread.sleep(2000); // Keep holding the lock for 2 seconds!
                } catch (InterruptedException e) {}
                System.out.println("Notifying thread: About to exit synchronized block");
            }
            System.out.println("Notifying thread: Exited synchronized block");
        });

        waitingThread.start();
//        waitingThread.join();
        Thread.sleep(100); // Let waiting thread start first
        notifyingThread.start();
    }

    /*-----------Output--------------------
        Waiting thread: About to wait
        Notifying thread: Got the lock
        Notifying thread: About to notify
        Notifying thread: Called notify()
        Notifying thread: About to exit synchronized block
        Notifying thread: Exited synchronized block
        Waiting thread: Resumed after wait!
     */
}
