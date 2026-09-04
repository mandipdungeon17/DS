package org.systemDesign.scenario;

import java.util.ArrayList;
import java.util.List;

/*
🎯 Scenario 4 — Order Notification System

Context
When an order's status changes (Placed, Shipped, Delivered), several independent
parties must be told: SMS to customer, Email to customer, an internal Analytics
service log, and (later) a Warehouse system. Originally `Order.updateStatus()`
directly called `smsService.send(...)`, `emailService.send(...)` etc. in one method.

Requirements
1. Adding a new notification channel (e.g. push notification) must not require
   editing Order's status-update code.
2. Order must not know concrete details of SMS/Email/Analytics — it should not import
   or depend on their classes directly, only some abstraction.
3. If one notifier throws an exception (e.g. SMS gateway down), the other notifiers
   must still run — one failure must not block the rest.
4. main must register multiple notifiers on one order, change status, and show all
   notifiers firing, plus demonstrate that a failing notifier doesn't stop the others.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: Observer. `Order` is the Subject holding a list of Observer references and
    notifying all of them on state change; SMS/Email/Analytics are concrete Observers.

Q2: What was structurally wrong with treating `Order` as just calling a fixed list of
    services directly, and what makes it a "true Subject" instead?
A2: A true Subject exposes `attach/register`, `detach`, and `notifyObservers()` against
    an Observer interface, and does not know or care how many observers exist or what
    they do — it only iterates and calls a common method (e.g. `onOrderUpdated(...)`).
    Calling named concrete services directly (`smsService.send()`) is tight coupling,
    not the Observer relationship.

Q3: How do you satisfy requirement 3 (one observer's failure must not block others)?
A3: Wrap each individual observer's `update()` call in its own try/catch inside the
    notification loop in Subject — never wrap the whole loop in one try/catch, since
    that would still abort remaining observers after the first exception.

Ruled out similar patterns:
- Chain of Responsibility: in CoR, one handler in a sequence "wins" and the rest are
  typically skipped; here EVERY observer must run for EVERY change — not a
  first-match-stops chain.
- Strategy: Strategy is one interchangeable algorithm chosen by the caller; here MANY
  independent listeners all react to the same one event, not a single swapped algorithm.

Rule of thumb:
- Subject holds `List<Observer>` and depends only on the Observer interface —
  never on concrete observer classes.
- Isolate each observer call in its own try/catch inside the notify loop so failures
  don't cascade.
- Observers should be attach/detachable at runtime — that dynamic registration is the
  signature that separates Observer from a hardcoded pipeline.
*/
public class OrderNotificationDemo {
    public static void main(String[] args) {
        Email email = new Email();
        Inventory inventory = new Inventory();
        Warehouse warehouse = new Warehouse();
        Loyalty loyalty = new Loyalty();
        Analytics analytics = new Analytics();

        OrderPublisher order = new OrderPublisher("12345");
        order.subscribe(email);
        order.subscribe(inventory);
        order.subscribe(warehouse);
        order.subscribe(loyalty);
        order.subscribe(analytics);

        order.updateOrderStatus("PLACED");

        order.unsubscribe(loyalty);

        order.updateOrderStatus("SHIPPED");

        order.unsubscribe(inventory);
        order.unsubscribe(warehouse);

        order.subscribe(loyalty);
        order.updateOrderStatus("DELIVERED");
    }
}

interface OrderObserver {
    void update(String orderId, String status);
}

class Email implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Email Notification: Order " + orderId + " is now " + status);
    }
}
class Inventory implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        if(status.equals("SHIPPED")) {
            System.out.println("Inventory Notification: Order " + orderId + " has been shipped. Updating inventory.");
        }
    }
}
class Warehouse implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Warehouse Notification: Order " + orderId + " is now " + status);
    }
}
class Loyalty implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        if(status.equals("DELIVERED")) {
            System.out.println("Loyalty Notification: Order " + orderId + " has been delivered. Awarding loyalty points.");
        }
    }
}
class Analytics implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Analytics Notification: Order " + orderId + " is now " + status);
    }
}

interface Subject {
    void subscribe(OrderObserver observer);
    void unsubscribe(OrderObserver observer);
    void notifyObservers(String orderId, String status);
}

class OrderPublisher implements Subject {
    private final String orderId;
    private final List<OrderObserver> observers;

    OrderPublisher(String orderId) {
        this.observers = new ArrayList<>();
        this.orderId = orderId;
    }

    public void updateOrderStatus(String status) {
        System.out.println("Order " + orderId + " status changed to " + status);
        this.notifyObservers(orderId, status);
    }

    @Override
    public void subscribe(OrderObserver observer) { this.observers.add(observer); }

    @Override
    public void unsubscribe(OrderObserver observer) { this.observers.remove(observer); }

    @Override
    public void notifyObservers(String orderId, String status) {
        for(OrderObserver observer : observers) {
            try {
                observer.update(orderId, status);
            } catch (Exception e) {
                System.out.println("Observer failed: " + observer.getClass().getSimpleName() + " — " + e.getMessage());
            }
        }
    }
}