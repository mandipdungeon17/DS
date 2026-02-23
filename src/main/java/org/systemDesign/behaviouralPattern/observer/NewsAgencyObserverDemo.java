package org.systemDesign.behaviouralPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgencyObserverDemo {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        // Create observers
        Observer emailSub = new EmailSubscriber("user@email.com");
        Observer smsSub = new SMSSubscriber("+91-9876543210");
        Observer appSub = new MobileAppSubscriber("john_doe");
        // Register observers
        newsAgency.registerObserver(emailSub);
        newsAgency.registerObserver(smsSub);
        newsAgency.registerObserver(appSub);
        // Publish news - all observers notified
        newsAgency.setNews("Stock market hits all-time high!");
        // Remove one observer
        System.out.println("\n--- Removing SMS subscriber ---");
        newsAgency.removeObserver(smsSub);
        // Publish again - only remaining observers notified
        newsAgency.setNews("New COVID variant detected!");
    }
}
// Observer interface
interface Observer {
    void update(String news);
}
// Concrete Observers
class EmailSubscriber implements Observer{
    private final String email;
    public EmailSubscriber(String email){
        this.email = email;
    }
    @Override
    public void update(String news) {
        System.out.println("Email sent to " + email + ": " + news);
    }
}
class SMSSubscriber implements Observer {
    private final String phoneNumber;

    public SMSSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void update(String news) {
        System.out.println("SMS sent to " + phoneNumber + ": " + news);
    }
}
class MobileAppSubscriber implements Observer {
    private final String username;

    public MobileAppSubscriber(String username) {
        this.username = username;
    }

    public void update(String news) {
        System.out.println("Push notification to " + username + ": " + news);
    }
}
// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
// Concrete Subject
class NewsAgency implements Subject{
    private final List<Observer> observers;
    private String latestNews;
    public NewsAgency(){
        this.observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer.getClass().getSimpleName());
    }
    @Override
    public void notifyObservers() {
        System.out.println("\n=== Notifying all observers ===");
        for(Observer observer : this.observers){
            observer.update(latestNews);
        }
    }
    // When news is published
    public void setNews(String news) {
        System.out.println("\nBreaking News: " + news);
        this.latestNews = news;
        notifyObservers();
    }
}