package org.leetcode.dailyProblems;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

//Time Complexity: O(1) where n is the length of the word
//https://leetcode.com/problems/all-oone-data-structure/submissions/1405949859/?envType=daily-question&envId=2024-09-29
class AllOne {
//    List<String> words;
//    List<Integer> counter;
//    public AllOne() {
//        this.words = new ArrayList<>();
//        this.counter = new ArrayList<>();
//    }
//
//    public void inc(String key) {
//        if(words.contains(key)){
//            int index = words.indexOf(key);
//            counter.set(index, counter.get(index)+1);
//        }
//        else{
//            words.add(key);
//            counter.add(1);
//        }
//    }
//
//    public void dec(String key) {
//        if(words.contains(key)){
//            int index = words.indexOf(key);
//            int num = counter.get(index);
//            if(num == 1){
//                words.remove(index);
//                counter.remove(index);
//            }else{
//                counter.set(index, counter.get(index)-1);
//            }
//        }
//    }
//
//    public String getMaxKey() {
//        if(words.isEmpty()) return "";
//        int max = 0;
//        int index = 0;
//        for(int i=0; i<counter.size(); i++){
//            if(max < counter.get(i)){
//                max = counter.get(i);
//                index = i;
//            }
//        }
//        return words.get(index);
//    }
//
//    public String getMinKey() {
//        if(words.isEmpty()) return "";
//        int min = Integer.MAX_VALUE;
//        int index = 0;
//        for(int i=0; i<counter.size(); i++){
//            if(min > counter.get(i)){
//                min = counter.get(i);
//                index = i;
//            }
//        }
//        return words.get(index);
//    }
//    Map<String, Integer> map;
//    int min;
//    int max;
//    String wordMin;
//    String wordMax;
//    public AllOne() {
//        this.map = new HashMap<>();
//        this.min = Integer.MAX_VALUE;
//        this.max = Integer.MIN_VALUE;
//        this.wordMin = "";
//        this.wordMax = "";
//    }
//
//    public void inc(String key) {
//        map.merge(key, 1, Integer::sum);
//        min = Integer.MAX_VALUE;
//        max = Integer.MIN_VALUE;
////        if(map.size() == 1){
////            wordMin = key;
////            wordMax = key;
////            max = 1;
////        }
////        if(max < map.get(key)){
////            wordMax = key;
////            max = map.get(key);
////        }
////        else{
//            for(Map.Entry<String, Integer> entry : map.entrySet()){
//                if(min >= entry.getValue()){
//                    min = entry.getValue();
//                    wordMin = entry.getKey();
//                }
//                if(max < entry.getValue()){
//                    max = entry.getValue();
//                    wordMax = entry.getKey();
//                }
//            }
////        }
//    }
//
//    public void dec(String key) {
//        boolean flag = false;
//        if(map.isEmpty()) return;
//        if(map.get(key) !=null){
//            if(map.get(key) == 1){
//                map.remove(key);
//                flag = true;
//            }
//            else{
//                map.put(key, map.get(key)-1);
//            }
//        }
//        if(wordMax.equals(key)){
//            wordMax = "";
//            max = Integer.MIN_VALUE;
//        }
//        else if(wordMin.equals(key)){
//            if(flag){
//                wordMin = "";
//                min = Integer.MAX_VALUE;
//            }else{
//                min--;
//            }
//        }
//        if(wordMin.isEmpty() || wordMax.isEmpty()){
//            for(Map.Entry<String, Integer> entry : map.entrySet()){
//                if(max < entry.getValue()){
//                    max = entry.getValue();
//                    wordMax = entry.getKey();
//                }
//                else if(min > entry.getValue()){
//                    min = entry.getValue();
//                    wordMin = entry.getKey();
//                }
//            }
//        }
//    }
//
//    public String getMaxKey() {
//        if(map.isEmpty()) return "";
//        return wordMax;
//    }
//
//    public String getMinKey() {
//        if(map.isEmpty()) return "";
//        return wordMin;
//    }

