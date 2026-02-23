package org.leetcode.leetcode75.easy.array.and.string;

public class MergeAlternately {
    //Time Complexity: O(n^2) & Space Complexity: O(n^2)
    /*
     * Merges two strings alternately into a new string.
     * This implementation uses plain string concatenation, resulting in O(n^2) time and space complexity
     * due to the inefficiency of repeated string concatenation with immutable strings.
     * Each concatenation results in the creation of a new string,
     * leading to quadratic time complexity as explained previously.
     * Each concatenation creates a new string, leading to O(n^2) space complexity for the intermediate strings.
     * However, the final result string itself requires
     * O(n+m) space.
     */
//    public String mergeAlternately(String word1, String word2) {
//        String result="";
//        int index = 0;
//        for(int i=0; i< Math.min(word1.length(), word2.length()); i++){
//            result+=word1.charAt(i)+""+word2.charAt(i);
//            index = i;
//        }
//        if(word1.length() > word2.length()){
//            result+=word1.substring(index+1);
//        }
//        else if(word1.length() < word2.length()){
//            result+=word2.substring(index+1);
//        }
//        return result;
//    }

    //Time Complexity: O(n+m) & Space Complexity: O(n+m)
    /*
     * Merges two strings alternately into a new string using StringBuilder.
     * This implementation is efficient with O(n + m) time and space complexity,
     * as StringBuilder handles string concatenation in amortized constant time.
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result= new StringBuilder();
        int index;
        for(index=0; index< Math.min(word1.length(), word2.length()); index++){
            result.append(word1.charAt(index)).append(word2.charAt(index));
        }
        if(word1.length() > word2.length()){
            result.append(word1.substring(index));
        }
        else if(word1.length() < word2.length()){
            result.append(word2.substring(index));
        }
        return result.toString();
    }



    public static void main(String[] args) {
        MergeAlternately mergeAlternately = new MergeAlternately();
        String word1 = "cdf";
        String word2 = "a";
        System.out.println(mergeAlternately.mergeAlternately(word1, word2));
    }
}
