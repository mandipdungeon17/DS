package org.java.multithreading.locking;

public class ClassLevelLocking {
     static class UserIdGenerator {
        private static int nextId = 1;
        private static int totalUsers = 0;

        // Class-level locking - one lock for entire class
        public static synchronized int generateUserId() {
            return nextId++;
        }

        public static synchronized void incrementUserCount() {
            totalUsers++;
            System.out.println("Total users: " + totalUsers);
        }

        public static synchronized int getTotalUsers() {
            return totalUsers;
        }

        // Alternative syntax for class-level locking
        public static int generateUserIdAlternative() {
            synchronized(UserIdGenerator.class) {
                return nextId++;
            }
        }
    }

     static class ClassLevelDemo {
        public static void main(String[] args) {
            // All these threads compete for the SAME CLASS LOCK
            Thread t1 = new Thread(() -> {
                int id = UserIdGenerator.generateUserId();
                System.out.println("Generated ID: " + id);
            });

            Thread t2 = new Thread(UserIdGenerator::incrementUserCount);

            Thread t3 = new Thread(() -> {
                int total = UserIdGenerator.getTotalUsers();
                System.out.println("Current total: " + total);
            });

            // These will execute SEQUENTIALLY, not concurrently
            t1.start(); t2.start(); t3.start();
        }
    }
}
