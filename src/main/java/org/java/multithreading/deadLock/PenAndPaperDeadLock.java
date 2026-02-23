package org.java.multithreading.deadLock;

/*
* Deadlock is a situation where two or more threads are blocked forever, waiting for each other.
* In the below example, we have two threads, one is using pen and trying to use paper, and the other is using paper and trying to use pen.
* Both threads are synchronized on their respective objects, but they are trying to acquire the lock on the other object.
* This will lead to a deadlock situation.
* To avoid deadlock, we should always acquire the lock on the objects in the same order.
* In the below example, we are acquiring the lock on the pen object first in the Paper thread and the paper object first in the Pen thread.
* This will avoid the deadlock situation.
* If we remove the synchronized block from the Paper thread, the deadlock situation will not occur.
* The reason is that the Pen thread will acquire the lock on the pen object and the Paper thread will not be able to acquire the lock on the paper object.
* The Pen thread will finish its execution and release the lock on the pen object.
* The Paper thread will acquire the lock on the paper object and finish its execution.
 */
public class PenAndPaperDeadLock {

    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Runnable runnablePen = () -> {
            System.out.println("Starting Pen thread");
//            synchronized (paper) {
                pen.writeWithPaperAndPen(paper);
//            }
        };

        Runnable runnablePaper = () -> {
            System.out.println("Starting Paper thread");
            synchronized (pen) {
                paper.writeWithPaperAndPen(pen);
            }
        };

        Thread t1 = new Thread(runnablePen);
        Thread t2 = new Thread(runnablePaper);
        t1.start();
        t2.start();
    }
}
