package org.leetcode.leetcode150.array_string;

//https://leetcode.com/problems/reverse-words-in-a-string/submissions/2069750814/?envType=study-plan-v2&envId=top-interview-150
public class ReverseWords {

    //Time Complexity O(n) and Space Complexity O(n). It took 5ms.
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] s1 = s.split(" ");
        res.append(s1[s1.length-1]).append(" ");
        for(int i=s1.length-2; i>=0; i--){
            if(s1[i].isEmpty()) continue;
            res.append(s1[i]).append(" ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        ReverseWords rw = new ReverseWords();
        System.out.println(rw.reverseWords("a good   example"));
    }
}
