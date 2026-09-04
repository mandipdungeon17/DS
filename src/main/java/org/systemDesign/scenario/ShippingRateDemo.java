package org.systemDesign.scenario;

import java.util.ArrayList;
import java.util.List;

/*
🎯 Scenario 5 — Courier SDK Rate Integration

Context
An e-commerce app wants to show the cheapest shipping rate across three courier
SDKs it has integrated: BlueDartSDK, DelhiveryClient, FedExApi. Each third-party SDK
has a completely different, incompatible method signature and unit system:
  BlueDartSDK.getRate(double weightKg, double distanceKm)      -> returns INR
  DelhiveryClient.fetchShippingCost(int grams, int km)         -> returns INR
  FedExApi.calculate(double weightLbs, double distanceMiles)   -> returns USD

Original problem: the app's `CheapestRateFinder` had to call each SDK differently and
convert units inline, duplicating conversion math each time a new courier was tried.

Requirements
1. The app must call all couriers through ONE common interface/method signature.
2. Each SDK's own units (kg vs grams, km vs miles, INR vs USD) must be converted to a
   single common unit (kg, km, INR) transparently to the caller.
3. Adding a fourth courier SDK must not require modifying the cheapest-rate-finding
   loop or any existing adapter.
4. main must find and print the cheapest rate among all registered couriers for a
   sample shipment.

Q&A (asked and answered during this exercise)
Q1: Name the pattern.
A1: Adapter — one wrapper class per SDK, each implementing a common
    `ShippingRateProvider` (or similar) interface, translating that SDK's own method
    signature/units into the interface's expected signature/units.

Q2: Why must each adapter hold the SDK client via composition (a field), not by
    extending the SDK class?
A2: The SDKs weren't designed to share a supertype and Java doesn't allow multiple
    inheritance of classes; composition lets the adapter hold any SDK instance and
    translate calls to it, keeping the adapter decoupled from the SDK's internals.

Q3: Where should unit/currency conversion math live, and why?
A3: Entirely inside each adapter's implementation of the common method — never in the
    cheapest-rate-finding loop. The loop should treat every provider identically via
    the common interface and know nothing about grams-vs-kg or USD-vs-INR.

Bugs found and fixed during review:
- Double-invocation bug: the cheapest-rate loop called `provider.getRate(...)` twice
  per provider (once to print, once to compare) — fixed by calling once and storing
  the result in a local variable.
- Encapsulation: the provider list was exposed as a mutable public field — fixed by
  keeping it private with a controlled way to add providers.
- Math errors: USD-to-INR conversion factor and the FedEx large-size price constant
  were wrong — fixed after tracing actual vs expected output.

Ruled out similar patterns:
- Facade: Facade invents a brand-new simplified interface over many unrelated
  subsystems; Adapter conforms ONE already-incompatible class to an ALREADY EXISTING
  target interface the client already expects.
- Chain of Responsibility: all providers are queried and compared for the cheapest
  rate — this is "ask everyone, pick the best," not "first handler that can process
  wins."

Rule of thumb:
- Define one target interface the application code depends on.
- Build exactly one adapter per third-party API; keep ALL unit/currency conversion
  math inside that adapter, never leaking into calling code.
- Adapter holds the SDK client as a field (composition), not via inheritance from the
  SDK class.
*/
public class ShippingRateDemo {
    public static void main(String[] args) {
        String origin = "110001";
        String destination = "560001";
        double weight = 2.5;

        ShippingService shippingService = new ShippingService(origin, destination, weight);

        ShippingRateProvider delhivery = new DelhiveryAdapter();
        ShippingRateProvider blueDart = new BlueDartAdapter();
        ShippingRateProvider fedEx = new FedexAdapter();

        double dRate = shippingService.getShippingRate(delhivery);
        double bRate = shippingService.getShippingRate(blueDart);
        double fRate = shippingService.getShippingRate(fedEx);

        System.out.println("Delhivery Rate: " + dRate);
        System.out.println("BlueDart Rate: " + bRate);
        System.out.println("FedEx Rate: " + fRate);

        shippingService.addShippingRateProvider(delhivery);
        shippingService.addShippingRateProvider(blueDart);
        shippingService.addShippingRateProvider(fedEx);
        System.out.println("Cheapest Rate: " + shippingService.findCheapestRate());
    }
}

