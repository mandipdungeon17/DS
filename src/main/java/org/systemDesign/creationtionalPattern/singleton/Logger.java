package org.systemDesign.creationtionalPattern.singleton;

/*
Internally, the enum compiles to something like:
public final class Logger extends Enum<Logger> {
    public static final Logger INSTANCE = new Logger();

    private Logger() {}  // Private constructor

    // Methods...
}
Logger is a clean, thread-safe, reflection-proof, and serialization-safe singleton—the approach recommended by
Joshua Bloch in Effective Java.

Why Enum Singleton is Immune
public enum Logger {
    INSTANCE;
}
Attack                      Enum Protection
Reflection           JVM throws IllegalArgumentException when trying to instantiate enum via reflection
Serialization        Enums are serialized by name, deserialization returns same instance
Cloning              Enums don't implement Cloneable, clone() throws exception
Your Logger enum is protected against all three attacks by default.
 */
public enum Logger {
    INSTANCE;

    public void log(String message){
        System.out.println("LOG " + message);
    }

    public void error(String message){
        System.out.println("ERROR " + message);
    }
}

class LoggerMain {
    public static void main(String[] args) {
        Logger.INSTANCE.log("Application Started");
        Logger.INSTANCE.error("Something Went Wrong");
    }
}
