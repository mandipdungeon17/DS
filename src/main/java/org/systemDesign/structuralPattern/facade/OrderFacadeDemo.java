package org.systemDesign.structuralPattern.facade;

public class OrderFacadeDemo {
    public static void main(String[] args) {
        OrderFacade facade = new OrderFacade();
        boolean success = facade.placeOrder("CUST001",
                "PROD123",
                2,
                "123 Main St, Mumbai",
                2500.0);
        if (success) {
            System.out.println("Order completed!");
        }
    }
}
class OrderFacade{
    private final Inventory inventory;
    private final PaymentGateway payment;
    private final ShippingService shipping;
    private final NotificationService notification;

    OrderFacade() {
        this.inventory = new Inventory();
        this.payment = new PaymentGateway();
        this.shipping = new ShippingService();
        this.notification = new NotificationService();
    }
    // Simplified order placement
    public boolean placeOrder(String customerId, String productId,
                              int quantity, String address, double amount) {
        System.out.println("\n=== Starting Order Process ===\n");
        // Step 1: Check inventory
        if(!inventory.checkStock(productId, quantity)){
            System.out.println("Error: Product out of stock");
            return false;
        }
        // Step 2: Process payment
        if(!payment.processPayment(customerId, amount)){
            System.out.println("Error: Payment failed");
            return false;
        }
        // Step 3: Reduce stock
        inventory.reduceStock(productId, quantity);
        // Step 4: Schedule shipping
        String trackingId = shipping.scheduleShipping(customerId, address);
        // Step 5: Send notification
        notification.sendOrderConfirmation(customerId, trackingId);
        System.out.println("\n=== Order Placed Successfully ===\n");
        return true;
    }
}
// Subsystem 1: Inventory
class Inventory{
    public boolean checkStock(String productId, int quantity){
        System.out.println("Inventory: Checking stock for " + productId);
        return true; // Simplified
    }
    public void reduceStock(String productId, int quantity) {
        System.out.println("Inventory: Reducing stock for " + productId);
    }
}
// Subsystem 2: Payment
class PaymentGateway {
    public boolean processPayment(String customerId, double amount) {
        System.out.println("Payment: Processing $" + amount + " for customer " + customerId);
        return true;
    }
}
// Subsystem 3: Shipping
class ShippingService {
    public String scheduleShipping(String customerId, String address) {
        System.out.println("Shipping: Scheduling delivery to " + address);
        return "SHIP123456";
    }
}
// Subsystem 4: Notification
class NotificationService {
    public void sendOrderConfirmation(String customerId, String orderId) {
        System.out.println("Notification: Sending confirmation for order " + orderId);
    }
}