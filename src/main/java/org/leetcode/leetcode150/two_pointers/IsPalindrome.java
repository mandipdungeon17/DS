package org.leetcode.leetcode150.two_pointers;

//https://leetcode.com/problems/valid-palindrome/submissions/2074066450/?envType=study-plan-v2&envId=top-interview-150
public class IsPalindrome {
    //Time Complexity O(n) and Space Complexity O(n). It took 84ms.
//    public boolean isPalindrome(String s) {
//        StringBuilder sb = new StringBuilder();
//        for(Character c: s.toCharArray()){
//            String s1 = c.toString();
//            if(c.toString().matches("[a-zA-Z0-9]"))
//                sb.append(s1.toLowerCase());
//        }
//        int i =0;
//        int j = sb.length()-1;
//        while (i< j){
//            if(sb.charAt(i) != sb.charAt(j)) return false;
//            i++;
//            j--;
//        }
//        return true;
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 2ms.
    public boolean isPalindrome(String s) {
        int i =0;
        int j = s.length()-1;
        while (i<j){
            if(!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            } else if(!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
