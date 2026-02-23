package org.dataStructure.problems.linkedList;

public class CircularDoubleLL<T> {
    public DoubleNode<T> head, tail = null;
    int size;

    public boolean nodeNull(){
        return this.head == null;
    }

    public void createNewNode(T data){
        DoubleNode<T> node = new DoubleNode<>(data);
        this.head = this.tail = node;
        this.tail.next = this.tail.prev = null;
    }

    public void addNodeAtLocation(T data, int index){
        DoubleNode<T> node = new DoubleNode<>(data);
        if(this.nodeNull()) this.createNewNode(data);
        else{
            if(index == 0){
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
                this.tail.next = this.head;
                this.head.prev = this.tail;
            }
            else if(index >= size){
                node.prev = this.tail;
                this.tail.next = node;
                this.tail = node;
                this.tail.next = this.head;
                this.head.prev = this.tail;
            }
            else{
                int count = 0;
                DoubleNode<T> current = this.head;
                while(current != tail){
                    count++;
                    if(count == index){
                        node.next = current.next;
                        node.prev = current;
                        current.next.prev = node;
                        current.next = node;
                        break;
                    }
                    current = current.next;
                }
            }
            size++;
        }
    }

    public void removeFromLocation(int index){
        if(this.nodeNull()) System.out.println("No node available to remove");
        else{
            if(index == 0){
                this.head = this.head.next;
                this.tail.next = this.head;
                this.head.prev = this.tail;
            }
            else if(index >= size-1){
                this.tail = this.tail.prev;
                this.tail.next = this.head;
                this.head.prev = this.tail;
            }
            else{
                DoubleNode<T> current = this.head;
                int count = -1;
                while(current.next != tail){
                    count++;
                    if(count == index){
                        current.next.prev = current.prev;
                        current.prev.next = current.next;
                        break;
                    }
                    current = current.next;
                }
            }
            size--;
            if(size==0) this.head = this.tail = this.head.next = null;
        }
    }

    public void searchNode(T data){
        if(this.nodeNull()) System.out.println("No data available");
        else if(this.tail.data == data) System.out.println("Data found at index : " + size);
        else{
            int count = -1;
            DoubleNode<T> current = this.head;
            while(current != tail){
                count++;
                if(current.data == data){
                    System.out.println("Data found at index : " + count);
                    return;
                }
                current = current.next;
            }
            System.out.println("No data available");
        }

    }

    public void print(){
        DoubleNode<T> current = this.head;
        while(current != tail){
            System.out.print(current.data + " " + " -> ");
            current = current.next;
        }
        System.out.print(current.data + " " + " -> ");
    }

    public void printReverse(){
        DoubleNode<T> current = this.tail;
        while(current != head){
            System.out.print(current.data + " " + " -> ");
            current = current.prev;
        }
        System.out.print(current.data + " " + " -> ");
    }

    public static void main(String[] args){
        CircularDoubleLL<Integer> circularDoubleLL = new CircularDoubleLL<>();
        circularDoubleLL.createNewNode(10);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.addNodeAtLocation(20, 1);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.addNodeAtLocation(30, 2);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.addNodeAtLocation(40, 1);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.addNodeAtLocation(50, 0);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.searchNode(30);

        /* --------------------------------------------------- */

        circularDoubleLL.removeFromLocation(2);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.removeFromLocation(6);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();
        circularDoubleLL.removeFromLocation(0);
        circularDoubleLL.print();
        System.out.println();
        circularDoubleLL.printReverse();
        System.out.println();

        circularDoubleLL.searchNode(40);

    }
}
