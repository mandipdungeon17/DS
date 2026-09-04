package org.systemDesign.scenario;

/*
🎯 Scenario 1 — Ride Fare Calculation

Context
A ride-hailing app computes fare at the end of a ride. Three fare types:
  Normal: ₹10/km
  Surge : ₹10/km × 1.8
  Night : ₹10/km + ₹50 flat charge

Original (bad) code:
    public double calculateFare(String type, double km) {
        if (type.equals("NORMAL"))      return km * 10;
        else if (type.equals("SURGE"))  return km * 10 * 1.8;
        else if (type.equals("NIGHT"))  return km * 10 + 50;
        throw new IllegalArgumentException("Unknown type");
    }

Requirements
1. Adding Airport/Subscription fare later must not require editing existing fare logic.
2. Fare type is decided at runtime, after the ride ends (surge known only then).
3. A ride must be able to switch fare calculation without being recreated.
4. Airport pickup (+₹150) can occur ON TOP OF any base fare (Normal/Surge/Night) —
   it must not be modeled as a 4th mutually exclusive fare type.

Q&A (asked and answered during this exercise)
Q1: Which pattern, and why not the lookalike?
A1: Strategy for base fare selection (Normal/Surge/Night are mutually exclusive —
    only one active at a time, caller picks it). Decorator for Airport pickup
    (additive surcharge that can stack on top of any base fare).
    Discriminator: "Can two of these be active on the same object at once?"
    No -> Strategy (alternatives). Yes -> Decorator (stackable add-ons).

Q2: Which SOLID principle does "add new fare type without editing existing code" map to?
A2: Open-Closed Principle — new fare type/surcharge = new class, zero edits to existing ones.

Q3: Why must a Decorator's constructor accept the same interface it implements (not a
    concrete class or enum)?
A3: So it can wrap ANY FareStrategy — including another decorator — enabling stacking
    (e.g. AirportPickupDecorator(NightFare) or AirportPickupDecorator(SurgeFare)).
    If the constructor took a concrete type/enum, decorators could never wrap each other.

Ruled out similar patterns:
- State: fare type is selected by the caller/context, not transitioned internally by Ride
  based on its own logic — so this is Strategy, not State.
- Treating Surge/Night as decorators: they are mutually exclusive alternatives, not
  stackable layers — that belongs to Strategy, not Decorator.

Rule of thumb:
- Strategy: Context (`Ride`) depends only on the strategy interface; caller swaps the
  strategy at runtime via a setter — no `if/switch` inside Context.
- Decorator: decorator class implements the same interface as what it wraps, and its
  constructor/field is typed to that interface (not a concrete class) so decorators can
  wrap base strategies AND other decorators interchangeably.
- Test before coding: "can two of these be active at once?" -> Decorator (stack).
  "Exactly one, chosen by caller?" -> Strategy (alternative).
*/
public class RideFareDemo {
    public static void main(String[] args) {
        Ride ride = new Ride(10.0);
        ride.setFareStrategy(FareFactory.getFareStrategy(RideType.NORMAL));
        System.out.println(ride.getFare());          // 100.0
        ride.setFareStrategy(FareFactory.getFareStrategy(RideType.SURGE));
        System.out.println(ride.getFare());          // 180.0  — same object, new behavior
        ride.setFareStrategy(FareFactory.getFareStrategy(RideType.NIGHT));
        System.out.println(ride.getFare());          // 150.0  — same object, new behavior

        ride.setFareStrategy(new AirportPickupDecorator(FareFactory.getFareStrategy(RideType.NIGHT)));
        System.out.println(ride.getFare());          // 300.0  — same object, new behavior
        ride.setFareStrategy(new AirportPickupDecorator(FareFactory.getFareStrategy(RideType.SURGE)));
        System.out.println(ride.getFare());          // 330.0  — same object, new behavior



        RideFare normalRide = new AirportRidePrice(new NightRidePrice(new SurgeRidePrice(new RideNormal())));
        System.out.println(normalRide.calculateFare(10.0)); // 380.0
    }
}

enum RideType {
    NORMAL, NIGHT, SURGE
}

class Ride {
    private final double distance;
    private FareStrategy fareStrategy;

    Ride(double distance) {
        this.distance = distance;
    }

    public void setFareStrategy(FareStrategy strategy) {
        this.fareStrategy = strategy;
    }

    public double getFare() {
        return this.fareStrategy.calculateFare(this.distance);
    }
}

class FareFactory {
    public static FareStrategy getFareStrategy(RideType rideType){
        switch (rideType){
            case NORMAL -> {
                return new NormalRideStrategy();
            }
            case NIGHT -> {
                return new NightRideStrategy();
            }
            case SURGE -> {
                return new SurgeRideStrategy();
            }
        }
        throw new IllegalArgumentException("Invalid ride type: " + rideType);
    }
}

interface FareStrategy {
    double BASE_FARE = 10.0;
    double calculateFare(double distance);
}
class NormalRideStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance) {
        return distance*BASE_FARE;
    }
}

class NightRideStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance) {
        return distance*BASE_FARE + 50.0;
    }
}

class SurgeRideStrategy implements FareStrategy {
    @Override
    public double calculateFare(double distance) {
        return distance*BASE_FARE*1.8;
    }
}

class AirportPickupDecorator implements FareStrategy {
    private final FareStrategy fareStrategy;

    AirportPickupDecorator(FareStrategy fareStrategy) {
        this.fareStrategy = fareStrategy;
    }
    @Override
    public double calculateFare(double distance) {
        return fareStrategy.calculateFare(distance) + 150.0;
    }
}




/*
Decorator Pattern Implementation
Decorator pattern allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.
If the Ride is dependent on various factors like surge pricing, night pricing, airport pickup, etc., we can use the decorator pattern to add these behaviors dynamically.
 */
interface RideFare {
    double calculateFare(double distance);
}

class RideNormal implements RideFare {
    @Override
    public double calculateFare(double distance) {
        return distance*10;
    }
}

abstract class RideDecorator implements RideFare {
    protected RideFare rideFare;

    public RideDecorator(RideFare rideFare) {
        this.rideFare = rideFare;
    }

    @Override
    public double calculateFare(double distance) {
        return rideFare.calculateFare(distance);
    }
}

class SurgeRidePrice extends RideDecorator{

    public SurgeRidePrice(RideFare rideFare) {
        super(rideFare);
    }

    @Override
    public double calculateFare(double distance) {
        return rideFare.calculateFare(distance)*1.8;
    }
}

class NightRidePrice extends RideDecorator{

    public NightRidePrice(RideFare rideFare) {
        super(rideFare);
    }

    @Override
    public double calculateFare(double distance) {
        return rideFare.calculateFare(distance) + 50.0;
    }
}

class AirportRidePrice extends RideDecorator {
    public AirportRidePrice(RideFare rideFare) {
        super(rideFare);
    }

    @Override
    public double calculateFare(double distance) {
        return rideFare.calculateFare(distance) + 150.0;
    }
}