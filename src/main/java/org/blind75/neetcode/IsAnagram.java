package org.blind75.neetcode;

import javax.xml.stream.events.Characters;
import java.util.HashMap;
import java.util.Map;

public class IsAnagram {
//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) return false;
//        Map<Character, Integer> map = new HashMap<>();
//        for(char c : s.toCharArray()){
//            map.put(c, map.getOrDefault(c, 0)+1);
//        }
//        System.out.println(map);
//
//        for(char c : t.toCharArray()){
//            if(null == map.get(c)) return false;
//            else if(map.get(c) == 1) map.remove(c);
//            else {
//                map.put(c, map.get(c) - 1);
//                if(map.get(c) == 0) map.remove(c);
//            }
//        }
//        return map.isEmpty();
//    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr = new int[26];
        for(int i =0; i<s.length(); i++){
            arr[s.charAt(i) - 'a']+=1;
            arr[t.charAt(i) - 'a']-=1;
        }

        for(int i : arr){
            if(i !=0) return false;
        }
        return true;
    }
}
