package org.systemDesign.structuralPattern.proxy;

import java.util.ArrayList;
import java.util.List;

public class InternetProxyDemo {
    public static void main(String[] args) {
        Internet internet = new InternetProxy();
        internet.connectTo("google.com");
        System.out.println();
        internet.connectTo("facebook.com");
        System.out.println();
        internet.connectTo("github.com");
        System.out.println();
        internet.connectTo("twitter.com");
    }
}
// Subject interface
interface Internet {
    void connectTo(String url);
}
// Real Subject
class RealInternet implements Internet {
    public void connectTo(String url) {
        System.out.println("Connecting to: " + url);
    }
}
// Proxy with blacklist and logging
class InternetProxy implements Internet {
    private final RealInternet realInternet;
    private final List<String> blacklistedSites;
    public InternetProxy(){
        this.realInternet = new RealInternet();
        this.blacklistedSites = new ArrayList<>();
        blacklistedSites.add("facebook.com");
        blacklistedSites.add("twitter.com");
        blacklistedSites.add("instagram.com");
    }
    @Override
    public void connectTo(String url) {
        if(isBlackListed(url)){
            System.out.println("Access Denied: " + url + " is blocked by company policy");
            logAttempt(url, false);
        } else {
            realInternet.connectTo(url);
            logAttempt(url, true);
        }
    }
    private boolean isBlackListed(String url){
        for (String site : blacklistedSites) {
            if (url.toLowerCase().contains(site)) {
                return true;
            }
        }
        return false;
    }
    private void logAttempt(String url, boolean allowed) {
        String status = allowed ? "ALLOWED" : "BLOCKED";
        System.out.println("[LOG] URL: " + url + " | Status: " + status);
    }
}
