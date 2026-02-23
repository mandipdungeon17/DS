package org.systemDesign.solid.liskovSubstitution;

/**
 * The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
 * This means that if class S is a subclass of class T, then objects of type T should be replaceable with objects of type S without altering any desirable properties of the program.
 * In practice, this often involves ensuring that subclasses adhere to the behavior expected by the superclass, including method signatures and return types.
 * Violating this principle can lead to unexpected behavior and bugs in the code.
 * For example, if a subclass overrides a method in a way that changes its expected behavior, it can cause issues when the subclass is used in place of the superclass.
 */
public class LiskovSubstitution {
    // Example of a class that adheres to the Liskov Substitution Principle
    public class Bird {
        public void fly() {
            System.out.println("Flying");
        }
    }

    public class Sparrow extends Bird {
        @Override
        public void fly() {
            System.out.println("Sparrow flying");
        }
    }

    public class Ostrich extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Ostriches cannot fly");
        }
    }

    // This violates the Liskov Substitution Principle because Ostrich cannot be used in place of Bird without causing an error.
    public void makeBirdFly(Bird bird) {
        bird.fly(); // This will throw an exception if an Ostrich is passed
    }
    // To adhere to the Liskov Substitution Principle, we can create a separate interface for flying birds
    public interface FlyingBird {
        void fly();
    }

    public class FlyingSparrow extends Sparrow implements FlyingBird {
        @Override
        public void fly() {
            super.fly();
            System.out.println("Flying Sparrow flying");
        }
    }

    public class NonFlyingOstrich extends Ostrich {
        // This class does not implement fly, adhering to the Liskov Substitution Principle
    }

    public void makeFlyingBirdFly(FlyingBird bird) {
        bird.fly(); // This will not throw an exception
    }

    public static void main(String[] args) {
        LiskovSubstitution lsp = new LiskovSubstitution();
        Bird sparrow = lsp.new Sparrow();
        lsp.makeBirdFly(sparrow); // Works fine

        Bird ostrich = lsp.new Ostrich();
        try {
            lsp.makeBirdFly(ostrich); // Throws exception
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        FlyingBird flyingSparrow = lsp.new FlyingSparrow();
        lsp.makeFlyingBirdFly(flyingSparrow); // Works fine
    }
}
