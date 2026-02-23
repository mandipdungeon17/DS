package org.dataStructure.problems.recursion;

public class FirstUpperCase {
    static char first(String str) {
        if(str.isEmpty()) return ' ';
        else if(str.charAt(str.length()-1) >= 'A' && str.charAt(str.length()-1) <= 'Z') return str.charAt(str.length()-1);
        return first(str.substring(0, str.length()-1));
    }

    public static void main(String[] args) {
        System.out.println(first("hello"));
    }
}
