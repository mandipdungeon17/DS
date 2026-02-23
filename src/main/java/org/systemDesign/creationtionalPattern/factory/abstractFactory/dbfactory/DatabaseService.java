package org.systemDesign.creationtionalPattern.factory.abstractFactory.dbfactory;

import java.util.Scanner;
/*
# Abstract Factory vs Strategy Pattern - Notes

## Pattern Classification

| Pattern | Category | Intent |
|---------|----------|--------|
| **Abstract Factory** | Creational | Create families of related **objects** |
| **Strategy** | Behavioral | Encapsulate interchangeable **algorithms** |

---

## Core Difference

```
Abstract Factory → "WHAT to create"  (produces objects)
Strategy         → "HOW to behave"   (executes logic)
```

---

## Structure Comparison

### Abstract Factory (DB Factory)
```java
// Factory CREATES and RETURNS objects
DatabaseFactory factory = new MySQLDatabaseFactory();
Connection conn = factory.createConnection();  // Returns NEW object
Command cmd = factory.createCommand();         // Returns NEW object

// Objects are then USED by client
conn.connect();
cmd.execute("...");
```

### Strategy (Discount)
```java
// Strategy EXECUTES algorithm and RETURNS result
DiscountStrategy strategy = new BlackFridayDiscountStrategy();
double discount = strategy.calculateDiscount(100.0);  // Returns computed VALUE
```

---

## Key Characteristics

| Aspect | Abstract Factory | Strategy |
|--------|------------------|----------|
| **Returns** | Object instances | Computed values/actions |
| **Method naming** | `create___()` | `calculate___()`, `execute___()` |
| **Client usage** | Creates objects, then uses them | Delegates behavior directly |
| **Variation** | Different object families | Different algorithms |

---

## When to Use

**Abstract Factory:**
- Need multiple related objects that work together
- Hide concrete class instantiation
- Ensure compatibility between created objects

**Strategy:**
- Multiple ways to perform same operation
- Swap algorithms at runtime
- Eliminate complex if/else or switch statements

---

## Memory Aid

```
Factory  = Object SUPPLIER   (gives you tools)
Strategy = Behavior EXECUTOR (does the work)
```
 */
public class DatabaseService {
    private final Connection connection;
    private final Command command;
    private final Transaction transaction;

    DatabaseService(DatabaseFactory databaseFactory){
        connection = databaseFactory.createConnection();
        command = databaseFactory.createCommand();
        transaction = databaseFactory.createTransaction();
    }

    public void performOperation(){
        connection.connect();
        transaction.begin();
        command.execute("INSERT INTO users VALUES (1, 'John')");
        transaction.commit();
    }
}

class DBMain{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter SQL to instantiate : ");
        String sql = scanner.nextLine();

        DatabaseFactory databaseFactory;
        if(sql.equals("MYSQL")){
            databaseFactory = new MySQLDatabaseFactory();
        }
        else{
            databaseFactory = new PostgresDatabaseFactory();
        }

        DatabaseService databaseService = new DatabaseService(databaseFactory);
        databaseService.performOperation();

    }
}
