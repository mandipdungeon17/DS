package org.dataStructure.problems.stack.question;

public class StackMin {
    Node top;
    Node min;

    public StackMin(){
        this.top = null;
        this.min = null;
    }
    public void min(){
        if(this.min != null) System.out.println("The min value is : " + min.data);
        else System.out.println("The stack is Empty");
    }

    public void push(int data){
        if(min == null) this.min = new Node(data, null);
        else if(min.data < data) this.min = new Node(min.data, min);
        else this.min = new Node(data, min);

        this.top = new Node(data, top);
    }

    public void pop(){
        this.min = this.min.next;
        System.out.println("The deleted value is : " + this.top.data);
        this.top = this.top.next;
    }

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();
        stackMin.min();
        stackMin.push(5);
        stackMin.min();
        stackMin.push(4);
        stackMin.min();
        stackMin.push(7);
        stackMin.min();
        stackMin.push(1);
        stackMin.min();
        stackMin.pop();
        stackMin.min();
        stackMin.pop();
        stackMin.min();
        stackMin.push(7);
        stackMin.min();
        stackMin.pop();
        stackMin.min();
        stackMin.pop();
        stackMin.min();
        stackMin.pop();
        stackMin.min();
    }
}

class Node{
    int data;
    Node next;

    public Node(int data, Node next){
        this.data = data;
        this.next = next;
    }
}
