package org.leetcode.leetcode150.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/evaluate-reverse-polish-notation/?envType=study-plan-v2&envId=top-interview-150
public class ReversePolishNotation {

    //Time Complexity O(n) and Space Complexity O(n). It took 7ms.
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        Set<String> set = Set.of("+", "-", "*", "/");
        for(String s : tokens){
            if(set.contains(s)){
                int a = st.pop();
                int b = st.pop();
                switch(s){
                    case "+": st.push(b+a); break;
                    case "/": st.push(b/a); break;
                    case "*": st.push(b*a); break;
                    case "-": st.push(b-a); break;
                }
            } else {
                st.push(Integer.parseInt(s));
            }
        }
        return st.pop();
    }

    public static void main(String[] args) {
        ReversePolishNotation rpn = new ReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(rpn.evalRPN(tokens)); // Output: 9
    }
}
