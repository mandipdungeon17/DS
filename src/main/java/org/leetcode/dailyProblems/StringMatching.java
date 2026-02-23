package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://leetcode.com/problems/string-matching-in-an-array/?envType=daily-question&envId=2025-01-07
public class StringMatching {

    //Time complexity O(n^2) and space complexity O(n). It took 14ms to execute.
    public static List<String> stringMatching(String[] words) {
//        List<String> res = new ArrayList<>();
//        for(String s : words){
//            for(String s1 : words){
//                if(!s1.equals(s) && !res.contains(s) && s1.contains(s)){
//                    res.add(s);
//                    break;
//                }
//            }
//        }
//        return res;
        List<String> res = new ArrayList<>();
//        Arrays.sort(words, Comparator.comparingInt(String::length));
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(stringMatching(new String[]{"mass","as","hero","superhero"}));
    }
}
