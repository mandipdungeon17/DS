package org.systemDesign.creationtionalPattern.factory.simpleFactory;

//Simple Factory: Static method, not extensible, violates Open-Closed Principle
public class NotificationFactory {
    public Notification createNotification(String type){
        return switch (type) {
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SMSNotification();
            case "PUSH" -> new PushNotification();
            default -> {
                System.out.println("Invalid Input");
                //yield is used to return a value from a switch expression block, while return exits the entire method.
                yield null;
            }
        };
    }
}

class Main {
    public static void main(String[] args) {
        NotificationFactory nf = new NotificationFactory();
        Notification notification = nf.createNotification("SMS");
        notification.send("Welcome to our platform");
        notification = nf.createNotification("EMAIL");
        notification.send("Welcome to our platform");
        notification = nf.createNotification("PUSH");
        notification.send("Welcome to our platform");

        notification = nf.createNotification("LOL");
        notification.send("Welcome to our platform");
    }
}
