package org.systemDesign.structuralPattern.decorator;

public class CoffeeDecoratorDemo {
    public static void main(String[] args) {
        // Simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " : $" + coffee.getCost());

        // Coffee with milk
        coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription() + " : $" + coffee.getCost());

        // Coffee with milk and sugar
        coffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(coffee.getDescription() + " : $" + coffee.getCost());

        // Coffee with all toppings
        coffee = new WhipDecorator(
                new SugarDecorator(
                        new MilkDecorator(new SimpleCoffee())));
        System.out.println(coffee.getDescription() + " : $" + coffee.getCost());
    }
}
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}
// Concrete Component (base coffee)
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    @Override
    public double getCost() {
        return 50.0;
    }
}
// Abstract Decorator
abstract class CoffeeDecorator implements Coffee{
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee){
        this.decoratedCoffee = coffee;
    }
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
// Concrete Decorators
class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", MILK";
    }
    public double getCost() {
        return decoratedCoffee.getCost() + 10.0;
    }
}
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
    public double getCost() {
        return decoratedCoffee.getCost() + 5.0;
    }
}
class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }
    public double getCost() {
        return decoratedCoffee.getCost() + 15.0;
    }
}