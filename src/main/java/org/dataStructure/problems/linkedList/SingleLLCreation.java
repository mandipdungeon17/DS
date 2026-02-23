package org.dataStructure.problems.linkedList;
public class SingleLLCreation<T> {

    Node<T> head, tail = null;
    int size;

    public boolean nodeNotNull(){
        return this.head != null;
    }
    public void createSingleLinkedList(T nodeValue){
        Node<T> node = new Node<T>(nodeValue);
        this.head = this.tail = node;
        this.tail.next = null;
        size=1;
    }

    // public void addNodeAtLast(T nodeValue){
    //     SingleNode<T> node = new SingleNode<>(nodeValue);

    //     if(this.nodeNotNull()){
    //         this.tail.next = node;
    //         this.tail = node;
    //         this.tail.next = null;
    //     }
    //     else this.createSingleLinkedList(nodeValue);
    // }

    // public void addNodeAtFirst(T nodeValue){
    //     SingleNode<T> node = new SingleNode<>(nodeValue);
    //     if(this.nodeNotNull()) {
    //         node.next = this.head;
    //         this.head = node;
    //     }
    //     else this.createSingleLinkedList(nodeValue);
    // }

    // public void addNodeAtIndex(T nodeValue, int index){
    //     SingleNode<T> node = new SingleNode<>(nodeValue);
    //     SingleNode<T> current = this.head;
    //     int count = 0;

    //     if(this.nodeNotNull()){
    //         while(current != null){
    //             count++;
    //             if(count == index) {
    //                 node.next = current.next;
    //                 current.next = node;
    //                 break;
    //             }
    //             current = current.next;
    //         }
    //     }
    //     else this.createSingleLinkedList(nodeValue);
    // }

    public void addNodeAfterValue(T nodeValue, T afterValue){
        Node<T> node = new Node<>(afterValue);
        Node<T> current = this.head;

        if(this.nodeNotNull()){
            while(current != null){
                if(current.data == afterValue){
                   node.next = current.next;
                   current.next = node; 
                   break;
                }
                current = current.next;
            }
        }
        else this.createSingleLinkedList(nodeValue);
    }

    public void addNodeBeforeValue(T nodeValue, T beforeValue){
        Node<T> node = new Node<>(nodeValue);
        Node<T> current = this.head;
        if(this.nodeNotNull()){
            while(current.next != null){
                if(current.next.data == beforeValue){
                   node.next = current.next;
                   current.next = node; 
                   break;
                }
                current = current.next;
            }
        }
        else this.createSingleLinkedList(nodeValue);
    }

    public void addNodeAtLocation(T nodeValue, int location){
        Node<T> node = new Node<>(nodeValue);

        if(!this.nodeNotNull()){
            this.createSingleLinkedList(nodeValue);
        }
        else {
            if (location == 0) {
                node.next = this.head;
                this.head = node;
            } else if (location >= this.size) {
                node.next = null;
                this.tail.next = node;
                this.tail = node;
            } else {
                Node<T> current = this.head;
                int count = 0;
                while (current != null) {
                    count++;
                    if (count == location) {
                        node.next = current.next;
                        current.next = node;
                        break;
                    }
                    current = current.next;
                }
            }
        }
        size++; 
    }
    

    /* --------------------- Deletion of SingleNode -------------------------------- */

    public void removeNodeFromFirst(){
        if(this.nodeNotNull() && size==1){
            this.head = null;
            this.tail = null;
            size--;
        } 
        else if(this.nodeNotNull()){
            this.head = this.head.next;
            size--;
            if(size==0) this.tail = null;
        }
        else System.out.print("No node available to remove");
    }

    public void removeNodeFromLast(){
        if(this.head == null){
            System.out.print("No node available to remove");
            return;
        }
        if(this.head == this.tail){
            this.head = this.tail = null;
        }
        else {
            Node<T> currentNode = this.head;
            while(currentNode.next != this.tail){
                currentNode = currentNode.next;
            }
            currentNode.next = null;
            this.tail = currentNode;
        }
        size--;

    }

    public void removeNodeAtIndex(int index){
        if(this.nodeNotNull()){
            int count = 0;
            Node<T> current = this.head;
            while(current.next.next != null){
                count++;
                if(count == index){
                    System.out.print("Current : " + current.data);
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
            size--;
            if(size==0) this.tail = null;
        }
    }

    public void printNodes(){
        Node<T> current = this.head;

        if(!this.nodeNotNull()){
            System.out.println("Singly LinkedList is Empty");
            return;
        }
        System.out.print(" Nodes are : ");

        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public static void main(String[] args){
        SingleLLCreation<Integer> singleLLCreation = new SingleLLCreation<>();
        singleLLCreation.createSingleLinkedList(5);
        // singleLLCreation.addNodeAtLast(6);
        // singleLLCreation.addNodeAtFirst(10);
        // singleLLCreation.addNodeAtIndex(15, 1);
        singleLLCreation.addNodeAtLocation(6, 0);
        singleLLCreation.addNodeAtLocation(10, 1);
        singleLLCreation.addNodeAtLocation(15, 2);
        singleLLCreation.addNodeAtLocation(20, 0);
        singleLLCreation.addNodeAtLocation(30, 5);
        // singleLLCreation.removeNodeFromFirst();
        singleLLCreation.printNodes();
         singleLLCreation.removeNodeFromLast();
        // singleLLCreation.removeNodeAtIndex(1);
        singleLLCreation.printNodes();
        singleLLCreation.removeNodeFromLast();
        singleLLCreation.printNodes();

    }
}

