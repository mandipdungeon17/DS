package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/largest-number/?envType=daily-question&envId=2024-09-18
public class LargestNumber {
    //Time complexity: O(nlogn) and Space complexity: O(n). It took 6 ms.
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for(int i : nums) list.add(String.valueOf(i));
        //list.sort((o1, o2) -> Integer.parseInt(o2+o1) - Integer.parseInt(o1+o2));
        list.sort((o1, o2) -> (o2+o1).compareTo(o1+o2));
        if(list.get(0).equals("0")) return "0";
        StringBuilder builder = new StringBuilder();
        for(String s : list){
            builder.append(s);
        }
        return builder.toString();
//        return String.join("", list); // StringBuilder takes less time than String.join
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        int[] nums = {999999991,9};
        String ans = largestNumber.largestNumber(nums);
        System.out.println("Largest Number: " + ans);
    }
}
