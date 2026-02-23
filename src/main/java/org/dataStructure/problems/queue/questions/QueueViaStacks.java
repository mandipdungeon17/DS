package org.dataStructure.problems.queue.questions;

public class QueueViaStacks {
    int size;
    Stack1 stack1;
    Stack2 stack2;
    public QueueViaStacks(int size){
        this.size = size;
        stack1 = new Stack1(this.size);
        stack2 = new Stack2(this.size);
    }

    public void enQueue(int data){
        stack1.push(data);
        System.out.println("The data is successfully inserted in the queue : " + data);
    }
    public void deQueue(){
        shiftStack();
        int value = stack2.pop();
        System.out.println("The data is successfully deleted from the queue : " + value);
    }

    private void shiftStack() {
        int value;
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                value = stack1.pop();
                stack2.push(value);
            }
        }
    }

    public void peek(){
        shiftStack();
        System.out.println("The first element in the queue is : " + stack2.arr[stack2.top]);
    }

    public static void main(String[] args) {
        QueueViaStacks queueViaStacks = new QueueViaStacks(5);
        queueViaStacks.enQueue(1);
        queueViaStacks.enQueue(2);
        queueViaStacks.peek();
        queueViaStacks.enQueue(3);
        queueViaStacks.enQueue(4);
        queueViaStacks.peek();
        queueViaStacks.deQueue();
        queueViaStacks.peek();
        queueViaStacks.deQueue();
        queueViaStacks.peek();
        queueViaStacks.enQueue(5);
        queueViaStacks.peek();
        queueViaStacks.deQueue();
        queueViaStacks.peek();
    }
}

class Stack1{
    int[] arr;
    int top;
    public Stack1(int size){
        this.arr = new int[size];
        this.top = -1;
    }
    public boolean isFull(){
        return this.top == this.arr.length;
    }

    public boolean isEmpty(){
        return this.top == -1;
    }
    public void push(int data){
        if(!isFull()) this.arr[++top] = data;
        else System.out.println("The Stack1 is Full");
    }
    public int pop(){
        if(!isEmpty()) return this.arr[top--];
        else return -1;
    }
}
class Stack2{
    int[] arr;
    int top;
    public Stack2(int size){
        this.arr = new int[size];
        this.top = -1;
    }
    public boolean isFull(){
        return this.top == this.arr.length;
    }

    public boolean isEmpty(){
        return this.top == -1;
    }
    public void push(int data){
        if(!isFull()) this.arr[++top] = data;
        else System.out.println("The Stack1 is Full");
    }
    public int pop(){
        if(!isEmpty()) return this.arr[top--];
        else return -1;
    }
}
