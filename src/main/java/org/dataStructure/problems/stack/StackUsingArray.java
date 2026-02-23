package org.dataStructure.problems.stack;

public class StackUsingArray {
    int[] arr;
    int top;

    public StackUsingArray(int size){
        this.arr = new int[size];
        this.top = -1;
        System.out.println("The StackUsingArray is created with the size of " + size);
    }

    //isEmpty
    public boolean isEmpty(){
        if(this.top == -1){
            System.out.println("The stack is Empty");
            return false;
        }
        else return true;
    }

    //isFull
    public boolean isFull(){
        if(this.top == this.arr.length-1){
            System.out.println("The stack is Full");
            return false;
        }
        else return true;
    }

    public void push(int value){
        if(isFull()){
            this.arr[++top] = value;
            System.out.println("The value is successfully Inserted");
        }
    }

    public void pop(){
        if(isEmpty()){
            System.out.println("Deleted value is : " + this.arr[top]);
            this.arr[top] = Integer.MIN_VALUE;
            this.top--;
        }
    }

    public void peek(){
        if(isEmpty()) System.out.println("The top value is : " + this.arr[top]);
    }

    public void delete(){
        this.arr = null;
        top = -1;
        System.out.println("The stack is successfully Deleted");
    }

    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.peek();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.peek();
        stack.delete();
        stack.push(1);
    }
}
