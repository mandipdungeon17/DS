package org.leetcode.leetcode75.easy.hash.map.set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrences {
    //Time complexity: O(n) and Space complexity: O(n)
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0) +1);
        }
        Set<Integer> set = new HashSet<>(map.values());
        return map.values().size() == set.size();
    }

    public static void main(String[] args) {
        UniqueOccurrences uniqueOccurrences = new UniqueOccurrences();
        boolean ans = uniqueOccurrences.uniqueOccurrences(new int[]{1,2,2,1,1,3});
        System.out.println("Unique Occurrences: " + ans);
    }
}
