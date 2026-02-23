package org.systemDesign.creationtionalPattern.singleton.failure;

import java.util.Objects;

class CloneSingleton implements Cloneable{
    private static volatile CloneSingleton instance;
    private final String appName;
    private final String version;

    private CloneSingleton(){
        appName = "MyApp";
        version = "1.0";
    }

    public static CloneSingleton getInstance(){
        if(Objects.isNull(instance)){
            synchronized (CloneSingleton.class){
                if(Objects.isNull(instance)){
                    instance = new CloneSingleton();
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

    @Override
    public Object clone() throws CloneNotSupportedException {
//        return super.clone();  // Allows cloning - breaks singleton!
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }
}

public class CloneAttack {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Get first instance normally
        CloneSingleton instance1 = CloneSingleton.getInstance();
        CloneSingleton instance2 = (CloneSingleton) instance1.clone();
        System.out.println(instance1 == instance2); // false, if clone returns super.clone, breaks Singleton

    }
}
