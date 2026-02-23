package org.leetcode.dailyProblems;

//https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/?envType=daily-question&envId=2024-11-05
public class MinChanges {
    //Time complexity: O(n) and Space complexity: O(1). It took 3 ms to execute.
    public int minChanges(String s) {
        int count = 0;
        for(int i=1; i<s.length(); i=i+2){
            if(s.charAt(i) != s.charAt(i-1)){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MinChanges minChanges = new MinChanges();
        int result = minChanges.minChanges("0100");
        System.out.println(result);
    }
}
