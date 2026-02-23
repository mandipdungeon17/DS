package org.leetcode.dailyProblems;

import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/count-vowel-strings-in-ranges/submissions/1495341074/?envType=daily-question&envId=2025-01-02
public class VowelStrings {

    //Time complexity O(n) and space complexity O(n). It took 10ms to execute.
    // The approach is to create a prefix sum array of the words array and then for each query, we can get the result in O(1) time.
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] res = new int[queries.length];
        List<Character> vowel = List.of('a', 'e', 'i', 'o', 'u');
        int[] prefixSum = new int[words.length + 1];

        for (int i = 0; i < words.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + (vowel.contains(words[i].charAt(0)) && vowel.contains(words[i].charAt(words[i].length() - 1)) ? 1 : 0);
        }

        for (int i = 0; i < queries.length; i++) {
            res[i] = prefixSum[queries[i][1] + 1] - prefixSum[queries[i][0]];
        }

        return res;
    }

    public static void main(String[] args) {
        VowelStrings vowelStrings = new VowelStrings();
//        String[] words = new String[]{"aba","bcb","ece","aa","e"};
//        int[][] queries = new int[][]{{0,2},{1,4},{1,1}};
        String[] words = new String[]{"a","e","i"};
        int[][] queries = new int[][]{{0,2},{0,1},{2,2}};
        System.out.println(Arrays.toString(vowelStrings.vowelStrings(words, queries)));
    }
}
