package org.leetcode.leetcode75.medium.sliding.window;

public class MaxVowels {
    //Time complexity: O(n) and Space complexity: O(k). It took 30 ms.
//    public int maxVowels(String s, int k) {
//        String vowels = "aeiou";  // Time: O(1), Space: O(1)
//        int count = 0;  // Time: O(1), Space: O(1)
//        int max = 0;  // Time: O(1), Space: O(1)
//        char[] ch = new char[k];  // Time: O(1), Space: O(k)
//        for(int i=0; i<k; i++){  // Time: O(k), Space: O(1)
//            if(vowels.contains(Character.toString(s.charAt(i)))){  // Time: O(1), Space: O(1)
//                count++;  // Time: O(1), Space: O(1)
//            }
//            ch[i] = (s.charAt(i));  // Time: O(1), Space: O(1)
//        }
//        System.out.println(Arrays.toString(ch));  // Time: O(k), Space: O(k)
//        int j=0;  // Time: O(1), Space: O(1)
//        max = Math.max(count, max);  // Time: O(1), Space: O(1)
//        for(int i=k; i<s.length(); i++){  // Time: O(n-k), Space: O(1)
//            char tempCh = ch[j];  // Time: O(1), Space: O(1)
//            ch[j++] = s.charAt(i);  // Time: O(1), Space: O(1)
//            boolean containsString = vowels.contains(Character.toString(s.charAt(i)));  // Time: O(1), Space: O(1)
//            boolean containsArr = vowels.contains(Character.toString(tempCh));  // Time: O(1), Space: O(1)
//            if(containsArr && !containsString){  // Time: O(1), Space: O(1)
//                count--;  // Time: O(1), Space: O(1)
//            }
//            else if(!containsArr && containsString){  // Time: O(1), Space: O(1)
//                count++;  // Time: O(1), Space: O(1)
//            }
//            max = Math.max(count, max);  // Time: O(1), Space: O(1)
//            if(j==k){  // Time: O(1), Space: O(1)
//                j=0;  // Time: O(1), Space: O(1)
//            }
//        }
//        return max;  // Time: O(1), Space: O(1)
//    }

    //Time complexity: O(n) and Space complexity: O(1). It took 13 ms.
    public int maxVowels(String s, int k) {
        int count = 0;  // Time: O(1), Space: O(1)
        int max;  // Time: O(1), Space: O(1)
        for(int i=0; i<k; i++){  // Time: O(k), Space: O(1)
            if(isVowel(s.charAt(i))){  // Time: O(1), Space: O(1)
                count++;  // Time: O(1), Space: O(1)
            }
        }
        max = count;  // Time: O(1), Space: O(1)
        for(int i=k; i<s.length(); i++){  // Time: O(n-k), Space: O(1)
            if(isVowel(s.charAt(i-k))){  // Time: O(1), Space: O(1)
                count--;  // Time: O(1), Space: O(1)
            }
            if(isVowel(s.charAt(i))){  // Time: O(1), Space: O(1)
                count++;  // Time: O(1), Space: O(1)
            }
            max = Math.max(count, max);  // Time: O(1), Space: O(1)
            if(max == k) return max;
        }
        return max;  // Time: O(1), Space: O(1)
    }

    public boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' ||ch == 'i' ||ch == 'o' ||ch == 'u';
    }

    public static void main(String[] args) {
        MaxVowels maxVowels = new MaxVowels();
        String s = "weallloveyou";
        int k = 7;
        int ans = maxVowels.maxVowels(s, k);
        System.out.println("Max Vowels: " + ans);
    }
}
