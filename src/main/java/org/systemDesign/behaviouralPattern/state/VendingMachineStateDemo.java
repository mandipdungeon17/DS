package org.systemDesign.behaviouralPattern.state;

public class VendingMachineStateDemo {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(3);
        System.out.println("=== Test 1: Normal flow ===");
        machine.selectProduct();
        machine.insertMoney();
        machine.selectProduct();
        System.out.println("\n=== Test 2: Refund ===");
        machine.insertMoney();
        machine.refund();
        System.out.println("\n=== Test 3: Out of stock ===");
        VendingMachine emptyMachine = new VendingMachine(0);
        emptyMachine.insertMoney();
        emptyMachine.selectProduct();
    }
}
//State interface
interface VendingMachineState {
    void insertMoney(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispense(VendingMachine machine);
    void refund(VendingMachine machine);
}
//Context
class VendingMachine {
    private VendingMachineState currentState;
    private final VendingMachineState noMoneyState;
    private final VendingMachineState hasMoneyState;
    private final VendingMachineState dispensingState;
    private int productCount;
    public VendingMachine(int productCount){
        this.productCount = productCount;
        // Initialize all states
        this.noMoneyState = new NoMoneyState();
        this.hasMoneyState = new HasMoneyState();
        this.dispensingState = new DispensingState();
        // Initial state
        this.currentState = noMoneyState;
    }
    public void setState(VendingMachineState state){
        this.currentState = state;
    }
    public void insertMoney(){
        this.currentState.insertMoney(this);
    }
    public void selectProduct(){
        this.currentState.selectProduct(this);
    }
    public void dispense(){
        this.currentState.dispense(this);
    }
    public void refund() {
        currentState.refund(this);
    }
    public void releaseProduct(){
        if(productCount > 0){
            System.out.println("Product dispensed!");
            productCount--;
        }
    }
    public int getProductCount() {
        return productCount;
    }
    // Getters for states
    public VendingMachineState getNoMoneyState() { return noMoneyState; }
    public VendingMachineState getHasMoneyState() { return hasMoneyState; }
    public VendingMachineState getDispensingState() { return dispensingState; }
}
// Concrete State 1: No Money
class NoMoneyState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money inserted");
        machine.setState(machine.getHasMoneyState());
    }
    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please insert money first");
    }
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please insert money first");
    }

    @Override
    public void refund(VendingMachine machine) {
        System.out.println("No money to refund");
    }
}
// Concrete State 2: Has Money
class HasMoneyState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Money already inserted");
    }
    @Override
    public void selectProduct(VendingMachine machine) {
        if(machine.getProductCount() > 0){
            System.out.println("Product selected");
            machine.setState(machine.getDispensingState());
            machine.dispense();
        } else {
            System.out.println("Out of stock! Refunding money");
            machine.setState(machine.getNoMoneyState());
        }
    }
    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please select a product first");
    }
    @Override
    public void refund(VendingMachine machine) {
        System.out.println("Money refunded");
        machine.setState(machine.getNoMoneyState());
    }
}
// Concrete State 3: No Money
class DispensingState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine machine) {
        System.out.println("Please wait, dispensing in progress");
    }
    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Please wait, dispensing in progress");
    }
    @Override
    public void dispense(VendingMachine machine) {
        machine.releaseProduct();
        machine.setState(machine.getNoMoneyState());
    }
    @Override
    public void refund(VendingMachine machine) {
        System.out.println("Cannot refund, dispensing in progress");
    }
}