    private final Map<String, Integer> countMap;
    private final TreeMap<Integer, LinkedHashSet<String>> keysMap;
    private String minKey = "", maxKey = "";

    public AllOne() {
        countMap = new HashMap<>();
        keysMap = new TreeMap<>();
    }

    public void inc(String key) {
        if (countMap.containsKey(key)) {
            int count = countMap.get(key);
            keysMap.get(count).remove(key);
            if (keysMap.get(count).isEmpty()) {
                keysMap.remove(count);
            }
            countMap.put(key, count + 1);
            keysMap.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
        } else {
            countMap.put(key, 1);
            keysMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        }
        minKey = keysMap.firstEntry().getValue().iterator().next();
        maxKey = keysMap.lastEntry().getValue().iterator().next();

        /*if (countMap.containsKey(key)) {
        int count = countMap.get(key);
        keysMap.get(count).remove(key);
        if (keysMap.get(count).isEmpty()) {
            keysMap.remove(count);
        }
        countMap.put(key, count + 1);

        // Simplified computeIfAbsent
        LinkedHashSet<String> set = keysMap.get(count + 1);
        if (set == null) {
            set = new LinkedHashSet<>();
            keysMap.put(count + 1, set);
        }
        set.add(key);
    } else {
        countMap.put(key, 1);

        // Simplified computeIfAbsent
        LinkedHashSet<String> set = keysMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            keysMap.put(1, set);
        }
        set.add(key);
    }
    minKey = keysMap.firstEntry().getValue().iterator().next();
    maxKey = keysMap.lastEntry().getValue().iterator().next();*/
    }

    public void dec(String key) {
        if (countMap.containsKey(key)) {
            int count = countMap.get(key);
            keysMap.get(count).remove(key);
            if (keysMap.get(count).isEmpty()) {
                keysMap.remove(count);
            }
            if (count == 1) {
                countMap.remove(key);
            } else {
                countMap.put(key, count - 1);
                keysMap.computeIfAbsent(count - 1, k -> new LinkedHashSet<>()).add(key);
            }
        }
        if (keysMap.isEmpty()) {
            minKey = "";
            maxKey = "";
        } else {
            minKey = keysMap.firstEntry().getValue().iterator().next();
            maxKey = keysMap.lastEntry().getValue().iterator().next();
        }

        /*if (countMap.containsKey(key)) {
        int count = countMap.get(key);
        keysMap.get(count).remove(key);
        if (keysMap.get(count).isEmpty()) {
            keysMap.remove(count);
        }
        if (count == 1) {
            countMap.remove(key);
        } else {
            countMap.put(key, count - 1);

            // Simplified computeIfAbsent
            LinkedHashSet<String> set = keysMap.get(count - 1);
            if (set == null) {
                set = new LinkedHashSet<>();
                keysMap.put(count - 1, set);
            }
            set.add(key);
        }
    }
    if (keysMap.isEmpty()) {
        minKey = "";
        maxKey = "";
    } else {
        minKey = keysMap.firstEntry().getValue().iterator().next();
        maxKey = keysMap.lastEntry().getValue().iterator().next();
    }*/
    }

    public String getMaxKey() {
        return maxKey;
    }

    public String getMinKey() {
        return minKey;
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
//        System.out.println(allOne.getMaxKey()); // return ""
//        System.out.println(allOne.getMinKey()); // return ""
//        allOne.inc("hello");
//        allOne.inc("hello");
//        System.out.println(allOne.getMaxKey()); // return "hello"
//        System.out.println(allOne.getMinKey()); // return "hello"
//        allOne.inc("leet");
//        System.out.println(allOne.getMaxKey()); // return "hello"
//        System.out.println(allOne.getMinKey()); // return "leet"
//        allOne.inc("a");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("b");
//        System.out.println(allOne.getMaxKey());
//        System.out.println(allOne.getMinKey());
//        allOne.dec("b");
//        allOne.dec("b");
//        System.out.println(allOne.getMaxKey());
//        System.out.println(allOne.getMinKey());
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
//        allOne.dec("b");
//        allOne.dec("b");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        //["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
        //[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
    }
}