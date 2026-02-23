package org.systemDesign.creationtionalPattern.singleton.failure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

class SerializeSingleton implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static volatile SerializeSingleton instance;
    private final String appName;
    private final String version;

    private SerializeSingleton(){
        appName = "MyApp";
        version = "1.0";
    }

    public static SerializeSingleton getInstance(){
        if(Objects.isNull(instance)){
            synchronized (SerializeSingleton.class){
                if(Objects.isNull(instance)){
                    instance = new SerializeSingleton();
                }
            }
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    //To prevent this attack, add readResolve():
    @Serial
    protected Object readResolve() {
        return getInstance(); // Return existing singleton
    }
}

public class SerializationAttack {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Get first instance normally
        SerializeSingleton instance1 = SerializeSingleton.getInstance();

        //Serialize
        /*
        Creates a FileOutputStream that opens/creates file singleton.ser in binary write mode
        Wraps it with ObjectOutputStream which converts Java objects to byte stream
         */
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("singleton.ser"));
        /*
        Serialization happens here
        JVM inspects instance1 and writes:
        Class metadata (class name, serialVersionUID)
        Field names and types
        Field values (appName = "MyApp", version = "1.0")
        static fields (instance) are NOT serialized (they belong to class, not object)
         */
        out.writeObject(instance1);
        //Flushes remaining bytes to file and releases resources
        out.close();

        //Deserialize - create NEW instance
        /*
        Opens singleton.ser in binary read mode
        Wraps with ObjectInputStream to convert byte stream back to objects
         */
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("singleton.ser"));

        /*
        Deserialization happens here
        JVM does the following:
        Reads class metadata from file
        Allocates memory for NEW object (bypasses constructor!)
        Reads field values and assigns them directly to the new object
        Checks if readResolve() exists → if yes, returns its result instead
         */
        SerializeSingleton instance2 = (SerializeSingleton) in.readObject();
        //Releases file resources
        in.close();

        /*
        Why Singleton Breaks (Without readResolve)
        Step                    What Happens
        writeObject()       Saves object state to bytes
        readObject()        Creates brand new object in memory
        Result              instance1 != instance2 (two different objects)

        How readResolve() Fixes It
        @Serial
        protected Object readResolve() {
            return getInstance(); // Return existing singleton
        }
        After readObject() reconstructs the new object, JVM checks for readResolve():
        If present → discards the newly created object
        Returns getInstance() instead → the original singleton
        Result: instance1 == instance2 is true
         */
        System.out.println(instance1 == instance2); // false - Singleton broken by Serialization/Deserialization!
                                                    // true - After handling it with readResolve
    }
}
