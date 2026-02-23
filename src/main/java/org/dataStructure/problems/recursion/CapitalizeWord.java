package org.dataStructure.problems.recursion;

public class CapitalizeWord {
    public static String capitalizeWord(String str){
        if(str.isEmpty()) return str;
        return capitalizeWord(str.substring(0, str.length()-1)) + (str.charAt(str.length()-1)  == ' '? str.charAt(str.length()-1) : (char)(str.charAt(str.length()-1) - 32));
    }

    public static String capitalizeCamelCase(String str){
        if(str.isEmpty()) return str;
        char chr = str.charAt(str.length()-1);
        if(str.length() == 1) chr = Character.toUpperCase(chr);
        else if(str.charAt(str.length() - 2) == ' ') chr = Character.toUpperCase(chr);
        return capitalizeCamelCase(str.substring(0, str.length()-1)) + chr;
    }

    public static void main(String[] args) {
//        System.out.println(capitalizeWord("i love java"));
        System.out.println(capitalizeCamelCase("i love java"));
    }
}
