package org.dataStructure.problems.recursion;

public class ReverseString {
    public String reverse(String str)
    {
        if(str == null || str.length() <= 1) return str;
        return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() -1));
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        System.out.println(reverseString.reverse("hello"));
    }
}
