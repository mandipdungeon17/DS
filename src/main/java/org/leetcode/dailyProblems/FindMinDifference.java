package org.leetcode.dailyProblems;

import java.util.List;

//https://leetcode.com/problems/minimum-time-difference/submissions/1393257159/?envType=daily-question&envId=2024-09-16
public class FindMinDifference {
    //Time complexity: O(nlogn) and Space complexity: O(n)
//    public int findMinDifference(List<String> timePoints) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        List<LocalTime> localTimes = new ArrayList<>();
//        for(String timeString : timePoints){
//            LocalTime time = LocalTime.parse(timeString, formatter);
//            localTimes.add(time);
//        }
//        Collections.sort(localTimes);
//        int min = Integer.MAX_VALUE;
//        for(int i=1; i< localTimes.size(); i++){
//            int diff = (int)Math.abs(Duration.between(localTimes.get(i-1),localTimes.get(i)).toMinutes());
//            if(diff > 720) diff = 1440 - diff;
//            min = Math.min(min, diff);
//        }
//        int diff = (int)Math.abs(Duration.between(localTimes.get(0),localTimes.get(localTimes.size()-1)).toMinutes());
//        if(diff > 720) diff = 1440 - diff;
//        min = Math.min(min, diff);
//        return min;
//    }

    //Time complexity: O(nlogn) and Space complexity: O(n)
    // Without using Date time API
//    public int findMinDifference(List<String> timePoints) {
//        List<Integer> list = new ArrayList<>();
//        for(String time : timePoints){
//            int value = convertToMinutes(time);
//            list.add(value);
//        }
//        Collections.sort(list);
//        int min = Integer.MAX_VALUE;
//        for(int i=1; i<list.size(); i++){
//            min = Math.min(min,Math.abs(list.get(i-1)-list.get(i)));
//        }
//        if(list.get(0) < 720 && list.get(list.size()-1)>720) min = Math.min(min, Math.abs(1440+list.get(0)-list.get(list.size()-1)));
//        return min;
//    }

    //Time complexity: O(n) and Space complexity: O(1)
    //Without using Sorting
    public int findMinDifference(List<String> timePoints) {
        if(timePoints.size() > 1440) return 0;
        boolean[] seen = new boolean[1440];
        for(String time : timePoints){
            int minutes = convertToMinutes(time);
            if(seen[minutes]) return 0;
            seen[minutes] = true;
        }
        int min = Integer.MAX_VALUE; int prev = Integer.MAX_VALUE; int first = Integer.MAX_VALUE;
        for(int i=0; i<1440; i++){
            if(seen[i]){
                if(first == Integer.MAX_VALUE) first = i;
                else min = Math.min(min, i-prev);
                prev = i;
            }
        }
        if(first < 720 && prev>720) min = Math.min(min, 1440 + first - prev);
        return min;
    }

    public int convertToMinutes(String time){
        String[] str = time.split(":");
        return Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
    }

    public static void main(String[] args) {
        FindMinDifference findMinDifference = new FindMinDifference();
//        System.out.println(findMinDifference.findMinDifference(List.of("00:00", "23:00", "00:00")));
        System.out.println(findMinDifference.findMinDifference(List.of("00:00","04:00","22:00")));

//        System.out.println(findMinDifference.convertToMinutes("12:59"));
    }
}
