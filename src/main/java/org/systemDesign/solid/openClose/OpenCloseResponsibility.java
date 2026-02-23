package org.systemDesign.solid.openClose;

/**
 * The Open/Closed Principle (OCP) states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
 * This means that the behavior of a module can be extended without modifying its source code.
 * In practice, this often involves using interfaces or abstract classes to allow new functionality to be added through inheritance or composition.
 * This principle helps to reduce the risk of introducing bugs when changes are made and promotes code reusability.
 * For example, if you have a class that processes payments, you can extend it to support new payment methods without changing the original class.
 */
public class OpenCloseResponsibility {
    // Example of a class that adheres to the Open/Closed Principle
    public interface PaymentProcessor {
        void processPayment(double amount);
    }

    public class CreditCardProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            // Code to process credit card payment
        }
    }

    public class PayPalProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            // Code to process PayPal payment
        }
    }

    // New payment methods can be added without modifying existing code
    public class BitcoinProcessor implements PaymentProcessor {
        @Override
        public void processPayment(double amount) {
            // Code to process Bitcoin payment
        }
    }
}
