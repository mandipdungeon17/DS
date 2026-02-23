package org.leetcode.leetcode75.medium.sliding.window;

/*
Given a string and an integer K, return the length of the longest substring
with at most K distinct characters.
Input: s = "eceba", k = 2
Output: 3
*/

public class LongestSubstring {

    static String longestSubstring(String s){
        StringBuilder subArray = new StringBuilder();
        String result = "";
        int j;
        for(int i=0; i<s.length(); i++){
            if(subArray.toString().contains(Character.toString(s.charAt(i)))){
                j = subArray.indexOf(Character.toString(s.charAt(i))) + 1;
                subArray = new StringBuilder(subArray.substring(j));
            }
            subArray.append(s.charAt(i));
            if(result.length() < subArray.length()){
                result = subArray.toString();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("pwwkew")); //ceba

    }

}
