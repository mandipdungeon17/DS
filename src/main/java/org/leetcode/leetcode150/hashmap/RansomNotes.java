package org.leetcode.leetcode150.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150
public class RansomNotes {
    //Time Complexity O(n) and Space Complexity O(n). It took 14ms.
//    public boolean canConstruct(String ransomNote, String magazine) {
//        Map<Character, Integer> mp = new HashMap<>();
//        for(char c : magazine.toCharArray()){
//            mp.put(c, mp.getOrDefault(c, 0)+1);
//        }
//        for(char c : ransomNote.toCharArray()){
//            if(mp.get(c) == null) return false;
//            else{
//                int count = mp.get(c);
//                if(count-1 == 0) mp.remove(c);
//                else mp.put(c, count-1);
//            }
//        }
//        return true;
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 1ms.
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if(--count[c - 'a'] < 0)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        RansomNotes ransomNotes = new RansomNotes();
        System.out.println(ransomNotes.canConstruct("a", "b"));
        System.out.println(ransomNotes.canConstruct("aa", "ab"));
        System.out.println(ransomNotes.canConstruct("aa", "aab"));
    }
}
