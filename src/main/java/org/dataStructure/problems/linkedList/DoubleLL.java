package org.dataStructure.problems.linkedList;

public class DoubleLL<T> {
    DoubleNode<T> head, tail = null;
    int size;

    public boolean nodeNull(){
        return this.head ==null;
    }

    public void createNewNode(T data){
        DoubleNode<T> node = new DoubleNode<>(data);
        this.head = this.tail = node;
        this.tail.next = this.tail.prev = null;
        size=1;
    }

    public void addNodeAtLocation(T data, int index){
        if(nodeNull()) this.createNewNode(data);
        else{
            DoubleNode<T> node = new DoubleNode<>(data);
            if(index == 0){
                this.head.prev = node;
                node.next = this.head;
                node.prev = null;
                this.head = node;
            }
            else if(index >= size){
                this.tail.next = node;
                node.next = null;
                node.prev = this.tail;
                this.tail = node;
                this.tail.next = null;
            }
            else{
                DoubleNode<T> current = this.head;
                int count = 0;
                while(current != null){
                    count++;
                    if(count == index){
                        node.next = current.next;
                        current.next.prev = node;
                        node.prev = current;
                        current.next = node;
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
                this.head.prev = null;
            }
            else if(index >= size-1){
                this.tail = this.tail.prev;
                this.tail.next = null;
            }
            else{
                DoubleNode<T> current = this.head;
                int count = -1;
                while(current.next != null){
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
            if(size == 0) this.head = this.tail = null;
        }
    }

    public void print(){
        DoubleNode<T> current = this.head;
        while(current != null){
            System.out.print(current.data + " " + " -> ");
            current = current.next;
        }
    }

    public void printReverse(){
        DoubleNode<T> current = this.tail;
        while(current != null){
            System.out.print(current.data + " " + " -> ");
            current = current.prev;
        }
    }

    public static void main(String[] args){
        DoubleLL<Integer> dll = new DoubleLL<>();
        dll.addNodeAtLocation(10, 0);
        dll.addNodeAtLocation(20, 1);
        dll.addNodeAtLocation(30, 0);
        dll.addNodeAtLocation(40, 2);
        dll.addNodeAtLocation(50, 6);
        dll.print();
        System.out.println();
        dll.removeNodeFromLocation(0);
        dll.print();
        System.out.println();
        System.out.println("Size : " + dll.size);
        dll.removeNodeFromLocation(1);
        dll.print();
        System.out.println();

        dll.printReverse();
    }
}
