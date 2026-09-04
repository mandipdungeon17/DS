package org.leetcode.leetcode150.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-anagrams/?envType=study-plan-v2&envId=top-interview-150
public class GroupAnagrams {

    //Time Complexity O(n*klogk) and Space Complexity O(n*k). It took 6ms.
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            List<String> l;
            if (mp.containsKey(sortedStr)) {
                l = mp.get(sortedStr);
            } else {
                l = new ArrayList<>();
            }
            l.add(str);
            mp.put(sortedStr, l);
        }
        return new ArrayList<>(mp.values());
    }

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        System.out.println(groupAnagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
