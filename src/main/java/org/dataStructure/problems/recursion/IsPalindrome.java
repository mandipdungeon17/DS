package org.dataStructure.problems.recursion;

public class IsPalindrome {
//    public  boolean isPalindrome(String s)
//    {
//        return s.equals(reverse(s));
//    }
//
//    public String reverse(String str){
//        if(str == null || str.length() <=1 ) return str;
//        return str.charAt(str.length()-1) + reverse(str.substring(0, str.length()-1));
//    }

    public boolean isPalindrome(String s){
        if(s == null || s.length() <= 1) return true;
        else if(s.charAt(0) == s.charAt(s.length()-1)){
            return isPalindrome(s.substring(1, s.length()-1));
        }
        return false;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome("madam"));
    }
}
