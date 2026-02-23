package org.systemDesign.behaviouralPattern.command;

import java.util.ArrayList;
import java.util.List;

public class StockCommandDemo {
    public static void main(String[] args) {
        // Receivers
        Stock reliance = new Stock("Reliance", 100);
        Stock tcs = new Stock("TCS", 50);
        // Commands
        Order buyReliance = new BuyStock(reliance);
        Order sellReliance = new SellStock(reliance);
        Order buyTCS = new BuyStock(tcs);
        //Invoker
        Broker broker = new Broker();
        // Queue commands
        broker.takeOrder(buyReliance);
        broker.takeOrder(buyTCS);
        broker.takeOrder(sellReliance);
        // Execute all at once
        broker.placeOrder();
    }
}
// Command interface
interface Order {
    void execute();
}
//Receiver
class Stock {
    private final String name;
    private final int quantity;
    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public void buy() {
        System.out.println("Stock [Name: " + name + ", Quantity: " +
                quantity + "] bought");
    }
    public void sell() {
        System.out.println("Stock [Name: " + name + ", Quantity: " +
                quantity + "] sold");
    }
}
// Concrete Commands
class BuyStock implements Order {
    private final Stock stock;
    public BuyStock(Stock stock){
        this.stock = stock;
    }
    @Override
    public void execute() {
        stock.buy();
    }
}
// Concrete Commands
class SellStock implements Order {
    private final Stock stock;
    public SellStock(Stock stock){
        this.stock = stock;
    }
    @Override
    public void execute() {
        stock.sell();
    }
}
// Invoker (Broker)
class Broker {
    private final List<Order> orderList;
    public Broker (){
        this.orderList = new ArrayList<>();
    }
    public void takeOrder(Order order){
        orderList.add(order);
        System.out.println("Order taken");
    }
    public void placeOrder(){
        System.out.println("\n=== Executing all orders ===");
        for(Order order : this.orderList){
            order.execute();
        }
        orderList.clear();
        System.out.println("All orders executed\n");
    }
}