package org.systemDesign.creationtionalPattern.singleton;

import java.util.Objects;

//Thread Safe
/*
Without volatile, even with synchronized, a problem can occur due to instruction reordering.
The Issue: Partial Object Construction
When instance = new ConfigurationManager() executes, the JVM performs three steps:
1. Allocate memory for the object
2. Initialize the object (run constructor)
3. Assign the reference to instance
The JVM/CPU may reorder steps 2 and 3 for optimization:
Allocate memory
Assign reference to instance ← happens before construction completes
Initialize the object
The Problem Scenario
Thread A                          Thread B
────────                          ────────
enters synchronized block
allocates memory
assigns reference to instance
                                  checks if(instance == null) → false
                                  returns instance (not fully constructed!)
                                  accesses appName → null or garbage
finishes constructor
exits synchronized block
Thread B sees instance != null at the first check (outside synchronized), so it returns immediately without entering the
synchronized block—but the object isn't fully initialized yet.
What volatile Does
volatile prevent this reordering by establishing a happens-before relationship. It guarantees that all writes before
the assignment to instance (including constructor initialization) are visible to any thread that reads instance.
In short: synchronized protects the block, but volatile ensures threads outside the block see a fully constructed object.
Non-volatile variables still use cache normally. Only the volatile variable (instance) goes directly to/from main memory.
 */
public class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private final String appName;
    private final String version;

    private ConfigurationManager(){
        appName = "MyApp";
        version = "1.0";
    }

    public static ConfigurationManager getInstance(){
        if(Objects.isNull(instance)){
            synchronized (ConfigurationManager.class){
                if (Objects.isNull(instance)){
                    instance = new ConfigurationManager();
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
}

class ConfigMain {

    public static void main(String[] args) {
        ConfigurationManager cg = ConfigurationManager.getInstance();
        System.out.println("App- " + cg.getAppName() + ":" +cg.getVersion());
    }
}
