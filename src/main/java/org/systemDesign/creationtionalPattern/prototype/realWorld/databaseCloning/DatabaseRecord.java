package org.systemDesign.creationtionalPattern.prototype.realWorld.databaseCloning;

public class DatabaseRecord implements Cloneable{
    private int id;
    private String data;
    private long timestamp;

    DatabaseRecord(int id){
        this.id = id;
        System.out.println("Fetching from database... (expensive operation)");
        try{
            Thread.sleep(2000); // Simulate DB call
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.data = "Data for ID: " + id;
        this.timestamp = System.currentTimeMillis();
    }

    private DatabaseRecord() {
        // Private constructor for cloning
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public DatabaseRecord clone(){
        try{
            return (DatabaseRecord) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CachingExample{
    public static void main(String[] args) {
        // First fetch - expensive
        long start = System.currentTimeMillis();
        DatabaseRecord original = new DatabaseRecord(100);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms");
        System.out.println(original);

        System.out.println("\n--- Cloning ---\n");

        // Clone - fast
        start = System.currentTimeMillis();
        DatabaseRecord clone1 = original.clone();
        clone1.setId(101);

        DatabaseRecord clone2 = original.clone();
        clone2.setId(102);

        end = System.currentTimeMillis();
        System.out.println("Time taken for 2 clones: " + (end - start) + "ms");

        System.out.println(clone1);
        System.out.println(clone2);
    }
}
