package org.java.multithreading.threadLocal;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
ThreadLocal is a class in Java that provides thread-local variables.
Each thread accessing such a variable (via its get or set method) has its own, independently initialized copy of the variable.
ThreadLocal instances are typically private static fields in classes that wish to associate state with a thread (e.g., a user ID or Transaction ID).
This class is useful in scenarios where you want to avoid sharing state between threads, such as in web applications where each request is handled by a separate thread.
Example: In web applications, you might use ThreadLocal to store user session information that is specific to each thread handling a request.
 */
public class ThreadLocalExample {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };

    public void method1() {
        SimpleDateFormat sf = threadLocal.get(); // Same instance for this thread
        // use sf
    }

    public void method2() {
        SimpleDateFormat sf = threadLocal.get(); // SAME instance as method1 (if same thread)
        // use sf

    }

    public static ThreadLocal<AtomicInteger> integerThreadLocal = ThreadLocal.withInitial(AtomicInteger::new);

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            SimpleDateFormat sf = threadLocal.get();
            sf.applyPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("Thread 1: "+sf);
        });

        Thread t2 = new Thread(()->{
            SimpleDateFormat sf = threadLocal.get();
            System.out.println("Thread 2: "+sf);
        });

        t1.start();
        t2.start();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        AtomicInteger atomicInteger = integerThreadLocal.get();
        for (int i=0; i<3; i++){
            int finalI = i;
            executorService.submit(
                    ()->{
                        // Each thread will have its own instance of AtomicInteger. Get() method will return the instance specific to the current thread.
                        AtomicInteger atomicInteger = integerThreadLocal.get();
                        for(int j=0; j<3; j++){
                            System.out.println("Thread "+ finalI +": "+atomicInteger.incrementAndGet());
                        }
                    }
            );
        }
        executorService.shutdown();
    }
}

class Demo {
    private static final ThreadLocal<String> tl = new ThreadLocal<>(); // default: null

    private static final ThreadLocal<String> tlWith =
            ThreadLocal.withInitial(() -> "created-" + Thread.currentThread().getName());

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " first get (default): " + tl.get());
            System.out.println(Thread.currentThread().getName() + " second get (default): " + tl.get());
            tl.remove();
            System.out.println(Thread.currentThread().getName() + " after remove get (default): " + tl.get());

            System.out.println(Thread.currentThread().getName() + " withInitial first: " + tlWith.get());
            System.out.println(Thread.currentThread().getName() + " withInitial second: " + tlWith.get());
            //Removing value for current thread from the ThreadLocal map
            tlWith.remove();
            // After remove, it will call initialValue() to create new value for current thread
            System.out.println(Thread.currentThread().getName() + " after remove get " + tlWith.get());
            System.out.println(Thread.currentThread().getName() + " withInitial first: " + tl.get());
            System.out.println(Thread.currentThread().getName() + " withInitial second: " + tl.get());
        };
        new Thread(r, "T1").start();
        new Thread(r, "T2").start();
    }
}

//Difference
class FormatterDemo {
    // One formatter per thread, reused
    private static final ThreadLocal<SimpleDateFormat> FMT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    // Creates a new formatter every call
    private static String withoutThreadLocal(long epoch) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(epoch);
    }

    // Reuses the thread's formatter
    private static String withThreadLocal(long epoch) {
        return FMT.get().format(epoch);
    }

    public static void main(String[] args) {
        // Simulate many calls on same thread (as in a thread pool worker)
        for (int i = 0; i < 3; i++) {
            System.out.println("Without TL: " + withoutThreadLocal(System.currentTimeMillis()));
            System.out.println("With TL:    " + withThreadLocal(System.currentTimeMillis()));
        }
        // If in container / pool and done with context-sensitive data:
        FMT.remove();
    }
}


