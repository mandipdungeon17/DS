package org.systemDesign.scenario;

/*
🎯 Scenario 10 — Coffee Shop Order Pricing

Context
A coffee shop app prices drinks. Base drinks: Espresso (₹80), Filter Coffee (₹60),
Cold Brew (₹120).
Customers can add any combination of extras, each adding cost:
  Extra           Price   Notes
  Extra Shot      ₹30     can be added multiple times
  Whipped Cream   ₹20
  Caramel Syrup   ₹15
  Oat Milk        ₹25
  Large Size      ₹40

Current (bad) code:
    public double calculatePrice(String base, boolean extraShot, int extraShotCount,
                                  boolean whippedCream, boolean caramelSyrup,
                                  boolean oatMilk, boolean largeSize) {
        double price = base.equals("ESPRESSO") ? 80 : base.equals("FILTER_COFFEE") ? 60 : 120;
        if (extraShot) price += 30 * extraShotCount;
        if (whippedCream) price += 20;
        if (caramelSyrup) price += 15;
        if (oatMilk) price += 25;
        if (largeSize) price += 40;
        return price;
    }

What's actually wrong, told directly: this method already has 6 boolean/int
parameters for 5 extras. Marketing wants to add Vanilla Syrup, Cinnamon Powder, and
Extra Hot next month — each addition means another parameter, and the method
signature keeps growing. A customer ordering "Cold Brew with 2 extra shots, oat milk,
and large size" requires the caller to correctly set 4 out of 7 parameters and
remember to leave the rest false — a common source of pricing bugs already reported
by support.

Requirements
1. Adding Vanilla Syrup must not require changing the method signature of anything
   already written, and must not touch the base drink classes.
2. A customer must be able to add the same extra multiple times (e.g. 3 extra shots)
   and be charged for each one.
3. It must be possible to get the price of just the base drink alone, and separately,
   the full description string (e.g. "Cold Brew, Extra Shot, Extra Shot, Oat Milk,
   Large Size") built incrementally as extras are added — not stored as a separate
   list that's manually kept in sync with price.
4. The customer should be able to combine extras in any order, and the order they're
   added should not affect the final price (but may affect the order they appear in
   the description).
5. main must build at least 2 different drinks with overlapping but different
   extras, print each one's full description and total price, and demonstrate the
   same extra applied twice on one drink.

Traps
- Don't use a List<String> extras field with a loop that sums prices — that's just the
  parameter-explosion problem moved into a list. Think about what structural
  difference is being asked for in requirement 3 ("built incrementally... not stored
  as a separate list").
- Don't make extras subclasses of the base drink classes (no EspressoWithOatMilk
  extends Espresso) — think about why that explodes combinatorially.
- This scenario intentionally sits right next to Proxy and Composite in shape. State
  why it's neither, briefly, before coding.

Deliverable: src\main\java\org\systemDesign\scenario\CoffeeShopDemo.java

Answer before coding:
1. Name the pattern.
2. Why not Proxy? (one sentence — think back to Scenario 6's discriminator)
3. Why not Composite? (one sentence — think about whether an extra is a "collection
   of drinks")
4. Requirement 3 — describe precisely how getPrice() and getDescription() should be
   implemented so nothing is stored redundantly.

Answer:
Q1 — Correct. Decorator.
Q2 — Close but sharpened to the actual discriminator (same one from Scenario 6):
Proxy adds no new capability — play() still just plays, the proxy only controls
whether/when it runs. Decorator genuinely adds new behavior the base object didn't
have — Oat Milk actually changes the price and description, it's not gating access to
the base drink. Test: "can the wrapped object now do something it couldn't do
before?" Decorator: yes. Proxy: no.
Q3 — Composite exists to let you treat one object and a group of objects identically
— e.g., a MacroCommand holding many Commands, still callable as a single Command
(built in Scenario 8). The test: "is the wrapped thing a collection of many peer
items?" Here, Oat Milk isn't a collection of drinks — it's a single drink with one
extra added. There's no "group of coffees" being treated as one coffee. That rules
out Composite.
Q4 — Each decorator's `getPrice()` calls `wrappedCoffee.getPrice() + own extra cost`,
and `getDescription()` calls `wrappedCoffee.getDescription() + ", " + own extra name`
— both computed on demand by delegating to the wrapped object and adding one
increment, recursively unwinding down to the base drink. Nothing is cached or stored
in a separate list; every call re-derives the full chain.

Ruled out similar patterns:
- Proxy: adds no new capability, only gates/controls access to unchanged behavior —
  an extra here genuinely changes price/description, which is new capability, not
  access control.
- Composite: an extra is not a collection of drinks being treated as one drink; there
  is no "group of coffees" here, only one drink with layered add-ons.

Rule of thumb:
- Create an abstract decorator base class that implements the same interface as the
  base object (`Coffee`), with a constructor/field holding a wrapped `Coffee`
  reference (the interface type, never the concrete class) — this is what allows
  decorators to wrap the base drink OR another decorator, enabling stacking.
- Each concrete decorator delegates to the wrapped object first, then adds its own
  cost/description fragment on top (`wrapped.getPrice() + extraCost`).
- Repeated extras are represented by simply wrapping the same decorator multiple times
  (e.g. `new ExtraShot(new ExtraShot(espresso))`) or via a quantity-aware decorator —
  either way, no separate list needs to be kept in sync with price.
*/
public class CoffeeShopDemo {
    public static void main(String[] args) {
        Espresso espresso = new Espresso();
        ColdBrew coldBrew = new ColdBrew();
        FilterCoffee filterCoffee = new FilterCoffee();

        System.out.println(espresso.getDescription() + ": " + espresso.getCost());
        Coffee coffee = new ExtraShot(new LargeSize(espresso), 2);
        System.out.println(coffee.getDescription() + ": " + coffee.getCost());

        System.out.println(coldBrew.getDescription() + ": " + coldBrew.getCost());
        Coffee coffee1 = new ExtraShot(new OatMilk(new WhippedCream(coldBrew)), 1);
        System.out.println(coffee1.getDescription() + ": " + coffee1.getCost());

        System.out.println(filterCoffee.getDescription() + ": " + filterCoffee.getCost());
        Coffee coffee2 = new OatMilk(new CaramelSyrup(filterCoffee));
        System.out.println(coffee2.getDescription() + ": " + coffee2.getCost());
    }
}

