package org.systemDesign.structuralPattern.adapter;

public class PaymentGatewayDemo {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        // Using PayPal through adapter
        PaymentProcessor paypal = new PayPalAdapter();
        service.makePayment(paypal, 100.0);

        System.out.println();

        // Using Stripe through adapter
        PaymentProcessor stripe = new StripeAdapter();
        service.makePayment(stripe, 250.0);
    }
}
// Adaptee 1 - PayPal (third-party library)
class PayPalAPI {
    public void sendPayment(double amount) {
        System.out.println("PayPal: Processing $" + amount);
    }

    public String checkPaymentStatus() {
        return "PayPal: Payment successful";
    }
}

// Adaptee 2 - Stripe (third-party library)
class StripeAPI {
    public void makePayment(double amount) {
        System.out.println("Stripe: Charging $" + amount);
    }

    public String verifyPayment() {
        return "Stripe: Payment verified";
    }
}
// Target interface (what our system expects)
interface PaymentProcessor {
    void processPayment(double amount);
    String getStatus();
}
// Adapter for PayPal
class PayPalAdapter implements PaymentProcessor{
    private final PayPalAPI payPalAPI;
    public PayPalAdapter(){
        this.payPalAPI = new PayPalAPI();
    }
    @Override
    public void processPayment(double amount) {
        payPalAPI.sendPayment(amount);
    }

    @Override
    public String getStatus() {
        return payPalAPI.checkPaymentStatus();
    }
}
// Adapter for Stripe
class StripeAdapter implements PaymentProcessor {
    private final StripeAPI stripe;

    public StripeAdapter() {
        this.stripe = new StripeAPI();
    }

    public void processPayment(double amount) {
        stripe.makePayment(amount);
    }

    public String getStatus() {
        return stripe.verifyPayment();
    }
}
// Client code
class PaymentService{
    public void makePayment(PaymentProcessor paymentProcessor, double amount){
        paymentProcessor.processPayment(amount);
        System.out.println(paymentProcessor.getStatus());
    }
}

