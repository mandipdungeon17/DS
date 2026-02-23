package org.leetcode.dailyProblems;

//Time Complexity: O(1) and Space Complexity: O(n)
//https://leetcode.com/problems/design-a-stack-with-increment-operation/submissions/1406685822/?envType=daily-question&envId=2024-09-30
public class CustomStack {
    int top;
    int size;
    int[] arr;

    public CustomStack(int maxSize) {
        this.top = -1;
        this.size = maxSize;
        this.arr = new int[size];
    }

    public void push(int x) {
        if(this.size-1 == this.top){
            return;
        }
        this.arr[++this.top] = x;
    }

    public int pop() {
        if(this.top == -1) return -1;
        return this.arr[this.top--];
    }

    public void increment(int k, int val) {
        if(k-1 >= this.top){
            for(int i=0; i<=this.top; i++){
                this.arr[i]+= val;
            }
        }
        else{
            for(int i=0; i<k; i++){
                this.arr[i]+= val;
            }
        }
    }

    public static void main(String[] args) {
        CustomStack stk = new CustomStack(3); // Stack is Empty []
        stk.push(1);                          // stack becomes [1]
        stk.push(2);                          // stack becomes [1, 2]
        System.out.println(stk.pop());                            // return 2 --> Return top of the stack 2, stack becomes [1]
        stk.push(2);                          // stack becomes [1, 2]
        stk.push(3);                          // stack becomes [1, 2, 3]
        stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
        stk.increment(5, 100);                // stack becomes [101, 102, 103]
        stk.increment(2, 100);                // stack becomes [201, 202, 103]
        System.out.println(stk.pop());                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        System.out.println(stk.pop());                            // return 202 --> Return top of the stack 202, stack becomes [201]
        System.out.println(stk.pop());                            // return 201 --> Return top of the stack 201, stack becomes []
        System.out.println(stk.pop());                            // return -1 --> Stack is empty return -1.
    }
}