class DelhiveryClient {
    public double calculateShipping(String origin, String dest, double weightKg) {
        System.out.printf("Calculating shipping from %s to %s for %.2f kg using Delhivery...\n", origin, dest, weightKg);
        return weightKg*45; // Dummy calculation
    }
}
class BlueDartRequest {
    final String originPin;
    final String destinationPin;
    final int weightInGrams;
    BlueDartRequest(String srcPin, String dstPin, int weightGrams) {
        // Initialize the request with source pin, destination pin, and weight in grams
        this.originPin = srcPin;
        this.destinationPin = dstPin;
        this.weightInGrams = weightGrams;
    }
}
class BlueDartService {
    public long fetchPriceInPaise(BlueDartRequest request) {
        System.out.println("Fetching price from BlueDart service...");
        return request.weightInGrams*5L; // Dummy calculation
    }
}
class FedExRateEngine {
    public FedExRateResponse getRate(String originZip, String destZip, double weightLbs, String currency) {
        System.out.printf("Getting rate from FedEx for %s to %s, %.2f lbs, currency: %s...\n", originZip, destZip, weightLbs, currency);
        FedExRateResponse response = new FedExRateResponse();
        response.amount = weightLbs * 0.6; // Dummy calculation
        return response;
    }
}
class FedExRateResponse {
    double amount;
    public double getAmount() {
        return amount;
    }
    public String getCurrency() {
        return "USD";
    }
}

interface ShippingRateProvider {
    String findShippingName();
    double calculateShippingRate(String source, String destination, double weightKg);
}

class DelhiveryAdapter implements ShippingRateProvider {
    private final DelhiveryClient delhiveryClient;
    public DelhiveryAdapter(){
        this.delhiveryClient = new DelhiveryClient();
    }
    @Override
    public String findShippingName(){
        return "Delhivery";
    }
    @Override
    public double calculateShippingRate(String source, String destination, double weightKg) {
        return this.delhiveryClient.calculateShipping(source, destination, weightKg);
    }
}

class BlueDartAdapter implements ShippingRateProvider {
    private final BlueDartService blueDartService;
    public BlueDartAdapter(){
        this.blueDartService = new BlueDartService();
    }
    @Override
    public String findShippingName(){
        return "BlueDart";
    }
    @Override
    public double calculateShippingRate(String source, String destination, double weightKg) {
        BlueDartRequest request = new BlueDartRequest(source, destination, (int)(weightKg*1000));
        return this.blueDartService.fetchPriceInPaise(request)/100.0;
    }
}

class FedexAdapter implements ShippingRateProvider {
    private static final double USD_TO_INR = 83.0;
    private static final double KG_TO_LB = 2.20462;

    private final FedExRateEngine fedExRateEngine;

    public FedexAdapter(){
        this.fedExRateEngine = new FedExRateEngine();
    }

    @Override
    public String findShippingName(){
        return "FedEx";
    }
    @Override
    public double calculateShippingRate(String source, String destination, double weightKg) {
        return this.fedExRateEngine.getRate(source, destination, weightKg*KG_TO_LB, "USD").getAmount()*USD_TO_INR;
    }
}

class ShippingService{
    private final List<ShippingRateProvider> shippingRateProviders;
    private final String originPin;
    private final String destinationPin;
    private final double weightInKg;

    ShippingService(String originPin, String destinationPin, double weightInKg){
        this.shippingRateProviders = new ArrayList<>();
        this.originPin = originPin;
        this.destinationPin = destinationPin;
        this.weightInKg = weightInKg;
    }

    public void addShippingRateProvider(ShippingRateProvider shippingRateProvider){
        this.shippingRateProviders.add(shippingRateProvider);
    }

    public double getShippingRate(ShippingRateProvider shippingRateProvider){
        return shippingRateProvider.calculateShippingRate(originPin, destinationPin, weightInKg);
    }

    public String findCheapestRate(){
        double min = Double.MAX_VALUE;
        String name = null;

        for (ShippingRateProvider shippingRateProvider : shippingRateProviders){
            double rate = getShippingRate(shippingRateProvider);
            if(min > rate){
                min = rate;
                name  = shippingRateProvider.findShippingName();
            }
        }
        return name + ":" + min;
    }
}
