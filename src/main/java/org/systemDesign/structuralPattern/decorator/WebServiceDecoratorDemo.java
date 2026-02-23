package org.systemDesign.structuralPattern.decorator;

public class WebServiceDecoratorDemo {
    public static void main(String[] args) {
        // Basic service with logging, auth, and caching
        WebService service = new CachingDecorator(
                                new LoggingDecorator(
                                new AuthenticationDecorator(
                                new BasicWebService())));
        System.out.println(service.handleRequest("GET /api/users?token=abc123"));
        System.out.println("\n--- Second request (cached) ---\n");
        System.out.println(service.handleRequest("GET /api/users?token=abc123"));
    }
}
//Component Interface
interface WebService{
    String handleRequest(String request);
}
//Concrete Component
class BasicWebService implements WebService{
    @Override
    public String handleRequest(String request) {
        return "Processing request: " + request;
    }
}
// Base Decorator
abstract class WebServiceDecorator implements WebService {
    protected WebService wrappee;
    public WebServiceDecorator(WebService service){
        this.wrappee = service;
    }
    public String handleRequest(String request){
        return wrappee.handleRequest(request);
    }
}
// Logging Decorator
class LoggingDecorator extends WebServiceDecorator{
    public LoggingDecorator(WebService service) {
        super(service);
    }
    public String handleRequest(String request){
        System.out.println("[LOG] Request received: " + request);
        long startTime = System.currentTimeMillis();
        String response = wrappee.handleRequest(request);
        long endTime = System.currentTimeMillis();
        System.out.println("[LOG] Response sent in " + (endTime - startTime) + "ms");
        return response;
    }
}
// Authentication Decorator
class AuthenticationDecorator extends WebServiceDecorator{
    public AuthenticationDecorator(WebService service) {
        super(service);
    }
    public String handleRequest(String request){
        if(!isAuthenticated(request)){
            return "Error: Authentication failed";
        }
        System.out.println("[AUTH] User authenticated");
        return wrappee.handleRequest(request);
    }
    private boolean isAuthenticated(String request){
        // Simulate auth check
        return request.contains("token");
    }
}
// Caching Decorator
class CachingDecorator extends WebServiceDecorator {
    private String cachedResponse;
    public CachingDecorator(WebService service) {
        super(service);
    }
    public String handleRequest(String request) {
        if (cachedResponse != null) {
            System.out.println("[CACHE] Returning cached response");
            return cachedResponse;
        }
        String response = wrappee.handleRequest(request);
        cachedResponse = response;
        System.out.println("[CACHE] Response cached");
        return response;
    }
}