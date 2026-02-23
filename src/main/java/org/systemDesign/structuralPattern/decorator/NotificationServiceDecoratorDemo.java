package org.systemDesign.structuralPattern.decorator;

public class NotificationServiceDecoratorDemo {
    public static void main(String[] args) {
        String message = "Server is down";
        //Email Only
        Notifier notifier = new EmailNotifier("admin@company.com");
        notifier.send(message);
        System.out.println("\n--- Multi-channel notification ---\n");
        // Email + SMS + Slack + WhatsApp
        notifier = new WhatsAppDecorator(
                new SlackDecorator(
                        new SMSDecorator(
                                new EmailNotifier("admin@company.com"),
                                "+91-9876543210"),
                        "#alerts"),
                "+91-9876543210");
        notifier.send(message);
    }
}
// Component interface
interface Notifier{
    void send(String message);
}
// Concrete Component
class EmailNotifier implements Notifier{
    private final String email;
    public EmailNotifier(String email){
        this.email = email;
    }
    @Override
    public void send(String message) {
        System.out.println("Sending email to " + email + ": " + message);
    }
}
abstract class NotifierDecorator implements Notifier{
    protected final Notifier wrappee;
    public NotifierDecorator(Notifier notifier){
        this.wrappee = notifier;
    }
    public void send(String message) {
        wrappee.send(message);
    }
}
// SMS Decorator
class SMSDecorator extends NotifierDecorator{
    private final String phoneNumber;
    public SMSDecorator(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }
    public void send(String message) {
        wrappee.send(message);
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
// Slack Decorator
class SlackDecorator extends NotifierDecorator{
    private final String slackChannel;
    public SlackDecorator(Notifier notifier, String slackChannel) {
        super(notifier);
        this.slackChannel = slackChannel;
    }
    public void send(String message) {
        wrappee.send(message);
        System.out.println("Sending to Slack Channel " + slackChannel + ": " + message);
    }
}
// WhatsApp Decorator
class WhatsAppDecorator extends NotifierDecorator{
    private final String whatsappNumber;
    public WhatsAppDecorator(Notifier notifier, String whatsappNumber){
        super(notifier);
        this.whatsappNumber = whatsappNumber;
    }
    public void send(String message){
        wrappee.send(message);
        System.out.println("Sending WhatsApp to " + whatsappNumber + ": " + message);
    }
}