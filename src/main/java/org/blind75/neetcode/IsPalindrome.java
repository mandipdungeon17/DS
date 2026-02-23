package org.blind75.neetcode;

/*
A palindrome is a string that reads the same forward and backward.
It is also case-insensitive and ignores all non-alphanumeric characters.
*/
public class IsPalindrome {
    public static boolean isPalindrome(String s) {
        StringBuilder str= new StringBuilder();
        for(char c : s.toCharArray()){
            if(Character.toString(c).matches("[a-zA-Z0-9]*$"))
                str.append(Character.toString(c).toLowerCase());
        }
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) != str.charAt(str.length() - 1 -i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Was it a car or a cat I saw?"));
    }
}
