package org.java.multithreading.locking;

public class SynchronizedLockCounter {
    class Parent {
        public synchronized void parentMethod() {
            // synchronized(this) - lock count becomes 2
            System.out.println("In parent method");
            // lock count decreases to 1 when method exits
        }
    }

    class Child extends Parent {
        public synchronized void childMethod() {
            // synchronized(this) - lock count becomes 1
            System.out.println("In child method");

            parentMethod(); // Same thread, same 'this' object
            // No blocking! Lock count increments to 2, then back to 1

            System.out.println("Back in child method");
            // lock count decreases to 0 when method exits - lock released
        }
    }
}
