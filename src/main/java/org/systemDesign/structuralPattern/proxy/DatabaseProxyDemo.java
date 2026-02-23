package org.systemDesign.structuralPattern.proxy;

import java.util.HashMap;
import java.util.Map;

public class DatabaseProxyDemo {
    public static void main(String[] args) {
        DatabaseQuery db = new DatabaseQueryProxy();

        System.out.println("=== First query ===");
        System.out.println(db.execute("SELECT * FROM users"));

        System.out.println("\n=== Same query again (from cache) ===");
        System.out.println(db.execute("SELECT * FROM users"));

        System.out.println("\n=== Different query ===");
        System.out.println(db.execute("SELECT * FROM products"));

        System.out.println("\n=== First query again (from cache) ===");
        System.out.println(db.execute("SELECT * FROM users"));
    }
}
// Subject interface
interface DatabaseQuery{
    String execute(String query);
}
// Real Subject (Expensive database operations)
class RealDatabase implements DatabaseQuery {
    @Override
    public String execute(String query) {
        System.out.println("Executing expensive database query: " + query);
        // Simulate slow database operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Result for: " + query;
    }
}
// Caching Proxy
class DatabaseQueryProxy implements DatabaseQuery{
    private final RealDatabase realDatabase;
    private final Map<String, String> cache;
    public DatabaseQueryProxy() {
        this.realDatabase = new RealDatabase();
        this.cache = new HashMap<>();
    }
    @Override
    public String execute(String query) {
        if(cache.containsKey(query)){
            System.out.println("Returning cached result for: " + query);
            return cache.get(query);
        }
        // Execute query and cache result
        else{
            String result = realDatabase.execute(query);
            cache.put(query, result);
            return result;
        }
    }
}