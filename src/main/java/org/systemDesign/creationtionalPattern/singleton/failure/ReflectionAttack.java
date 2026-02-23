package org.systemDesign.creationtionalPattern.singleton.failure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

class ReflectionSingleton{
    private static volatile ReflectionSingleton instance;
    private final String appName;
    private final String version;

    private ReflectionSingleton(){
        appName = "MyApp";
        version = "1.0";

    //Reflection Prevention: Throw exception in constructor if instance exists, or use enum (reflection can't instantiate enums).
//        if(Objects.nonNull(instance)){
//            throw new RuntimeException("Use GetInstance");
//        }
    }

    public static ReflectionSingleton getInstance(){
        if(Objects.isNull(instance)){
            synchronized (ReflectionSingleton.class){
                if(Objects.isNull(instance)){
                    instance = new ReflectionSingleton();
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

public class ReflectionAttack {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        // Get first instance normally
        ReflectionSingleton instance1 = ReflectionSingleton.getInstance();

        // Break singleton using reflection
        Constructor<ReflectionSingleton> constructor =
                ReflectionSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true); // Bypass private

        ReflectionSingleton instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2); //false, if reflection not handled
    }
}
