package org.leetcode.dailyProblems;

public class MakeFancyString {
    public String makeFancyString(String s) {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length()-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                count++;
                if(count == 3){
                    count--;
                }
                else{
                    sb.append(s.charAt(i));
                }
            }
            else{
                count = 1;
                sb.append(s.charAt(i));
            }
        }
        if(count != 3) sb.append(s.charAt(s.length()-1));
        return sb.toString();
    }

    public static void main(String[] args) {
        MakeFancyString makeFancyString = new MakeFancyString();
        String result = makeFancyString.makeFancyString("aaabaaaa");
        System.out.println(result);
    }
}
