package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://leetcode.com/problems/uncommon-words-from-two-sentences/?envType=daily-question&envId=2024-09-17
public class UncommonFromSentences {
    //Time complexity: O(n) and Space complexity: O(n). It took 3 ms.
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        String[] list1 = s1.split(" ");
        String[] list2 = s2.split(" ");

        for(String s : list1){
            stringIntegerMap.merge(s, 1, Integer::sum);
        }
        for(String s : list2){
            stringIntegerMap.merge(s, 1, Integer::sum);
        }
        List<String> builder = new ArrayList<>();
        for(String s : stringIntegerMap.keySet()){
            if(stringIntegerMap.get(s) == 1){
                builder.add(s);
            }
        }
        if(builder.isEmpty()) return new String[0];
       return builder.toArray(new String[0]);
    }

    public static void main(String[] args) {
        UncommonFromSentences uncommonFromSentences = new UncommonFromSentences();
        String s1 = "this apple is sweet";
        String s2 = "this apple is sour";
        String[] ans = uncommonFromSentences.uncommonFromSentences(s1, s2);
        System.out.println("Uncommon From Sentences: " + Arrays.toString(ans));
    }
}
