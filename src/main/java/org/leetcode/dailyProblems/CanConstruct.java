package org.leetcode.dailyProblems;

//https://leetcode.com/problems/construct-k-palindrome-strings/submissions/1509435068/?envType=daily-question&envId=2025-01-11
public class CanConstruct {

    //Time complexity O(n) and space complexity O(1). It took 4ms to execute.
    public boolean canConstruct(String s, int k) {
        if(s.length() < k) return false;
        if(s.length() == k) return true;
        int[] freq = new int[26];
        int oddCount = 0;
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        for(int i : freq){
            if(i%2 != 0) oddCount++;
        }
        return oddCount <= k;
    }

    public static void main(String[] args) {
        CanConstruct canConstruct = new CanConstruct();
        String s = "leetcode";
        int k = 3;
        System.out.println(canConstruct.canConstruct(s, k));
    }
}
