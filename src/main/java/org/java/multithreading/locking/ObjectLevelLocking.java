package org.java.multithreading.locking;

public class ObjectLevelLocking {

    static class BankAccount {
        private double balance;
        private final String accountNumber;

        public BankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        // Object-level locking - each account has its own lock
        public synchronized void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited " + amount + " to " + accountNumber +
                    ". New balance: " + balance);
        }

        public void withdraw(double amount) {
            synchronized (this) {
                if (balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrew " + amount + " from " + accountNumber +
                            ". New balance: " + balance);
                }
            }
        }

        public synchronized double getBalance() {
            return balance;
        }
    }

    class ObjectLevelDemo {
        public static void main(String[] args) {
            BankAccount account1 = new BankAccount("ACC001", 1000);
            BankAccount account2 = new BankAccount("ACC002", 2000);

            // These can run CONCURRENTLY - different locks
            Thread t1 = new Thread(() -> account1.deposit(500));    // Lock on account1
            Thread t2 = new Thread(() -> account2.withdraw(300));   // Lock on account2

            // These will run SEQUENTIALLY - same lock
            Thread t3 = new Thread(() -> account1.withdraw(200));   // Lock on account1
            Thread t4 = new Thread(() -> account1.getBalance());    // Lock on account1

            t1.start(); t2.start(); t3.start(); t4.start();
        }
    }
}
