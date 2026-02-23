package org.leetcode.leetcode75.medium.hash.map.set;
/*
* Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

Example 1:

Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:

Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:

Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"

Constraints:

1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : (word1+word2).toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        System.out.println("List: " + list);
        return !list.contains(1);
    }

    public static void main(String[] args) {
        CloseStrings closeStrings = new CloseStrings();
        String word1 = "cabbba"; // "aabbbc"
        String word2 = "abbccc"; // "abbccc"
        boolean ans = closeStrings.closeStrings(word1, word2);
        System.out.println("Close Strings: " + ans);
    }
}
