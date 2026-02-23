package org.leetcode.leetcode75.medium.stack;

public class RemoveStars {
    //Time complexity: O(n) and Space complexity: O(n). It took 60 ms.
//    public String removeStars(String s) {
//        int top = 0;
//        Stack<Character> characters = new Stack<>();
//        for(char c : s.toCharArray()){
//            if(c == '*' && top >0){
//                characters.pop();
//                top--;
//                continue;
//            }
//            characters.push(c);
//            top++;
//        }
//        System.out.println("Top: " + characters);
//        StringBuilder builder = new StringBuilder();
//        for(char c : characters){
//            builder.append(c);
//
//        }
//        return builder.toString();
//    }

    //Time complexity: O(n) and Space complexity: O(n) -> later modified to O(1). It took 12 ms.
    public String removeStars(String s) {
//        char[] ch = new char[s.length()];
        char[] ch = s.toCharArray();
        int top=-1;
//        for(char c : s.toCharArray()){
        for(char c : ch){
            if(c == '*' && top >=0){
                top--;
            }
            else{
                ch[++top] = c;
            }
        }
//        System.out.println(String.valueOf(ch).substring(0, top+1));
//        return new String(ch).substring(0, top+1);
        return new String(ch, 0, top+1);
    }

    public static void main(String[] args) {
        RemoveStars removeStars = new RemoveStars();
        //String s = "erase*****";
        String s = "leet**cod*e";
        String ans = removeStars.removeStars(s);
        System.out.println("Remove Stars: " + ans);
    }
}
