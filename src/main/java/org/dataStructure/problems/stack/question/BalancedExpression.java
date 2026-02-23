package org.dataStructure.problems.stack.question;

import java.util.Stack;

public class BalancedExpression {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        System.out.println(expression);

        for(int i=0; i< expression.length(); i++){
            char c = expression.charAt(i);

            if(c == '{' || c == '[' || c == '('){
                stack.push(c);
            }
            else if(c == '}' || c == ']' || c == ')'){
                if(stack.isEmpty()) return false;

                char top = stack.pop();

                if((c == '}' && top != '{') || (c == ']' && top != '[') || (c == ')' && top != '(')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]}"));
    }

}
