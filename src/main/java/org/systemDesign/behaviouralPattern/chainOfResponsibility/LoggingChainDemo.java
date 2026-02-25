package org.systemDesign.behaviouralPattern.chainOfResponsibility;

public class LoggingChainDemo {
    public static void main(String[] args) {
        Logger loggerChain = LoggingSystem.getChainOfLogger();
        System.out.println("=== DEBUG Message ===");
        loggerChain.logMessage(Logger.DEBUG, "Debug information");

        System.out.println("\n=== INFO Message ===");
        loggerChain.logMessage(Logger.INFO, "Information message");

        System.out.println("\n=== WARNING Message ===");
        loggerChain.logMessage(Logger.WARNING, "Warning: Low disk space");

        System.out.println("\n=== ERROR Message ===");
        loggerChain.logMessage(Logger.ERROR, "Error: System crash");
    }
}
// Handler
abstract class Logger {
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    protected int level;
    protected Logger nextLogger;
    public void setNextLogger(Logger nextLogger){
        this.nextLogger = nextLogger;
    }
    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if(null != nextLogger){
            nextLogger.logMessage(level, message);
        }
    }
    protected abstract void write(String message);
}
//Concrete Handlers
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("[CONSOLE] " + message);
    }
}
//Concrete Handlers
class FileLogger extends Logger {
    public FileLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        System.out.println("[FILE] Writing to file: " + message);
    }
}
class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("[ERROR LOG] ⚠️  " + message);
    }
}
// Usage
class LoggingSystem {
    public static Logger getChainOfLogger(){
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.WARNING);
        Logger consoleLogger = new ConsoleLogger(Logger.DEBUG);
        // Build chain: Console → File → Error
        consoleLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(errorLogger);
        return consoleLogger;
    }
}

