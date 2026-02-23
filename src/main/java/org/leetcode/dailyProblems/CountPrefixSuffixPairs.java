package org.leetcode.dailyProblems;

//https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/submissions/1502589732/?envType=daily-question&envId=2025-01-08
public class CountPrefixSuffixPairs {
    //Time complexity O(n^2) and space complexity O(1). It took 0ms to execute.
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
//        Arrays.sort(words, Comparator.comparingInt(String::length));
        for(int i=0; i<words.length; i++){
            for(int j=i+1; j< words.length; j++){
                if(words[j].startsWith(words[i]) && words[j].endsWith(words[i])){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountPrefixSuffixPairs countPrefixSuffixPairs = new CountPrefixSuffixPairs();
        String[] words = {"a","aba","ababa","aa"};
        System.out.println(countPrefixSuffixPairs.countPrefixSuffixPairs(words));
        words = new String[]{"pa","papa","ma","mama"};
        System.out.println(countPrefixSuffixPairs.countPrefixSuffixPairs(words));
        words = new String[]{"abab","ab"};
        System.out.println(countPrefixSuffixPairs.countPrefixSuffixPairs(words));
    }
}
