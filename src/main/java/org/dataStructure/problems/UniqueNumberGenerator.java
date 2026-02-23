package org.dataStructure.problems;

import java.util.concurrent.atomic.AtomicLong;

public class UniqueNumberGenerator {
    private static final AtomicLong counter = new AtomicLong(0);

    public static String generateUniqueNumber() {
        long currentValue = counter.incrementAndGet();
        if (currentValue > 9999999999L) {
            counter.set(1);
            currentValue = 1;
        }
        return "GLPN-" + String.format("%010d", currentValue);
    }

    public static void main(String[] args) {
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
        System.out.println(generateUniqueNumber());
    }
}