package org.leetcode.leetcode75.medium.array.and.string;

public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        String[] s1 = s.split(" ");
        for(int i=s1.length-1; i>=0; i--){
            if(!s1[i].isEmpty()){
                builder.append(s1[i]);
                builder.append(" ");
            }
        }
        return builder.substring(0, builder.length()-1);
    }

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        String s = "  hello world  ";
        System.out.println(reverseWords.reverseWords(s));
    }
}
