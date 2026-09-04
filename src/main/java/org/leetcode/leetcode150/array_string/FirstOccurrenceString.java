package org.leetcode.leetcode150.array_string;

//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150
public class FirstOccurrenceString {

    //Time Complexity O(n * m) and Space Complexity O(1). It took 0ms.
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        FirstOccurrenceString f = new FirstOccurrenceString();
        System.out.println(f.strStr("sadbutsad", "sad"));
        System.out.println(f.strStr("leetcode", "leeto"));
    }
}
