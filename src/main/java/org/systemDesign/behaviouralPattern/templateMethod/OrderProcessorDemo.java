package org.systemDesign.behaviouralPattern.templateMethod;

public class OrderProcessorDemo {
    public static void main(String[] args) {
        System.out.println("=== Physical Product Order ===");
        OrderProcessor physical = new PhysicalProductOrder();
        physical.processOrder();

        System.out.println("=== Digital Product Order ===");
        OrderProcessor digital = new DigitalProductOrder();
        digital.processOrder();

        System.out.println("=== Subscription Order ===");
        OrderProcessor subscription = new SubscriptionOrder();
        subscription.processOrder();
    }
}
// Abstract class
abstract class OrderProcessor {
    // Template method
    public final void processOrder() {
        validateOrder();
        calculateTotal();
        processPayment();
        if (requiresShipping()) { // Hook
            arrangeShipping();
        }
        sendConfirmation();
        System.out.println("Order processed successfully!\n");
    }
    // Common steps (concrete methods)
    private void validateOrder() {
        System.out.println("Validating order details");
    }
    private void sendConfirmation() {
        System.out.println("Sending order confirmation email");
    }
    // Steps that vary (abstract methods)
    protected abstract void calculateTotal();
    protected abstract void processPayment();
    // Optional step (hook)
    protected void arrangeShipping() {
        System.out.println("Arranging shipping");
    }
    protected boolean requiresShipping() {
        return true;
    }
}

// Concrete class: Physical Product Order
class PhysicalProductOrder extends OrderProcessor {
    protected void calculateTotal() {
        System.out.println("Calculating: Product price + Tax + Shipping");
    }
    protected void processPayment() {
        System.out.println("Processing credit card payment");
    }
}

// Concrete class: Digital Product Order
class DigitalProductOrder extends OrderProcessor {
    protected void calculateTotal() {
        System.out.println("Calculating: Product price + Tax (no shipping)");
    }
    protected void processPayment() {
        System.out.println("Processing PayPal payment");
    }
    // Override hook - no shipping for digital products
    protected boolean requiresShipping() {
        return false;
    }
}

// Concrete class: Subscription Order
class SubscriptionOrder extends OrderProcessor {
    protected void calculateTotal() {
        System.out.println("Calculating: Monthly subscription fee");
    }
    protected void processPayment() {
        System.out.println("Setting up recurring payment");
    }
    protected boolean requiresShipping() {
        return false;
    }
}