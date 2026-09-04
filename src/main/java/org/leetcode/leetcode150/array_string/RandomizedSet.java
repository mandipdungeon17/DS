package org.leetcode.leetcode150.array_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//https://leetcode.com/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150
public class RandomizedSet {

    // It took 120ms.
//    private Map<Integer, Integer> map;
//    public RandomizedSet() {
//        this.map = new HashMap<>();
//    }
//
//    public boolean insert(int val) {
//        if (map.containsKey(val)) {
//            return false;
//        } else {
//            map.put(val, 0);
//            return true;
//        }
//    }
//
//    public boolean remove(int val) {
//        if (map.containsKey(val)) {
//            map.remove(val);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public int getRandom() {
//        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
//        int randomIndex = (int) (Math.random() * keys.size());
//        return keys.get(randomIndex);
//    }

    // It took 215ms
    private final ArrayList<Integer> list;
    private final Map<Integer, Integer> map;
    private final Random rand;
    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.rand = new Random();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val);
            int lastElement = list.getLast();
            list.set(index, lastElement);
            map.put(lastElement, index);
            list.removeLast();
            map.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int randomIndex = rand.nextInt(list.size());//(int) (Math.random() * list.size());
        return list.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.remove(2);
        int param_3 = obj.getRandom();

        System.out.println("Insert 1: " + param_1);
        System.out.println("Remove 2: " + param_2);
        System.out.println("Random: " + param_3);

    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
