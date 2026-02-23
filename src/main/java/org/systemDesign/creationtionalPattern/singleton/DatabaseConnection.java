package org.systemDesign.creationtionalPattern.singleton;

import java.util.Objects;

//Not thread Safe
public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Database connection created");
    }

    public static DatabaseConnection getInstance() {
        if(Objects.isNull(instance)) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql){
        System.out.println("Executing query... "+ sql);
    }
}

class DBMain {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println(db1 == db2); // true - same instance
        db1.query("SELECT * FROM users");
        db2.query("SELECT * FROM client");
    }
}