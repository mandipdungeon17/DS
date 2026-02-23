package org.systemDesign.behaviouralPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class StockObserverDemo {
    public static void main(String[] args) {
        // Create observers
        MobileApp user1 = new MobileApp("User1");
        MobileApp user2 = new MobileApp("User2");
        WebDashboard dashboard = new WebDashboard();
        AlertSystem alert = new AlertSystem(2600);

        Stock reliance = new Stock("RELIANCE", 2500);
        // Register observers
        reliance.addObserver(user1);
        reliance.addObserver(user2);
        reliance.addObserver(dashboard);
        reliance.addObserver(alert);
        // Stock price changes - all observers notified
        reliance.setPrice(2550);
        reliance.setPrice(2620); // Will trigger alert
        reliance.setPrice(2580);
        reliance.removeObserver(user1);
        reliance.setPrice(3000);
        System.out.println(reliance.getPrice());
    }
}
// Observer interface
interface StockObserver{
    void update(String stockSymbol, double price);
}
// Concrete Observers
class MobileApp implements StockObserver {
    private final String userId;
    public MobileApp(String userId) {
        this.userId = userId;
    }
    public void update(String stockSymbol, double price) {
        System.out.println("[Mobile App - " + userId + "] " +
                stockSymbol + " updated to $" + price);
    }
}
class WebDashboard implements StockObserver {
    public void update(String stockSymbol, double price) {
        System.out.println("[Web Dashboard] Refreshing " +
                stockSymbol + " display: $" + price);
    }
}

class AlertSystem implements StockObserver {
    private final double threshold;
    public AlertSystem(double threshold) {
        this.threshold = threshold;
    }
    public void update(String stockSymbol, double price) {
        if (price > threshold) {
            System.out.println("[ALERT] " + stockSymbol +
                    " crossed threshold! Current: $" + price);
        }
    }
}
// Subject
class Stock {
    private final List<StockObserver> observers;
    private final String symbol;
    private double price;
    public Stock(String symbol, double initialPrice){
        this.observers = new ArrayList<>();
        this.symbol = symbol;
        this.price = initialPrice;
    }
    public void addObserver(StockObserver observer){
        observers.add(observer);
    }
    public void removeObserver(StockObserver observer){
        observers.remove(observer);
    }
    public void notifyObserver(){
        for(StockObserver observer : observers){
            observer.update(symbol, price);
        }
    }
    public void setPrice(double newPrice){
        System.out.println("\n" + symbol + " price changed: $" + price + " -> $" + newPrice);
        this.price = newPrice;
        notifyObserver();
    }
    public double getPrice(){
        return price;
    }
}

