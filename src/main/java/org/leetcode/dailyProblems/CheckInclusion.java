package org.leetcode.dailyProblems;

import java.util.Arrays;

public class CheckInclusion {
    //Below logic will work for a non-repetitive character
//    public boolean checkInclusion(String s1, String s2) {
//        Map<Character, Integer> map = new HashMap<>();
//        for(char c : s1.toCharArray()){
//            map.merge(c, 1, Integer::sum);
//        }
//
//        for(int i=0; i<s2.length(); i++){
//            if(map.containsKey(s2.charAt(i))){
//                int j=0;
//                while(j<s1.length()){
//                    if(null == map.get(s2.charAt(i)) || map.get(s2.charAt(i)) == 0){
//                        return false;
//                    }
//                    map.replace(s2.charAt(i), map.get(s2.charAt(i))-1);
//                    j++;
//                    i++;
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    //Time Complexity: O(n^2) where n is the length of s2. It took 863 ms to run the code on leetcode.
//    public boolean checkInclusion(String s1, String s2) {
//        char[] charArray = s1.toCharArray();
//        Arrays.sort(charArray);
//        String s3 = new String(charArray);
//        for(int i=0; i<s2.length(); i++){
//            char c = s2.charAt(i);
//            if(s2.contains(Character.toString(c))){
//                if(i+s1.length() > s2.length()) return false;
//                char[] charArray1 = s2.substring(i, i+s1.length()).toCharArray();
//                Arrays.sort(charArray1);
//                String s4 = new String(charArray1);
//                if(s4.equals(s3)) return true;
//            }
//        }
//        return false;
//    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Frequency = new int[26];
        int[] s2Frequency = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Frequency[s1.charAt(i) - 'a']++;
            s2Frequency[s2.charAt(i) - 'a']++;
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            if (Arrays.equals(s1Frequency, s2Frequency)) {
                return true;
            }
            s2Frequency[s2.charAt(i) - 'a']++;
            s2Frequency[s2.charAt(i - s1.length()) - 'a']--;
        }

        return Arrays.equals(s1Frequency, s2Frequency);
    }

    public static void main(String[] args) {
        CheckInclusion checkInclusion = new CheckInclusion();
        String s1 = "abc";
        String s2 = "eibcdcbaoo";
        System.out.println(checkInclusion.checkInclusion(s1, s2));
    }
}
