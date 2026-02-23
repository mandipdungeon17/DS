package org.systemDesign.creationtionalPattern.factory.factoryMethod;

//Factory Method: Uses inheritance, extensible, follows Open-Closed Principle
public abstract class PaymentFactory {
    public abstract PaymentProcessor createProcessor();

    public void makePayment(double amount){
        PaymentProcessor paymentProcessor = createProcessor();
        paymentProcessor.processPayment(amount);
    }
}

class CreditCardFactory extends PaymentFactory {

    @Override
    public PaymentProcessor createProcessor() {
        return new CreditCardProcessor();
    }
}

class PayPalFactory extends PaymentFactory {

    @Override
    public PaymentProcessor createProcessor() {
        return new PayPalProcessor();
    }
}

class UPIFactory extends PaymentFactory {

    @Override
    public PaymentProcessor createProcessor() {
        return new UPIProcessor();
    }
}