package org.systemDesign.behaviouralPattern.state;
// Usage
public class OrderStateDemo {
    public static void main(String[] args) {
        System.out.println("=== Order Flow 1: Normal ===");
        Order order1 = new Order("ORD001");
        order1.confirm();
        order1.ship();
        order1.deliver();
        order1.cancel(); // Should fail
        System.out.println("\n=== Order Flow 2: Cancelled ===");
        Order order2 = new Order("ORD002");
        order2.confirm();
        order2.cancel();
        order2.ship(); // Should fail
        System.out.println("\n=== Order Flow 3: Invalid transitions ===");
        Order order3 = new Order("ORD003");
        order3.ship(); // Should fail - not confirmed
        order3.deliver(); // Should fail - not confirmed
    }
}
// State interface
interface OrderState {
    void confirmOrder(Order order);
    void shipOrder(Order order);
    void deliverOrder(Order order);
    void cancelOrder(Order order);
}
// Context
class Order {
    private final String orderId;
    private OrderState currentState;

    private final OrderState pendingState;
    private final OrderState confirmedState;
    private final OrderState shippedState;
    private final OrderState deliveredState;
    private final OrderState cancelledState;

    public Order(String orderId){
        this.orderId = orderId;
        // Initialize states
        this.pendingState = new PendingState();
        this.confirmedState = new ConfirmedState();
        this.shippedState = new ShippedState();
        this.deliveredState = new DeliveredState();
        this.cancelledState = new CancelledState();
        // Initial state
        this.currentState = this.pendingState;
        System.out.println("Order " + orderId + " created in PENDING state");
    }
    public void setState(OrderState state){ this.currentState = state; }

    public void confirm(){ this.currentState.confirmOrder(this); }
    public void ship(){ this.currentState.shipOrder(this); }
    public void deliver(){ this.currentState.deliverOrder(this); }
    public void cancel(){ this.currentState.cancelOrder(this); }

    public String getOrderId(){ return this.orderId; }

    // Getters for states
    public OrderState getPendingState() { return pendingState; }
    public OrderState getConfirmedState() { return confirmedState; }
    public OrderState getShippedState() { return shippedState; }
    public OrderState getDeliveredState() { return deliveredState; }
    public OrderState getCancelledState() { return cancelledState; }
}
// Concrete Classes
class PendingState implements OrderState{
    @Override
    public void confirmOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": PENDING -> CONFIRMED");
        order.setState(order.getConfirmedState());
    }
    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship - order not confirmed yet");
    }
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver - order not confirmed yet");
    }
    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": PENDING -> CANCELLED");
        order.setState(order.getCancelledState());
    }
}
class ConfirmedState implements OrderState{
    @Override
    public void confirmOrder(Order order) {
        System.out.println("Order already confirmed");
    }
    @Override
    public void shipOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": CONFIRMED -> SHIPPED");
        order.setState(order.getShippedState());
    }
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver - order not shipped yet");
    }
    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": CONFIRMED -> CANCELLED");
        order.setState(order.getCancelledState());
    }
}
class ShippedState implements OrderState{
    @Override
    public void confirmOrder(Order order) {
        System.out.println("Order already confirmed and shipped");
    }
    @Override
    public void shipOrder(Order order) {
        System.out.println("Order already shipped");
    }
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": SHIPPED -> DELIVERED");
        order.setState(order.getDeliveredState());
    }
    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel - order already shipped");
    }
}
class DeliveredState implements OrderState{
    @Override
    public void confirmOrder(Order order) {
        System.out.println("Order already delivered");
    }
    @Override
    public void shipOrder(Order order) {
        System.out.println("Order already delivered");
    }
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order already delivered");
    }
    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel - order already delivered");
    }
}
class CancelledState implements OrderState{
    @Override
    public void confirmOrder(Order order) {
        System.out.println("Cannot confirm - order cancelled");
    }
    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship - order cancelled");
    }
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver - order cancelled");
    }
    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order already cancelled");
    }
}