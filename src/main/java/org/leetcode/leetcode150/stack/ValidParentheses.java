package org.leetcode.leetcode150.stack;

//https://leetcode.com/problems/valid-parentheses/submissions/2080355928/?envType=study-plan-v2&envId=top-interview-150
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    //Time Complexity O(n) and Space Complexity O(n). It took 3ms.
//    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
//        for (char c : s.toCharArray()) {
//            if (c == '(') {
//                stack.push(c);
//            }
//            else if (c == ')') {
//                if (stack.isEmpty() || stack.pop() != '(') {
//                    return false;
//                }
//            }
//            else if (c == '[') {
//                stack.push(c);
//            }
//            else if (c == ']') {
//                if (stack.isEmpty() || stack.pop() != '[') {
//                    return false;
//                }
//            } else if (c == '{') {
//                stack.push(c);
//            }
//            else if (c == '}') {
//                if (stack.isEmpty() || stack.pop() != '{') {
//                    return false;
//                }
//            }
//        }
//        return stack.isEmpty();
//    }

    //Time Complexity O(n) and Space Complexity O(n). It took 4ms.
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid("()[]{}")); // Output: true
        System.out.println(vp.isValid("([)]"));
        System.out.println(vp.isValid("["));
    }
}
