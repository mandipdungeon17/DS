package org.systemDesign.behaviouralPattern.strategy;

import java.util.ArrayList;
import java.util.List;

public class PaymentStrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop");
        cart.addItem("Smartphone");
        // Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardStrategy("1234-5678-9012-3456"));
        cart.checkout(50000);
        // Change strategy - Pay with UPI
        cart.setPaymentStrategy(new UPIStrategy("user@paytm"));
        cart.checkout(50000);
        // Change strategy - Pay with PayPal
        cart.setPaymentStrategy(new PayPalStrategy("user@email.com"));
        cart.checkout(50000);
    }
}
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}
// Concrete Strategies
class CreditCardStrategy implements PaymentStrategy {
    private final String cardNumber;
    public CreditCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending with " +
                cardNumber.substring(cardNumber.length() - 4));
    }
}
class PayPalStrategy implements PaymentStrategy {
    private final String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account " + email);
    }
}
class UPIStrategy implements PaymentStrategy {
    private final String upiId;

    public UPIStrategy(String upiId) {
        this.upiId = upiId;
    }

    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI ID: " + upiId);
    }
}
// Context class
class ShoppingCart{
    private final List<String> items;
    private PaymentStrategy paymentStrategy;
    public ShoppingCart(){
        this.items = new ArrayList<>();
    }
    public void addItem(String item){
        items.add(item);
    }
    // Set strategy at runtime
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method");
            return;
        }
        System.out.println("Items: " + items);
        paymentStrategy.pay(amount);
        System.out.println("Payment successful!\n");
    }
}