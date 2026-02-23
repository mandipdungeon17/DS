package org.leetcode.dailyProblems;

public class IsCircularSentence {

    //Time complexity: O(n) where n is the size of the array and space complexity: O(n). It took 2 ms.
//    public boolean isCircularSentence(String sentence) {
//        List<String> list = List.of(sentence.split(" "));
//        String firstString = list.get(0);
//        String lastString = list.get(list.size()-1);
//        if(firstString.charAt(0) == lastString.charAt(lastString.length()-1)){
//            for(int i=1; i<list.size(); i++){
//                String str1 = list.get(i-1);
//                String str2 = list.get(i);
//                if(str2.charAt(0) != str1.charAt(str1.length()-1)) return false;
//            }
//            return true;
//        }
//        return false;
//    }
    //Time complexity: O(n) where n is the size of the array and space complexity: O(n). It took 1 ms
    public boolean isCircularSentence(String sentence) {
        String[] list = sentence.split(" ");
        String firstString = list[0];
        String lastString = list[list.length-1];
        if(firstString.charAt(0) == lastString.charAt(lastString.length()-1)){
            for(int i=1; i<list.length; i++){
                String str1 = list[i-1];
                String str2 = list[i];
                if(str2.charAt(0) != str1.charAt(str1.length()-1)) return false;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        IsCircularSentence isCircularSentence = new IsCircularSentence();
        boolean isCircularSentenceCircularSentence = isCircularSentence.isCircularSentence("leetcode exercises sound delightful");
        System.out.println(isCircularSentenceCircularSentence);
    }
}
