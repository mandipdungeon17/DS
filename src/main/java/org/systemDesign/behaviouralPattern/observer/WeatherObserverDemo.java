package org.systemDesign.behaviouralPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherObserverDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        station.setMeasurements(26, 65, 1013.1f);
        station.setMeasurements(28, 70, 1012.5f);
        station.setMeasurements(24, 90, 1011.0f);
        // Displays auto-register themselves
        CurrentConditionsDisplay current = new CurrentConditionsDisplay(station);
        StatisticsDisplay stats = new StatisticsDisplay(station);
        ForecastDisplay forecast = new ForecastDisplay(station);
        // Simulate weather changes
        station.setMeasurements(26, 65, 1013.1f);
        station.setMeasurements(28, 70, 1012.5f);
        station.setMeasurements(24, 90, 1011.0f);
    }
}
// Observer interface (Pull model - observers pull data)
interface WeatherObserver {
    void update();
}
// Concrete Subject
class WeatherStation {
    private final List<WeatherObserver> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    public WeatherStation(){
        this.observers = new ArrayList<>();
    }
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        System.out.println(observers.size());
        for (WeatherObserver observer : observers) {
            observer.update();
        }
    }
    public void setMeasurements(float temp, float humidity, float pressure){
        System.out.println("\n=== Weather Update ===");
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
    // Getters for pull model
    public float getTemperature() {
        return temperature;
    }
    public float getHumidity() {
        return humidity;
    }
    public float getPressure() {
        return pressure;
    }
}
// Concrete Observer
class CurrentConditionsDisplay implements WeatherObserver{
    private final WeatherStation weatherStation;
    public CurrentConditionsDisplay(WeatherStation weatherStation){
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }
    @Override
    public void update() {
        float temp = weatherStation.getTemperature();
        float humidity = weatherStation.getHumidity();
        System.out.println("Current conditions: " + temp + "°C and " +
                humidity + "% humidity");
    }
}
class StatisticsDisplay implements WeatherObserver {
    private final WeatherStation weatherStation;
    private float maxTemp = 0;
    private float minTemp = 200;
    private int numReadings = 0;

    public StatisticsDisplay(WeatherStation station) {
        this.weatherStation = station;
        weatherStation.registerObserver(this);
    }
    @Override
    public void update() {
        float temp = weatherStation.getTemperature();
        numReadings++;
        if(temp > maxTemp) maxTemp = temp;
        if (temp < minTemp) minTemp = temp;
        System.out.println("Statistics: Max=" + maxTemp + "°C, Min=" +
                minTemp + "°C, Readings=" + numReadings);
    }
}
class ForecastDisplay implements WeatherObserver {
    private final WeatherStation weatherStation;
    private float lastPressure = 0;

    public ForecastDisplay(WeatherStation station) {
        this.weatherStation = station;
        weatherStation.registerObserver(this);
    }

    public void update() {
        float pressure = weatherStation.getPressure();
        if (pressure > lastPressure) {
            System.out.println("Forecast: Improving weather on the way!");
        } else if (pressure < lastPressure) {
            System.out.println("Forecast: Watch out for cooler, rainy weather");
        } else {
            System.out.println("Forecast: More of the same");
        }
        lastPressure = pressure;
    }
}
