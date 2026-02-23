package org.systemDesign.solid.singleResponsibility;

/**
 * The Single Responsibility Principle (SRP) states that a class should have only one reason to change.
 * This means that a class should only have one job or responsibility.
 * If a class has more than one responsibility, it becomes coupled and changes to one responsibility can affect the other.
 * This can lead to a violation of the Open/Closed Principle, as the class may need to be modified for changes in either responsibility.
 * In practice, this means that classes should be designed to encapsulate a single responsibility, making them easier to maintain, test, and understand.
 * For example, a class that handles user authentication should not also handle user notifications.
 **/
public class SingleResponsibility {
    // Example of a class that violates the Single Responsibility Principle
    // This class has two responsibilities: managing user data and sending notifications
    public class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Method to save user to database
        public void save() {
            // Code to save user to database
        }

        // Method to send notification to user
        public void sendNotification(String message) {
            // Code to send notification
        }
    }

    // Example of a class that adheres to the Single Responsibility Principle
    public class UserRepository {
        public void save(User user) {
            // Code to save user to database
        }
    }

    public class NotificationService {
        public void sendNotification(User user, String message) {
            // Code to send notification
        }
    }
}
