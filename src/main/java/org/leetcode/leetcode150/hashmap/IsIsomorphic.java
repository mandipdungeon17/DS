package org.leetcode.leetcode150.hashmap;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150
public class IsIsomorphic {
    //Time Complexity O(n) and Space Complexity O(n). It took 35ms.
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mpS = new HashMap<>();
        Map<Character, Character> mpT = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if((mpS.containsKey(s.charAt(i)) &&
                    mpS.get(s.charAt(i)) != t.charAt(i))
                    || (mpT.containsKey(t.charAt(i)) &&
                    mpT.get(t.charAt(i)) != s.charAt(i))){
                return false;
            } else {
                mpS.put(s.charAt(i), t.charAt(i));
                mpT.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }

    //Time Complexity O(n) and Space Complexity O(1). It took 5ms.
//    public boolean isIsomorphic(String s, String t) {
//        if(s.length()!=t.length()) return false;
//        int[] T=new int[256];
//        int[] S=new int[256];
//        for(int i=0;i<s.length();i++){
//            char c=t.charAt(i);
//            char c1=s.charAt(i);
//            if(T[c]!=S[c1]) return false;
//            T[c]=i+1;
//            S[c1]=i+1;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        IsIsomorphic isIsomorphic = new IsIsomorphic();
        System.out.println(isIsomorphic.isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic.isIsomorphic("f11", "b23"));
    }
}
