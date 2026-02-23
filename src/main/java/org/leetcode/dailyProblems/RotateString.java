package org.leetcode.dailyProblems;

//https://leetcode.com/problems/rotate-string/?envType=daily-question&envId=2024-11-03
public class RotateString {
//    public boolean rotateString(String s, String goal) {
//        String str;
//        for(int i=0; i<goal.length(); i++){
//            str = s.substring(i) + s.substring(0, i);
//            if(str.equals(goal)) return true;
//        }
//        return false;
//    }

    //Time complexity: O(n) and Space complexity: O(n). It took 0 ms to execute.
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        String combined = s + s;
        return combined.contains(goal);
    }

    public static void main(String[] args) {
        RotateString rotateString = new RotateString();
        boolean result = rotateString.rotateString("abcde", "cdeab");
        System.out.println(result);
    }
}
