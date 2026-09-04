package org.leetcode.leetcode150.two_pointers;

//https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
public class IsSubsequence {

    //Time Complexity O(n) and Space Complexity O(1). It took 2ms.
//    public boolean isSubsequence(String s, String t) {
//        if(s.length() > t.length()) return false;
//        int i=0;
//        int j=0;
//
//        while(i<s.length() && j<t.length()){
//            if(s.charAt(i) != t.charAt(j)){
//                j++;
//            } else {
//                i++;
//                j++;
//            }
//        }
//        return i == s.length();
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public boolean isSubsequence(String s, String t) {
        if(s.length() > t.length()) return false;
        int index = -1;
        for(int i=0; i<s.length(); i++){
            index = t.indexOf(s.charAt(i), index+1);
            if(index == -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.isSubsequence("abc", "ahbgdc"));
    }

}
