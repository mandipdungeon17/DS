package org.systemDesign.structuralPattern.adapter;

public class DatabaseAdapterDemo {
    public static void main(String[] args) {
        Application app = new Application();
        DatabaseConnection mysql = new MySQLAdapter();
        app.performDatabaseOperations(mysql);

        System.out.println();

        DatabaseConnection mongo = new MongoAdapter();
        app.performDatabaseOperations(mongo);
    }
}
// Adaptee 1 - MySQL library
class MySQLDatabase{
    public void mysqlConnect() {
        System.out.println("MySQL: Connection established");
    }

    public void runQuery(String sql) {
        System.out.println("MySQL: Executing - " + sql);
    }

    public void mysqlDisconnect() {
        System.out.println("MySQL: Connection closed");
    }
}
// Adaptee 2 - MongoDB library
class MongoDatabase {
    public void establishConnection() {
        System.out.println("MongoDB: Connected");
    }

    public void find(String query) {
        System.out.println("MongoDB: Finding - " + query);
    }

    public void closeConnection() {
        System.out.println("MongoDB: Disconnected");
    }
}
// Target interface
interface DatabaseConnection {
    void connect();
    void executeQuery(String query);
    void disconnect();
}
// MySQL Adapter
class MySQLAdapter implements DatabaseConnection {
    private final MySQLDatabase mySQLDatabase;
    public MySQLAdapter(){
        this.mySQLDatabase = new MySQLDatabase();
    }
    @Override
    public void connect() {
        this.mySQLDatabase.mysqlConnect();
    }
    @Override
    public void executeQuery(String query) {
        this.mySQLDatabase.runQuery(query);
    }
    @Override
    public void disconnect() {
        this.mySQLDatabase.mysqlDisconnect();
    }
}
// MongoDB Adapter
class MongoAdapter implements DatabaseConnection {
    private final MongoDatabase mongo;

    public MongoAdapter() {
        this.mongo = new MongoDatabase();
    }

    public void connect() {
        mongo.establishConnection();
    }

    public void executeQuery(String query) {
        mongo.find(query);
    }

    public void disconnect() {
        mongo.closeConnection();
    }
}
class Application{
    public void performDatabaseOperations(DatabaseConnection db) {
        db.connect();
        db.executeQuery("SELECT * FROM users");
        db.disconnect();
    }
}
