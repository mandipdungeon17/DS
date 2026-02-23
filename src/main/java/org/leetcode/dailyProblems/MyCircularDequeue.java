package org.leetcode.dailyProblems;

class MyCircularDeque {
    int[] queue;
    int front;
    int rear;

    public MyCircularDeque(int k) {
        this.queue = new int[k];
        this.front = -1;
        this.rear = -1;
    }

    public boolean insertFront(int value) {
        if(isFull()) return false;
        if(front == -1 && rear == -1){
            rear=0;
        }
        else if(front == queue.length-1) front =-1;
        queue[++front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()) return false;
        if (rear-1 == -1) {
            rear = queue.length;
        }
        else if(front == -1 && rear == -1){
            front=0;
            rear=1;
        }
        queue[--rear] = value;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()) return false;
        if(front == rear){
            front = -1;
            rear = -1;
        }
        else if(front == 0) front = queue.length-1;
        else front--;
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) return false;
        if(front == rear){
            front = -1;
            rear = -1;
        }
        else if(rear == queue.length-1) rear = 0;
        else rear++;
        return true;
    }

    public int getFront() {
        return front == -1 ? -1 :queue[front];
    }

    public int getRear() {
        return rear == -1 ? -1 :queue[rear];
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public boolean isFull() {
        return (front == queue.length-1 && rear == 0) || front+1 == rear;
    }

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(3);
//        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.insertLast(1));
//        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.insertLast(2));
//        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.insertFront(3));
//        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.insertFront(4));
//        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
//        System.out.println("----- Delete Operations -----");
        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.isFull());
        System.out.println(myCircularDeque.deleteLast());
        System.out.println(myCircularDeque.insertFront(4));
        System.out.println(myCircularDeque.getFront());
//        System.out.println(myCircularDeque.getRear());
//        System.out.println(myCircularDeque.isEmpty());
//        System.out.println(myCircularDeque.isFull());
    }
}
