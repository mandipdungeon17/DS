package org.leetcode.leetcode150.hashmap;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    //Time Complexity O(n) and Space Complexity O(n). It took 1ms.
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> mp = new HashMap<>();
        Map<String, Character> mps = new HashMap<>();
        String[] s1 = s.split(" ");

        if(pattern.length() != s1.length) return false;
        for(int i=0; i<s1.length; i++){
            if((mps.containsKey(s1[i]) && mps.get(s1[i]) != pattern.charAt(i))
                    || (mp.containsKey(pattern.charAt(i)) && !mp.get(pattern.charAt(i)).equals(s1[i]))){
                return false;
            }
            mp.put(pattern.charAt(i), s1[i]);
            mps.put(s1[i], pattern.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        System.out.println(wp.wordPattern("abba", "dog cat cat dog"));
        System.out.println(wp.wordPattern("aaa", "aa aa aa aa"));
    }
}
