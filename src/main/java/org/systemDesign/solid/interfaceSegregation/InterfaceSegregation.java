package org.systemDesign.solid.interfaceSegregation;

/**
 * The Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use.
 * This means that interfaces should be designed to be specific to the needs of the clients that use them, rather than being general-purpose.
 * In practice, this often involves creating multiple smaller interfaces rather than a single large interface.
 * This principle helps to reduce the impact of changes in one part of the system on other parts and promotes better code organization.
 * For example, if you have a class that implements an interface with many methods, but only uses a few of them, it can lead to unnecessary complexity and confusion.
 */
public class InterfaceSegregation {
    // Example of a class that adheres to the Interface Segregation Principle
    public interface Printer {
        void print(String document);
    }

    public interface Scanner {
        void scan(String document);
    }

    public interface Fax {
        void fax(String document);
    }

    public class MultiFunctionPrinter implements Printer, Scanner, Fax {
        @Override
        public void print(String document) {
            System.out.println("Printing: " + document);
        }

        @Override
        public void scan(String document) {
            System.out.println("Scanning: " + document);
        }

        @Override
        public void fax(String document) {
            System.out.println("Faxing: " + document);
        }
    }

    // Example of a class that violates the Interface Segregation Principle
    public class OldPrinter implements Printer, Scanner {
        @Override
        public void print(String document) {
            System.out.println("Printing: " + document);
        }

        @Override
        public void scan(String document) {
            throw new UnsupportedOperationException("OldPrinter does not support scanning");
        }
    }
}
