package org.systemDesign.structuralPattern.adapter;

public class TemperatureAdapterDemo {
    public static void main(String[] args) {
        LegacyTemperatureSensor sensor = new LegacyTemperatureSensor();
        // Adapt legacy sensor to new interface
        TemperatureAdapter adapter = new TemperatureAdapter(sensor);
        HealthMonitor healthMonitor = new HealthMonitor();
        healthMonitor.checkTemperature(adapter);
    }
}
// Adaptee (legacy system - returns Fahrenheit)
class LegacyTemperatureSensor{
    public double getTemperature(){
        return 98.6; // Body temperature in Fahrenheit
    }
}
// Target interface (new system)
interface TemperatureSensor {
    double getTemperatureInCelsius();
}
//Adapter
class TemperatureAdapter implements TemperatureSensor{
    //Adaptee
    private final LegacyTemperatureSensor legacyTemperatureSensor;
    public TemperatureAdapter (LegacyTemperatureSensor legacyTemperatureSensor){
        this.legacyTemperatureSensor = legacyTemperatureSensor;
    }
    @Override
    public double getTemperatureInCelsius() {
        double fahrenheit = legacyTemperatureSensor.getTemperature();
        // Convert Fahrenheit to Celsius
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}
// New system
class HealthMonitor{
    public void checkTemperature(TemperatureAdapter temperatureAdapter){
        double temp = temperatureAdapter.getTemperatureInCelsius();
        System.out.println("Temperature: " + temp + "°C");
        if (temp > 37.5) {
            System.out.println("Warning: High fever detected!");
        } else {
            System.out.println("Temperature normal");
        }
    }
}
