package org.dataStructure.problems.queue.queueUsingArray;

public class LinearQueue {
    int[] arr;
    int front;
    int end;

    public LinearQueue(int size){
        this.arr = new int[size];
        this.front = -1;
        this.end = -1;
    }

    public boolean isEmpty(){
        return this.end == this.arr.length || this.end == -1;
    }

    public boolean isFull(){
        return this.front == this.arr.length-1;
    }

    public void enqueue(int value){
        if(!isFull()){
            if(isEmpty()) this.end++;
            this.arr[++front] = value;
            System.out.println("The value is successfully Inserted : " + value);
        }
        else System.out.println("The Queue is Full");
    }

    public void dequeue(){
        if(!isEmpty()){
            System.out.println("The value is deleted : " + this.arr[this.end]);
            this.arr[this.end] = Integer.MIN_VALUE;
            this.end++;
            if(this.end > this.front) this.end = this.front = -1;
        }
        else System.out.println("The Queue is Empty");
    }

    public void peek(){
        if(isEmpty()){
            System.out.println("The Queue is Empty");
            return;
        }
        System.out.println("The first and the last value in the queue is : " + this.arr[this.end] +" and : " + this.arr[this.front] + " respectively");
    }

    public void delete(){
        this.arr = null;
        this.front = this.end = -1;
        System.out.println("The Queue is deleted successfully");
    }

    public static void main(String[] args) {
        LinearQueue queue = new LinearQueue(3);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(1);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(2);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(3);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(4);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.dequeue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.dequeue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.dequeue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.dequeue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(4);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enqueue(5);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.dequeue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.dequeue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.delete();
        queue.enqueue(5);
    }
}
