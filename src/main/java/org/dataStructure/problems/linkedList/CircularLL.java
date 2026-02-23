package org.dataStructure.problems.linkedList;

public class CircularLL<T>{

    Node<T> head, tail = null;
    int size;
    public boolean nodeNull(){
        return this.head == null;
    }

    public void createCircularLL(T nodeValue){
        Node<T> newNode = new Node<>(nodeValue);
        this.head = this.tail = newNode;
        this.tail.next = this.head;
        size = 1;
    }

    public void addNodeAtLocation(T nodeValue, int index){
        Node<T> newNode = new Node<>(nodeValue);

        if(nodeNull()) this.createCircularLL(nodeValue);
        else{
            if(index == 0){
                newNode.next = this.head;
                this.head = newNode;
                this.tail.next = newNode;
            } else if(index >= size){
                newNode.next = this.head;
                this.tail.next = newNode;
                this.tail = newNode;
            }
            else{
                Node<T> current = this.head;
                int count = 0;
                while(current != this.tail){
                    count++;
                    if(count == index){
                        newNode.next = current.next;
                        current.next = newNode;
                        break;
                    }
                    current = current.next;
                }
            }
            size++;
        }
    }

    public void removeNodeFromLocation(int index){
        if(this.nodeNull()) System.out.print("No node available to delete");
        else{
            if(index == 0){
               this.head = this.head.next;
               this.tail.next = this.head;
            }
            else if(index >= size-1){
                Node<T> current = this.head;
                while(current.next != this.tail) current = current.next;
                this.tail = current;
                this.tail.next = this.head;
            }
            else{
                Node<T> current = this.head;
                int count = 0;
                while(current.next != tail){
                    count++;
                    if(count == index) current.next = current.next.next;
                    current = current.next;
                }
            }
            size--;
        }
        if(size == 0) this.head = this.tail = this.head.next = null;
    }

    public void print(){
        Node<T> current = this.head;
        while(current != this.tail){
            System.out.print(current.data + " " + " -> ");
            current = current.next;
        }
        System.out.print(current.data + " " +  " -> " + this.tail.next.data + " size : " + size);
    }


    public static void main(String[] args){

        CircularLL<Integer> circularLL = new CircularLL<>();
        circularLL.createCircularLL(1);
        System.out.print("Head : " + circularLL.head.data +
                " Tail : " + circularLL.tail.data + " Head.next : "
                + circularLL.head.next.data + " Tail.next : " + circularLL.tail.next.data);
        circularLL.addNodeAtLocation(6, 0);
        circularLL.print();
        System.out.println();
        circularLL.addNodeAtLocation(10, 1);
        circularLL.print();
        System.out.println();
        circularLL.addNodeAtLocation(15, 2);
        circularLL.print();
        System.out.println();
        circularLL.addNodeAtLocation(20, 0);
        circularLL.print();
        System.out.println();
        circularLL.addNodeAtLocation(30, 5);
        circularLL.print();
        System.out.println();
        circularLL.removeNodeFromLocation(0);
        circularLL.print();
        System.out.println();
        circularLL.removeNodeFromLocation(0);
        circularLL.print();
        System.out.println();
    }
}