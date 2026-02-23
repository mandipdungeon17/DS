package org.java.multithreading.thread;

import java.util.concurrent.*;

public class ThreadInterruptionExample {

    public static void main(String[] args) throws InterruptedException {
        // Create and start the worker thread
        Thread worker = new Thread(new InterruptibleTask(), "Worker-Thread");
        worker.start();

        // Let it run for 3 seconds
        Thread.sleep(3000);

        System.out.println("Main thread interrupting worker...");
        worker.interrupt();  // Send interrupt signal

        // Wait for worker to finish
        worker.join();
        System.out.println("Worker thread has stopped gracefully");
    }
}

class InterruptibleTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Worker thread started: " + Thread.currentThread().getName());

        // Main loop - checks interrupt flag
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doWork();
            } catch (InterruptedException e) {
                // CRITICAL: InterruptedException clears the interrupt flag
                // We must restore it for the while loop to see it
                System.out.println("Caught InterruptedException, restoring interrupt status");
                Thread.currentThread().interrupt(); // Restore interrupt flag
                break; // Exit the loop
            }
        }

        System.out.println("Worker thread exiting gracefully");
    }

    private void doWork() throws InterruptedException {
        // Simulate some work
        System.out.println("Doing work...");

        // This is an interruptible blocking call
        Thread.sleep(500); // Will throw InterruptedException if interrupted

        // Simulate more computation (non-blocking)
        performCalculation();
    }

    private void performCalculation() {
        // Simulate CPU-intensive work
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i); // Some computation

            // Check interrupt flag periodically during long computations
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interruption detected during calculation");
                return; // Exit early
            }
        }
    }
}

/*
COMPLETE FLOW LOGIC FOR NOTES:

1. INTERRUPTION MECHANISM:
   - Thread.interrupt() sets interrupt flag on target thread
   - Does NOT forcefully stop the thread
   - Only works automatically with blocking operations (sleep, wait, join)

2. TWO WAYS TO CHECK INTERRUPTION:
   - isInterrupted(): Checks flag, keeps flag set
   - Thread.interrupted(): Checks flag, CLEARS flag when called

3. HANDLING NON-BLOCKING THREADS:
   - Must manually check interrupt flag: while (!Thread.currentThread().isInterrupted())
   - Check periodically in long-running loops
   - Thread continues running unless it checks the flag

4. HANDLING INTERRUPTEDEXCEPTION:
   - Thrown by blocking operations (sleep, wait, join) when interrupted
   - Exception handler CLEARS the interrupt flag automatically
   - MUST restore flag: Thread.currentThread().interrupt()
   - Then exit gracefully (return/break)

5. BEST PRACTICES:
   - Always check interrupt status in loops
   - Restore interrupt flag after catching InterruptedException
   - Exit gracefully, don't just ignore interruption
   - Use try-catch around interruptible blocking calls
   - Propagate interruption up the call stack when possible

6. COMMON MISTAKE:
   - Catching InterruptedException and continuing without restoring flag
   - This breaks the interruption mechanism for caller code
*/
