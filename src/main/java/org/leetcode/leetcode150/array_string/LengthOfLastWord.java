package org.leetcode.leetcode150.array_string;
//https://leetcode.com/problems/length-of-last-word/submissions/2068716345/?envType=study-plan-v2&envId=top-interview-150
public class LengthOfLastWord {

    //Time complexity O(n) and space complexity O(n). It took 1ms.
//    public int lengthOfLastWord(String s) {
//        s = s.trim();
//        String[] words = s.split(" ");
//        System.out.println(Arrays.toString(words));
//        return words[words.length-1].length();
//    }

    //Time complexity O(n) and space complexity O(1). It took 0ms.
    public int lengthOfLastWord(String s) {
        int count=0;
        for(int i=s.length()-1; i>=0; i--){
            if(s.charAt(i)==' ' && count==0){
                continue;
            } else if(s.charAt(i)==' ' && count>0){
                return count;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        LengthOfLastWord l = new LengthOfLastWord();
        System.out.println(l.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(l.lengthOfLastWord("Hello World"));
    }
}
