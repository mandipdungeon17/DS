package org.leetcode.dailyProblems;
//https://leetcode.com/problems/string-compression-iii/description/?envType=daily-question&envId=2024-11-04
public class CompressedString {
    //Time complexity: O(n) and Space complexity: O(n). It took 23 ms to execute.
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for(int i=1; i<word.length(); i++){
            if(word.charAt(i) == word.charAt(i-1)){
                if(count == 9){
                    sb.append(count).append(word.charAt(i-1));
                    count = 0;
                }
                count++;
            }
            else{
                sb.append(count).append(word.charAt(i-1));
                count = 1;
            }
        }
        sb.append(count).append(word.charAt(word.length()-1));
        return sb.toString();
    }

    public static void main(String[] args) {
        CompressedString compressedString = new CompressedString();
        String result = compressedString.compressedString("aaaaaaaaaaaaaabb");
        System.out.println(result);
    }
}
