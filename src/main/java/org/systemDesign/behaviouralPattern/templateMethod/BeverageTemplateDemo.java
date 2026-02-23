package org.systemDesign.behaviouralPattern.templateMethod;

public class BeverageTemplateDemo {
    public static void main(String[] args) {
        System.out.println("=== Making Tea ===");
        Beverage tea = new Tea();
        tea.prepareBeverage();

        System.out.println("=== Making Coffee ===");
        Beverage coffee = new Coffee();
        coffee.prepareBeverage();

        System.out.println("=== Making Green Tea ===");
        Beverage greenTea = new GreenTea();
        greenTea.prepareBeverage();
    }
}
//Abstract class
abstract class Beverage{
    // Template method
    public final void prepareBeverage(){
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){ //Hook
            addCondiments();
        }
        System.out.println("Beverage ready!\n");
    }
    // Concrete methods (same for all beverages)
    private void boilWater(){
        System.out.println("Boiling water");
    }
    private void pourInCup() {
        System.out.println("Pouring into cup");
    }
    // Abstract methods (different for each beverage)
    protected abstract void brew();
    protected abstract void addCondiments();
    // Hook method (can be overridden)
    protected boolean customerWantsCondiments() {
        return true;
    }
}
// Concrete class: Tea
class Tea extends Beverage {
    protected void brew() {
        System.out.println("Steeping tea bag");
    }
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}
// Concrete class: Coffee
class Coffee extends Beverage {
    protected void brew() {
        System.out.println("Dripping coffee through filter");
    }
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
// Concrete class: Green Tea (uses hook)
class GreenTea extends Beverage {
    protected void brew() {
        System.out.println("Steeping green tea leaves");
    }
    protected void addCondiments() {
        System.out.println("Adding honey");
    }
    // Override hook - no condiments
    protected boolean customerWantsCondiments() {
        return false; // Pure green tea
    }
}