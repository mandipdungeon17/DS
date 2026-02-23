package org.dataStructure.problems.stack;

public class StackUsingLL {
    int size;
    StackNode head;
    StackNode tail;

    public void createLL(int data){
        StackNode node = new StackNode(data);
        node.next = null;
        this.head = node;
        this.tail = node;
        this.size = 0;
    }

    public boolean isEmpty(){
        return !(this.head == null);
    }

    public void push(int data){
        if(isEmpty()) {
            StackNode node = new StackNode(data);
            node.next = this.head;
            this.head = node;
            size++;
            System.out.println("The value is inserted successfully : " + data);
        }
        else{
            createLL(data);
            System.out.println("The value is inserted successfully : " + data);
        }
    }

    public void pop(){
        if(size == 0){
            System.out.println("The last deleted node is : " + this.head.data);
            deleteStack();
            size--;
        }
        else if(isEmpty()) {
            System.out.println("The deleted node is : " + this.head.data);
            this.head = this.head.next;
            size--;
        }
        else{
            System.out.println("The stack is Empty");
        }
    }

    public void peek(){
        System.out.println("The last node value is : " + this.head.data);
    }

    public void deleteStack(){
        this.head = null;
        System.out.println("The stack is null");
    }

    public static void main(String[] args) {
        StackUsingLL stack = new StackUsingLL();
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
//        stack.peek();
        stack.deleteStack();
        stack.push(1);
    }
}
