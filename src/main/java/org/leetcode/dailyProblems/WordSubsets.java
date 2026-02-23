package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-subsets/submissions/1504974526/?envType=daily-question&envId=2025-01-10
public class WordSubsets {

    //Time complexity O(n*m) and space complexity O(1). It took 14ms to execute.
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> list = new ArrayList<>();
        int[] chars = new int[26];
        for(String word2 : words2){
            int[] newChar = count(word2);
            for(int i=0; i<26; i++){
                chars[i] = Math.max(newChar[i], chars[i]);
            }
        }

        for(String word1 : words1){
            boolean flag = true;
            int[] words = count(word1);
            for(int i=0; i<26; i++){
                if (words[i] < chars[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) list.add(word1);
        }
        return list;
    }

    private int[] count(String s){
        int[] alpha = new int[26];
        for(char c : s.toCharArray()){
            alpha[c - 'a']++;
        }
        return alpha;
    }

    public static void main(String[] args) {
        WordSubsets wordSubsets = new WordSubsets();
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"e","o"};
        System.out.println(wordSubsets.wordSubsets(words1, words2));
        words1 = new String[]{"amazon","apple","facebook","google","leetcode"};
        words2 = new String[]{"l","e"};
        System.out.println(wordSubsets.wordSubsets(words1, words2));
        words1 = new String[]{"amazon","apple","facebook","google","leetcode"};
        words2 = new String[]{"e","oo"};
        System.out.println(wordSubsets.wordSubsets(words1, words2));
        words1 = new String[]{"amazon","apple","facebook","google","leetcode"};
        words2 = new String[]{"lo","eo"};
        System.out.println(wordSubsets.wordSubsets(words1, words2));
        words1 = new String[]{"amazon","apple","facebook","google","leetcode"};
        words2 = new String[]{"ec","oc","ceo"};
        System.out.println(wordSubsets.wordSubsets(words1, words2));
    }
}
