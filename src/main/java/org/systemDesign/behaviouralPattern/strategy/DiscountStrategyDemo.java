package org.systemDesign.behaviouralPattern.strategy;
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
public class DiscountStrategyDemo {
    public static void main(String[] args) {
        PricingContext pricing = new PricingContext();
        double productPrice = 10000;

        System.out.println("=== Regular Day ===");
        pricing.setDiscountStrategy(new NoDiscountStrategy());
        pricing.calculateFinalPrice(productPrice);

        System.out.println("\n=== Seasonal Sale ===");
        pricing.setDiscountStrategy(new SeasonalDiscountStrategy());
        pricing.calculateFinalPrice(productPrice);

        System.out.println("\n=== Black Friday ===");
        pricing.setDiscountStrategy(new BlackFridayDiscountStrategy());
        pricing.calculateFinalPrice(productPrice);

        System.out.println("\n=== Loyalty Member ===");
        pricing.setDiscountStrategy(new LoyaltyDiscountStrategy(500));
        pricing.calculateFinalPrice(productPrice);
    }
}
// Strategy interface
interface DiscountStrategy {
    double calculateDiscount(double price);
}
// Concrete Strategies
class NoDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculateDiscount(double price) {
        return 0;
    }
}
class SeasonalDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculateDiscount(double price) {
        System.out.println("Seasonal Discount: 10%");
        return price * 0.1; // 10% seasonal discount
    }
}
class BlackFridayDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculateDiscount(double price) {
        System.out.println("Black Friday Discount: 50%");
        return price * 0.5; // 50% Black Friday discount
    }
}
class LoyaltyDiscountStrategy implements DiscountStrategy {
    private final int loyaltyPoints;
    public LoyaltyDiscountStrategy(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    @Override
    public double calculateDiscount(double price) {
        double discount = loyaltyPoints * 0.1; // 1% discount per point
        if(discount > price * 0.3) { // Max 30% discount
            discount = price * 0.3;
        }
        System.out.println("Loyalty Discount: $ " + discount);
        return discount;
    }
}
// Context
// Composition over Inheritance
class PricingContext {
    private DiscountStrategy strategy;
    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
    public void calculateFinalPrice(double originalPrice) {
        double discount = strategy.calculateDiscount(originalPrice);
        double finalPrice = originalPrice - discount;
        System.out.println("Original Price: $" + originalPrice);
        System.out.println("Discount: $" + discount);
        System.out.println("Final Price: $" + finalPrice);
    }
}