interface Coffee {
    String getDescription();
    double getCost();
}

class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }
    @Override
    public double getCost() { return 80;}
}

class FilterCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Filter Coffee";
    }
    @Override
    public double getCost() { return 60;}
}

class ColdBrew implements Coffee {
    @Override
    public String getDescription() {
        return "ColdBrew Coffee";
    }
    @Override
    public double getCost() { return 120;}
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    protected CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
//    public String getDescription() {
//        return coffee.getDescription();
//    }
//    public double getCost(){
//        return this.coffee.getCost();
//    }
}
 class WhippedCream extends CoffeeDecorator {
    WhippedCream(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return coffee.getDescription() + " Whipped Cream";
    }
    public double getCost(){
        return coffee.getCost() + 20;
    }
 }
class CaramelSyrup extends CoffeeDecorator {
    CaramelSyrup(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return coffee.getDescription() + " Caramel Syrup";
    }
    public double getCost(){
        return coffee.getCost() + 15;
    }
}
class OatMilk extends CoffeeDecorator {
    OatMilk(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return coffee.getDescription() + " Oat Milk";
    }
    public double getCost(){
        return coffee.getCost() + 25;
    }
}
class LargeSize extends CoffeeDecorator {
    LargeSize(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return coffee.getDescription() + " Large Size";
    }
    public double getCost(){
        return coffee.getCost() + 40;
    }
}
class ExtraShot extends CoffeeDecorator {
    private final Integer quantity;
    ExtraShot(Coffee coffee, Integer quantity) {
        super(coffee);
        this.quantity = quantity;
    }
    public String getDescription() {
        return coffee.getDescription() + " " + this.quantity + " Extra Shot";
    }
    public double getCost(){
        return coffee.getCost() + 30*quantity;
    }
}