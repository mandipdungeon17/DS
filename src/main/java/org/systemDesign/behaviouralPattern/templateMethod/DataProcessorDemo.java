package org.systemDesign.behaviouralPattern.templateMethod;

public class DataProcessorDemo {
    public static void main(String[] args) {
        System.out.println("=== CSV Processing ===");
        DataProcessor csvProcessor = new CSVDataProcessor();
        csvProcessor.process();

        System.out.println("=== JSON Processing ===");
        DataProcessor jsonProcessor = new JSONDataProcessor();
        jsonProcessor.process();

        System.out.println("=== XML Processing ===");
        DataProcessor xmlProcessor = new XMLDataProcessor();
        xmlProcessor.process();
    }
}
// Abstract class with template method
abstract class DataProcessor {
    // Template method (final - can't be overridden)
    public final void process(){
        readData();
        processData();
        if(isValidationRequired()){ // Hook method
            validateData();
        }
        saveData();
        System.out.println("Processing complete!\n");
    }
    // Abstract methods (must be implemented by subclasses)
    protected abstract void readData();
    protected abstract void processData();
    protected abstract void saveData();
    protected void validateData(){
        System.out.println("Validating data...");
    }
    // Hook method (optional - subclass can override)
    protected boolean isValidationRequired(){
        return true; // Default behavior
    }
}
// Concrete class 1: CSV processor
class CSVDataProcessor extends DataProcessor{
    @Override
    protected void readData() {
        System.out.println("Reading data from CSV file");
    }
    @Override
    protected void processData() {
        System.out.println("Processing CSV data: Converting to objects");
    }
    @Override
    protected void saveData() {
        System.out.println("Saving CSV data to database");
    }
}
// Concrete class 2: JSON Processor
class JSONDataProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading data from JSON file");
    }
    protected void processData() {
        System.out.println("Processing JSON data: Parsing JSON");
    }
    protected void saveData() {
        System.out.println("Saving JSON data to database");
    }
    // Override hook - no validation needed
    protected boolean isValidationRequired() {
        return false;
    }
}
// Concrete class 3: XML Processor
class XMLDataProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading data from XML file");
    }
    protected void processData() {
        System.out.println("Processing XML data: Parsing XML tags");
    }
    protected void saveData() {
        System.out.println("Saving XML data to database");
    }
}
