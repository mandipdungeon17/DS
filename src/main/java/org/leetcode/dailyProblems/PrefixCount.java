package org.leetcode.dailyProblems;

//https://leetcode.com/problems/counting-words-with-a-given-prefix/submissions/1502571777/?envType=daily-question&envId=2025-01-09
public class PrefixCount {
    //Time complexity O(n) and space complexity O(1). It took 0ms to execute.
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for(String s : words){
            if(s.startsWith(pref)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new PrefixCount().prefixCount(new String[]{"pay","attention","practice","attend"}, "at"));
    }
}
