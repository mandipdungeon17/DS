package org.leetcode.dailyProblems;

import java.util.TreeMap;

//https://leetcode.com/problems/my-calendar-i/submissions/1404556859/?envType=daily-question&envId=2024-09-26
//Time Complexity: O(n) and Space Complexity: O(n). It's a brute force approach and it took 179ms to execute
public class MyCalendarOne {
//    List<Integer> startTime;
//    List<Integer> endTime;
//    public MyCalendarOne() {
//        startTime = new ArrayList<>();
//        endTime = new ArrayList<>();
//    }
//
//    public boolean book(int start, int end) {
//        if (!startTime.isEmpty()) {
//        for (int i = 0; i < startTime.size(); i++) {
//            if (start >= startTime.get(i) && start <= endTime.get(i) - 1 ||
//                    end - 1 >= startTime.get(i) && end - 1 <= endTime.get(i) - 1 ||
//                    start < startTime.get(i) && end - 1 > endTime.get(i) - 1) {
//                return false;
//            }
//        }
//    }
//        startTime.add(start);
//        endTime.add(end);
//        return true;
//    }

    //Time Complexity: O(logn) and Space Complexity: O(n). It took 20ms to execute
    TreeMap<Integer, Integer> treeMap;
    public MyCalendarOne() {
        treeMap = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prevVal = treeMap.lowerKey(end);
        if(prevVal != null && start <= treeMap.get(prevVal)-1){
            return false;
        }
        treeMap.put(start,end);
        return true;
    }

    public static void main(String[] args) {
        MyCalendarOne myCalendar = new MyCalendarOne();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
        System.out.println(myCalendar.book(5, 10));
    }
}
