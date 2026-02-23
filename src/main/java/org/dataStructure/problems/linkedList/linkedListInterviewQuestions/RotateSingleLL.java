package org.dataStructure.problems.linkedList.linkedListInterviewQuestions;

public class RotateSingleLL {

    SingleNode head;
    SingleNode tail;
    int size;
    public boolean isEmpty(){
        return this.head == null;
    }
    public void createNewNode(int data){
        SingleNode node = new SingleNode(data);
        this.head = this.tail = node;
        this.size++;
    }

    public void insertNode(int data){
        if(isEmpty()) this.createNewNode(data);
        else{
            SingleNode node = new SingleNode(data);
            this.tail.next = node;
            this.tail = node;
            this.size++;
        }
        System.out.println("The node is successfully inserted : " + data);
    }

    public void popNode(){
        int number;
        if(isEmpty()){
            System.out.println("The LL is Empty");
            return;
        }
        else{
            SingleNode node = this.head;
            while(node.next != tail){
                node = node.next;
            }
            number = node.next.data;
            node.next = null;
            this.tail = node;
            this.size--;
        }
        System.out.println("The popped number is : " + number);
    }

    public void rotate(int number){
        for(int i=0; i<number; i++){
            SingleNode node = this.head;
            this.head = this.head.next;
            this.tail.next = node;
            this.tail = node;
            this.tail.next = null;
        }
        System.out.println("The rotated node is");
        printNode();
    }

    public void printNode() {
        SingleNode current = this.head;

        if(this.isEmpty()){
            System.out.println("Singly LinkedList is Empty");
            return;
        }
        System.out.print(" Nodes are : ");

        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public static void main(String[] args) {
        RotateSingleLL rotateSingleLL = new RotateSingleLL();
        rotateSingleLL.insertNode(1);
        rotateSingleLL.insertNode(2);
        rotateSingleLL.insertNode(3);
        rotateSingleLL.insertNode(4);
        rotateSingleLL.insertNode(5);
        rotateSingleLL.printNode();
        rotateSingleLL.rotate(2);
    }


}
