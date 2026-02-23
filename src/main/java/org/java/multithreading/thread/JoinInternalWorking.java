package org.java.multithreading.thread;

/*
If you call join() on a thread that hasn't started yet, the method returns immediately without waiting. The thread doesn't terminate - it was never running!
Internal Implementation of Thread.join():
Step-by-Step Process:

Check thread.isAlive()

If thread is NOT alive (NEW or TERMINATED state) → return immediately
If thread IS alive → continue to step 2


Enter synchronized block on the thread object
Loop while thread is alive:

Call thread.wait() to suspend the calling thread
JVM automatically calls thread.notifyAll() when the thread dies
Calling thread wakes up and checks isAlive() again


Exit when thread is no longer alive

Key Points:
java// Simplified internal logic:
public final synchronized void join() throws InterruptedException {
    while (isAlive()) {  // ← If never started, this is false immediately!
        wait(0);         // ← This never executes for unstarted threads
    }
}
Thread States and join() Behavior:
Thread StateisAlive()join() BehaviorNEW (not started)falseReturns immediatelyRUNNABLE (running)trueWaits until completionBLOCKED/WAITINGtrueWaits until completionTERMINATED (finished)falseReturns immediately
The Magic Behind join():
The JVM has a special mechanism: when any thread dies, the JVM automatically calls notifyAll() on that thread object. This is how waiting threads wake up when join() completes.
So your original answer was incorrect - the thread doesn't terminate when you call join() on an unstarted thread. Instead, join() simply returns immediately because there's nothing to wait for!RetryClaude does not have the ability to run the code it generates yet.Claude can make mistakes. Please double-check responses.
 */
public class JoinInternalWorking {
    public static class ThreadJoinExplanation {

        public static void demonstrateJoinBehavior() throws InterruptedException {

            System.out.println("=== Testing join() on unstarted thread ===");

            Thread unstartedThread = new Thread(() -> {
                System.out.println("This will never print because thread is not started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            });

            // Check thread state
            System.out.println("Thread state before join: " + unstartedThread.getState()); // NEW
            System.out.println("Is thread alive: " + unstartedThread.isAlive()); // false

            // Call join() on unstarted thread
            long startTime = System.currentTimeMillis();
            unstartedThread.join(); // This returns immediately!
            long endTime = System.currentTimeMillis();

            System.out.println("join() returned in: " + (endTime - startTime) + "ms");
            System.out.println("Thread state after join: " + unstartedThread.getState()); // Still NEW
            System.out.println("Is thread alive: " + unstartedThread.isAlive()); // Still false

            System.out.println("\n=== Testing join() on started thread ===");

            Thread startedThread = new Thread(() -> {
                System.out.println("Started thread is running...");
                try {
                    Thread.sleep(2000); // Work for 2 seconds
                } catch (InterruptedException e) {
                    System.out.println("Started thread interrupted");
                }
                System.out.println("Started thread finished");
            });

            startedThread.start(); // Actually start the thread
            System.out.println("Thread state after start: " + startedThread.getState()); // RUNNABLE

            // Now join() will wait
            startTime = System.currentTimeMillis();
            startedThread.join(); // This waits for thread to complete
            endTime = System.currentTimeMillis();

            System.out.println("join() waited for: " + (endTime - startTime) + "ms");
            System.out.println("Thread state after completion: " + startedThread.getState()); // TERMINATED
        }
    }

    // Simplified version of how Thread.join() works internally
    static class ThreadJoinInternals {

        /**
         * This is a simplified version of how Thread.join() actually works in the JVM
         * The real implementation is in native code, but the logic is similar
         */
        public static void demonstrateJoinLogic() throws InterruptedException {

            System.out.println("\n=== How join() works internally ===");

            Thread workerThread = new Thread(() -> {
                try {
                    System.out.println("Worker thread started");
                    Thread.sleep(3000); // Simulate work
                    System.out.println("Worker thread finished");
                } catch (InterruptedException e) {
                    System.out.println("Worker thread interrupted");
                }
            });

            // Manual implementation of join() logic
            System.out.println("Manual join implementation:");
            manualJoin(workerThread);
        }

        /**
         * This shows the internal logic of Thread.join()
         * The actual implementation uses native methods, but this demonstrates the concept
         */
        public static void manualJoin(Thread thread) throws InterruptedException {
            // Step 1: Check if thread is alive (started and not finished)
            while (thread.isAlive()) {
                System.out.println("Thread is alive, waiting...");

                // Step 2: Wait using Object.wait() - this is the key mechanism
                synchronized (thread) {
                    // The JVM calls thread.notifyAll() when the thread dies
                    thread.wait(1000); // Wait up to 1 second, then check again
                }
            }

            System.out.println("Thread has died, join() complete");
        }
    }

    // The actual Thread.join() source code looks something like this:
    static class ActualJoinImplementation {
    /*
    // This is simplified from actual OpenJDK source
    public final synchronized void join(long millis) throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            // KEY POINT: If thread is not alive (not started or already dead),
            // this loop never executes and method returns immediately!
            while (isAlive()) {
                wait(0); // Wait indefinitely until notified
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
    */

        public static void explainJoinSteps() {
            System.out.println("\n=== Thread.join() Internal Steps ===");
            System.out.println("1. Check if thread.isAlive()");
            System.out.println("   - If NOT alive (NEW or TERMINATED state): return immediately");
            System.out.println("   - If alive (RUNNABLE, BLOCKED, WAITING, TIMED_WAITING): continue");
            System.out.println();
            System.out.println("2. Enter synchronized block on the thread object");
            System.out.println("3. Loop while thread.isAlive():");
            System.out.println("   - Call thread.wait() to suspend current thread");
            System.out.println("   - JVM automatically calls thread.notifyAll() when thread dies");
            System.out.println("   - Current thread wakes up and checks isAlive() again");
            System.out.println("4. When thread is no longer alive, exit loop and return");
        }
    }

    // Test class to demonstrate everything
    static class ThreadJoinTest {
        public static void main(String[] args) throws InterruptedException {

            ThreadJoinExplanation.demonstrateJoinBehavior();

            ThreadJoinInternals.demonstrateJoinLogic();

            ActualJoinImplementation.explainJoinSteps();

            System.out.println("\n=== Key Takeaways ===");
            System.out.println("1. join() on unstarted thread returns IMMEDIATELY");
            System.out.println("2. join() on running thread WAITS until thread completes");
            System.out.println("3. join() on already finished thread returns IMMEDIATELY");
            System.out.println("4. join() uses Object.wait()/notify() mechanism internally");
            System.out.println("5. The JVM automatically notifies when a thread dies");

            // Edge case testing
            System.out.println("\n=== Edge Case: join() on already finished thread ===");

            Thread quickThread = new Thread(() -> {
                System.out.println("Quick thread runs and finishes immediately");
            });

            quickThread.start();
            Thread.sleep(100); // Ensure it finishes

            long startTime = System.currentTimeMillis();
            quickThread.join(); // Should return immediately
            long endTime = System.currentTimeMillis();

            System.out.println("join() on finished thread took: " + (endTime - startTime) + "ms");
            System.out.println("Thread state: " + quickThread.getState()); // TERMINATED
        }
    }
}
