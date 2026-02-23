package org.leetcode.dailyProblems;

import java.util.TreeMap;

public class MyCalendarTwo {
    TreeMap<Integer, Integer> noOverLap;
    TreeMap<Integer, Integer> overLap;
    public MyCalendarTwo() {
        this.noOverLap = new TreeMap<>();
        this.overLap = new TreeMap<>();
    }

    //10,30 5,20
    public boolean book(int start, int end) {
        Integer noOverLapLowerVal = noOverLap.lowerKey(end);
        // 10 ---------------------------- 20
        //          15 ------------------------------------- 25
        //          15 ------------------ 20
        //                  17 -------------------23
        //         14 ----------------- 21
        if(noOverLapLowerVal!= null && start < noOverLap.get(noOverLapLowerVal)){
            Integer overLapLowerVal = overLap.lowerKey(end);
            if(overLapLowerVal != null && start < overLap.get(overLapLowerVal)){
                return false;
            }
            this.overLap.put(Math.max(start, noOverLapLowerVal), Math.min(noOverLap.get(noOverLapLowerVal), end));
            return true;
        }
        noOverLap.put(start, end);
        return true;
//        Integer noOverLapLowerVal = noOverLap.lowerKey(end);
//        if(noOverLapLowerVal!= null && start<= noOverLap.get(noOverLapLowerVal)-1){
//            Integer overLapLowerVal = overLap.lowerKey(end);
//            if(overLapLowerVal != null && start <= overLap.get(overLapLowerVal)-1){
//                return false;
//            }
//            this.overLap.put(start, noOverLap.get(noOverLapLowerVal));
//            return true;
//        }
//        noOverLap.put(start, end);
//        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
//        System.out.println(myCalendarTwo.book(10, 20)); // return True, The event can be booked.
//        System.out.println(myCalendarTwo.book(50, 60)); // return True, The event can be booked.
//        System.out.println(myCalendarTwo.book(10, 40)); // return True, The event can be double booked.
//        System.out.println(myCalendarTwo.book(5, 15));  // return False, The event cannot be booked, because it would result in a triple booking.
//        System.out.println(myCalendarTwo.book(5, 10)); // return True, The event can be booked, as it does not use time 10 which is already double booked.
//        System.out.println(myCalendarTwo.book(25, 55)); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.


//        System.out.println(myCalendarTwo.book(28, 46));
//        System.out.println(myCalendarTwo.book(9, 21));
//        System.out.println(myCalendarTwo.book(21, 39));
//        System.out.println(myCalendarTwo.book(37, 48));
//        System.out.println(myCalendarTwo.book(38, 50));
//        System.out.println(myCalendarTwo.book(22, 39));
//        System.out.println(myCalendarTwo.book(45, 50));
//        System.out.println(myCalendarTwo.book(1, 12));
//        System.out.println(myCalendarTwo.book(40, 50));
//        System.out.println(myCalendarTwo.book(31, 44));

            int[][] testCases = {
                    {47, 50}, {1, 10}, {27, 36}, {40, 47}, {20, 27}, {15, 23}, {10, 18}, {27, 36}, {17, 25}, {8, 17},
                    {24, 33}, {23, 28}, {21, 27}, {47, 50}, {14, 21}, {26, 32}, {16, 21}, {2, 7}, {24, 33}, {6, 13},
                    {44,50}, {33,39},{30,36},{6,15},{21,27},{49,50},{38,45},{4,12},{46,50},{13,21}
            };
            for(int[] testCase : testCases){
                System.out.print(myCalendarTwo.book(testCase[0], testCase[1])+ ",");
            }
    }
}
