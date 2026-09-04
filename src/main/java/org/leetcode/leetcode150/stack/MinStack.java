package org.leetcode.leetcode150.stack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/min-stack/?envType=study-plan-v2&envId=top-interview-150
//Time Complexity O(1) and Space Complexity O(n). It took 29ms.
public class MinStack {
    private final List<Integer> list;
    private final List<Integer> minList;
    int min = Integer.MAX_VALUE;
    public MinStack() {
        this.list = new ArrayList<>();
        this.minList = new ArrayList<>();
    }

    public void push(int value) {
        list.add(value);
        min = Math.min(value, min);
        if(min == value)
            minList.add(min);
    }

    public void pop() {
        if(list.getLast() == min){
            minList.removeLast();
            min = minList.isEmpty() ? Integer.MAX_VALUE : minList.getLast();
        }
        list.removeLast();
    }

    public int top() {
        return list.getLast();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
