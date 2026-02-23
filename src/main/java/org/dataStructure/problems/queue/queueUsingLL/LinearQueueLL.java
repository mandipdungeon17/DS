package org.dataStructure.problems.queue.queueUsingLL;

public class LinearQueueLL {
    QueueNode head;
    QueueNode tail;

    public void createNode(int data){
        QueueNode node = new QueueNode(data);
        this.head = this.tail = node;
        System.out.println("The value is successfully inserted : " + this.tail.data);
    }

    public void enQueue(int data){
        if(this.head == null) this.createNode(data);
        else{
            QueueNode node = new QueueNode(data);
            this.tail.next = node;
            this.tail = node;
            System.out.println("The value is successfully inserted : " + this.tail.data);
        }
    }

    public void deQueue(){
        if(this.head == null) System.out.println("The Queue is Empty");
        else{
            System.out.println("The value is deleted : " + this.head.data);
            this.head = this.head.next;
        }
    }

    public void peek(){
        if(this.head == null) System.out.println("The Queue is Empty");
        else System.out.println("The first queue is : " + this.head.data);
    }

    public void delete(){
        this.head = this.tail = null;
        System.out.println("The Queue is deleted");
    }

    public static void main(String[] args) {
        LinearQueueLL queue = new LinearQueueLL();
        queue.peek();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.enQueue(10);
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.enQueue(4);
        queue.peek();
        queue.enQueue(5);
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.deQueue();
        queue.peek();
        queue.delete();
    }
}
