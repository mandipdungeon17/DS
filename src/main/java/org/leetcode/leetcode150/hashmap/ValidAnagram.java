package org.leetcode.leetcode150.hashmap;

public class ValidAnagram {
    //Time Complexity O(n) and Space Complexity O(1). It took 3ms.
    public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for(char c : s.toCharArray()){
            arr[c-'a']++;
        }
        for(char c : t.toCharArray()){
            arr[c-'a']--;
            if(arr[c-'a'] < 0) return false;
        }
        for(int a : arr){
            if(a > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.isAnagram("anagram", "nagaram"));
    }
}
