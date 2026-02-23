package org.leetcode.leetcode75.easy.two.pointers;

public class IsSubsequence {
    //Time complexity: O(n) and Space complexity: O(1). It took 0 ms.
    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()) return true;
        if(t.isEmpty()) return false;
        int index = -1;
        for(char c : s.toCharArray()){
            index = t.indexOf(c, index+1);
            if(index == -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        String s = "abc";
        String t = "ahbgdc";
        boolean ans = isSubsequence.isSubsequence(s, t);
        System.out.println("Is Subsequence: " + ans);
    }
}
