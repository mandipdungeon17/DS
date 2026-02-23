package org.systemDesign.structuralPattern.decorator;
/*
    Demonstration of Combination of Strategy and Decorator Pattern
    Here, we have different discount strategies implemented using Decorator pattern.
    Each discount strategy decorates the previous one and applies its own discount logic.
    The final price is calculated by applying all the discounts in sequence.
                            But

The implementation is primarily a **Decorator pattern** with elements that *resemble* Strategy, though it's not a pure Strategy pattern implementation.

### Decorator Pattern ✅ (Fully Implemented)

The code correctly implements the Decorator pattern:

1. **Component Interface**: `DiscountStrategy` serves as the common interface
2. **Concrete Component**: `NoDiscount` is the base implementation
3. **Abstract Decorator**: `DiscountDecorator` wraps a `DiscountStrategy` and delegates to it
4. **Concrete Decorators**: `SeasonalDiscount`, `BlackFridayDiscount`, `LoyaltyDiscount` extend the decorator and add behavior

**Evidence**: Decorators are composed/wrapped around each other:
```java
strategy = new SeasonalDiscount(new BlackFridayDiscount(new NoDiscount()));
```

### Strategy Pattern ⚠️ (Partial/Misleading)

The code **names** the interface `DiscountStrategy`, but it doesn't follow the true Strategy pattern intent:

| Strategy Pattern Requirement | This Code |
|------------------------------|-----------|
| Interchangeable algorithms at runtime | ❌ Not really — decorators are chained, not swapped |
| Context class that uses strategy | ❌ Missing — no separate context class |
| Single algorithm per strategy | ❌ Decorators wrap and delegate, not standalone |

**True Strategy** would look like:
```java
// Context holds ONE strategy and uses it
class PriceCalculator {
    private DiscountStrategy strategy;
    void setStrategy(DiscountStrategy s) { this.strategy = s; }
    double calculate(double price) { return strategy.calculateDiscount(price, price); }
}
```

### Verdict

- **Decorator Pattern**: ✅ Correctly implemented
- **Strategy Pattern**: ❌ The interface naming suggests Strategy, but the actual behavior is Decorator composition, not runtime algorithm swapping

This is a **Decorator pattern** that uses a `DiscountStrategy` interface as its component type — the naming is somewhat misleading.
*/
public class DiscountStrategyCumDecoratorDemo {
    public static void main(String[] args) {
        DiscountStrategy strategy = new NoDiscount();
        System.out.println("No Discount: $" +strategy.calculateDiscount(5000.0, 5000.0));
        System.out.println("\n");
        System.out.println("Loyalty Discount");
        LoyaltyDiscount loyaltyDiscount = new LoyaltyDiscount(strategy);
        loyaltyDiscount.setLoyaltyPoints(50);
        System.out.println(loyaltyDiscount.calculateDiscount(5000.0, 5000.0));
        System.out.println("\n");
        System.out.println("Black and Seasonal Discounts Applied:");
        strategy = new SeasonalDiscount(new BlackFridayDiscount(new NoDiscount()));
        System.out.println("All Discounted price: $" + strategy.calculateDiscount(5000.0, 5000.0));
        System.out.println("\n");
        System.out.println("All Discounts with Loyalty Applied:");
        loyaltyDiscount = new LoyaltyDiscount(new NoDiscount());
        loyaltyDiscount.setLoyaltyPoints(80);
        strategy = new SeasonalDiscount(new BlackFridayDiscount(loyaltyDiscount));
        System.out.println("All Discounted price with Loyalty: $" + strategy.calculateDiscount(5000.0, 5000.0));


    }
}
// Strategy interface
interface DiscountStrategy {
    double maxDiscount = 0.60;
    double calculateDiscount(double discountPrice, double originalPrice);
}
//Basic Concrete class
class NoDiscount implements DiscountStrategy{
    @Override
    public double calculateDiscount(double discountPrice, double originalPrice) {
        return discountPrice;
    }
}
abstract class DiscountDecorator implements DiscountStrategy{
    protected DiscountStrategy strategy;
    public DiscountDecorator(DiscountStrategy strategy){
        this.strategy = strategy;
    }
    @Override
    public double calculateDiscount(double discountPrice, double originalPrice) {
        return this.strategy.calculateDiscount(discountPrice, originalPrice);
    }
}
class SeasonalDiscount extends DiscountDecorator{
    public SeasonalDiscount(DiscountStrategy strategy) {
        super(strategy);
    }
    @Override
    public double calculateDiscount(double discountPrice, double originalPrice) {
        double discount = discountPrice - discountPrice * 0.10;
        double maxDiscountPrice = originalPrice * (1-maxDiscount);
        System.out.println("Discount after Seasonal: $ " + discount +
                " vs Max Discount Price: $ " + maxDiscountPrice);
        if(maxDiscountPrice > discount)
            return this.strategy.calculateDiscount(maxDiscountPrice, originalPrice);
        return this.strategy.calculateDiscount(discount, originalPrice);
    }
}
class BlackFridayDiscount extends DiscountDecorator{
    public BlackFridayDiscount(DiscountStrategy strategy) {
        super(strategy);
    }
    @Override
    public double calculateDiscount(double discountPrice, double originalPrice) {
        double discount = discountPrice - discountPrice * 0.50;
        double maxDiscountPrice = originalPrice * (1-maxDiscount);
        System.out.println("Discount after Black Friday: $ " + discount +
                " vs Max Discount Price: $ " + maxDiscountPrice);
        if(maxDiscountPrice > discount)
            return this.strategy.calculateDiscount(maxDiscountPrice, originalPrice);
        return this.strategy.calculateDiscount(discount, originalPrice);
    }
}
class LoyaltyDiscount extends DiscountDecorator{
    private int loyaltyPoints;
    public LoyaltyDiscount(DiscountStrategy strategy) {
        super(strategy);
    }
    public void setLoyaltyPoints(int loyaltyPoints){
        this.loyaltyPoints = loyaltyPoints;
    }
    @Override
    public double calculateDiscount(double discountPrice, double originalPrice) {
        double discount = loyaltyPoints * 0.1; // 1% discount per point
        if(discount > discountPrice * 0.3) { // Max 30% discount
            discount = discountPrice * 0.3;
        }
        double finalPrice = discountPrice - discount;
        double maxDiscountPrice = originalPrice * (1-maxDiscount);
        System.out.println("Discount after Loyalty points: $ " + finalPrice +
                " vs Max Discount Price: $ " + maxDiscountPrice);
        if(maxDiscountPrice > finalPrice)
            return this.strategy.calculateDiscount(maxDiscountPrice, originalPrice);
        return this.strategy.calculateDiscount(finalPrice, originalPrice);
    }
}