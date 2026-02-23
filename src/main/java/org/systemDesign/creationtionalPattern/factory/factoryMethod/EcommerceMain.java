package org.systemDesign.creationtionalPattern.factory.factoryMethod;

public class EcommerceMain {
    public static void main(String[] args) {
        PaymentFactory paymentFactory = new CreditCardFactory();
        paymentFactory.makePayment(1000.0);

        paymentFactory = new PayPalFactory();
        paymentFactory.makePayment(2000.0);

        paymentFactory = new UPIFactory();
        paymentFactory.makePayment(3000.0);
    }
}
