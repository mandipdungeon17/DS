package org.leetcode.leetcode150.sliding_window;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-interview-150
public class LengthOfLongestSubstring {

    //Time Complexity O(n) and Space Complexity O(n). It took 6ms.
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        int j=0;
        int max=0;
        for(int i=0; i<s.length(); i++){
            if(mp.get(s.charAt(i)) != null){
                max = Math.max(max,mp.size());
                while(mp.get(s.charAt(i)) > j){
                    mp.remove(s.charAt(j));
                    j++;
                }
                j++;
            }
            mp.put(s.charAt(i), i);
        }
        max = Math.max(max, mp.size());
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew"));
    }
}
