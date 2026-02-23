package org.dataStructure.problems.queue.queueUsingArray;

public class CircularQueue {
    int[] arr;
    int front;
    int end;
    
    public CircularQueue(int size){
        this.arr = new int[size];
        this.front = this.end = -1;
    }
    
    public boolean isEmpty(){
        return this.end == -1 && this.front == -1;
    }
    
    public boolean isFull(){
        return (this.arr.length - 1 == this.front && this.end == 0) || (this.front == this.end - 1);
    }
    
    public void enQueue(int value){
        if(!isFull()){
            if(isEmpty()) this.end++;
            else if(this.front == this.arr.length -1) this.front = -1;
            this.arr[++this.front] = value;
            System.out.println("The value is successfully Inserted : " + value);
        }
        else System.out.println("The Queue is Full");
    }
    
    public void deQueue(){
        if(!isEmpty()){
            System.out.println("The value is deleted : " + this.arr[this.end]);
            this.arr[this.end] = Integer.MIN_VALUE;
            if(this.end == this.front) this.end = this.front = -1;
            else if(this.end == this.arr.length -1) this.end = 0;
            else this.end++;

        }
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
        CircularQueue queue = new CircularQueue(3);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(1);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(2);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
//        queue.peek();
//        queue.deQueue();
//        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(3);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(4);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.deQueue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.deQueue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.enQueue(10);
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.deQueue();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.peek();
        queue.deQueue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(4);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.enQueue(5);
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.deQueue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.deQueue();
        queue.peek();
        System.out.println("The value of front and end are : " + queue.front + " and " + queue.end);
        queue.delete();
//        queue.enQueue(5);
    }
}
