package org.systemDesign.structuralPattern.proxy;
/*
    Protection Proxy Pattern Example
    In this example, we have a BankAccount interface that defines the operations for a bank account.
    The RealBankAccount class implements the actual bank account functionality.
    The BankAccountProxy class acts as a protection proxy, controlling access to the RealBankAccount based on user roles.
 */
public class BankAccountProxyDemo {
    public static void main(String[] args) {
        // Owner access
        System.out.println("=== OWNER Access ===");
        BankAccount ownerAccount = new BankAccountProxy(1000, "OWNER");
        ownerAccount.deposit(5000);
        ownerAccount.withdraw(2000);
        System.out.println("Owner Balance: $" + ownerAccount.getBalance());

        System.out.println("\n=== GUEST Access ===");
        BankAccount guestAccount = new BankAccountProxy(10000, "GUEST");
        guestAccount.deposit(3000); // Allowed
        guestAccount.withdraw(2000); // Denied
        System.out.println("Guest Balance: $" + guestAccount.getBalance()); // Denied
    }
}
// Subject interface
interface BankAccount {
    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
}
// Real Subject
class RealBankAccount implements BankAccount{
    private double balance;
    public RealBankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    @Override
    public void withdraw(double amount) {
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }
    @Override
    public double getBalance() {
        return balance;
    }
}
//Protection Proxy
class BankAccountProxy implements BankAccount{
    private final RealBankAccount realBankAccount;
    private final String userRole;
    public BankAccountProxy(double initialBalance, String userRole) {
        this.realBankAccount = new RealBankAccount(initialBalance);
        this.userRole = userRole;
    }
    @Override
    public void withdraw(double amount) {
        if(this.userRole.equals("OWNER") || userRole.equals("ADMIN")){
            realBankAccount.withdraw(amount);
        }else System.out.println("Access Denied: You do not have permission to withdraw funds.");
    }

    @Override
    public void deposit(double amount) {
        // Anyone can deposit
        realBankAccount.deposit(amount);
    }

    @Override
    public double getBalance() {
        if(this.userRole.equals("OWNER") || userRole.equals("ADMIN")){
           return realBankAccount.getBalance();
        }else System.out.println("Access Denied: You do not have permission to withdraw funds.");
        return 0;
    }
}
