package org.systemDesign.solid.dependencyInversion;

/**
 * The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules.
 * Both should depend on abstractions (e.g., interfaces).
 * This principle helps to reduce the coupling between different parts of the system, making it easier to change and maintain.
 * In practice, this often involves using dependency injection to provide dependencies to classes rather than creating them directly.
 * This allows for greater flexibility and testability, as dependencies can be easily swapped out or mocked.
 * For example, if a class depends on a specific implementation of a service, it can be difficult to change that implementation without modifying the class itself.
 */
public class DependencyInversion {
    // Example of a class that adheres to the Dependency Inversion Principle
    public interface NotificationService {
        void sendNotification(String message);
    }

    public class EmailNotificationService implements NotificationService {
        @Override
        public void sendNotification(String message) {
            System.out.println("Sending email notification: " + message);
        }
    }

    public class SMSNotificationService implements NotificationService {
        @Override
        public void sendNotification(String message) {
            System.out.println("Sending SMS notification: " + message);
        }
    }

    public class User {
        private final NotificationService notificationService;

        public User(NotificationService notificationService) {
            this.notificationService = notificationService;
        }

        public void notifyUser(String message) {
            notificationService.sendNotification(message);
        }
    }

    public static void main(String[] args) {
        // Example usage of the Dependency Inversion Principle
        DependencyInversion dip = new DependencyInversion();
        NotificationService emailService = dip.new EmailNotificationService();
        User user = dip.new User(emailService);
        user.notifyUser("Hello, User!"); // Sends email notification

        NotificationService smsService = dip.new SMSNotificationService();
        user = dip.new User(smsService);
        user.notifyUser("Hello, User!"); // Sends SMS notification
    }
}
