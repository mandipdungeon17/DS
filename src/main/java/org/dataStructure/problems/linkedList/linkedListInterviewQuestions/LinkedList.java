package org.dataStructure.problems.linkedList.linkedListInterviewQuestions;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {
    public SingleNode head;
    public SingleNode tail;
//    public SingleNode head1;
//    public SingleNode tail1;
    public int size;

    public void createLL(int data){
        SingleNode node = new SingleNode(data);
        node.next = null;
        head = node;
        tail = node;
        size = 1;
    }

    public void insertNode(int data){
        SingleNode node = new SingleNode(data);
        node.next = null;
        tail.next = node;
        tail = node;
        size++;
    }

    /*------- Question 1 -------------------*/
    public void deleteDuplicates(){
//        SingleNode current = this.head;
//        Set<Integer> dupli = new HashSet<>();
//        if(current != null) dupli.add(current.data);
//        while(current != null && current.next != null){
//            if(dupli.contains(current.next.data)) {
//                current.next = current.next.next;
//            }
//            else{
//                dupli.add(current.data);
//                current = current.next;
//            }
//        }
        SingleNode current = this.head;
        SingleNode prev = null;
        Set<Integer> dupli = new HashSet<>();
        while(current != null){
            if(dupli.contains(current.data)){
                prev.next = current.next;
                size--;
            }
            else{
                prev = current;
                dupli.add(current.data);
            }
            current= current.next;
        }
    }

    /*------- Question 2 -------------------*/
    public SingleNode nthToLast(int index){
//        SingleNode current = this.head;
//        int pos = size - index;
//        while(pos != 0 ){
//            current = current.next;
//            pos--;
//        }
//        return current;
//        SingleNode current = this.head;
//        SingleNode nth = this.head;
//        int count = 0;
//        while(current != null){
//            if(count == index) nth = nth.next;
//            else count++;
//            current = current.next;
////            index--;
////            if(index == 0) nth = this.head;
////            if(index <0) nth = nth.next;
//        }
//        return nth;
        SingleNode current = this.head;
        SingleNode nth = this.head;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        while(current != null){
            current = current.next;
            nth = nth.next;
        }
        return nth;
    }

    /*------- Question 3 -------------------*/
    public LinkedList partition(int x) {
        // TODO
//      Self Approach 1
//        SingleNode current = this.head;
//        SingleNode node;
//        SingleNode head1 = null;
//        SingleNode tail1 = null;
//        LinkedList linkedList = new LinkedList();
//        int size1 = 0;
//        while(current != null){
//            node = new SingleNode(current.data);
//            if(size1 == 0){
//                node.next = null;
//                head1 = node;
//                tail1 = node;
//            }
//            else{
//                if(current.data < x){
//                    node.next = head1;
//                    head1 = node;
//                }
//                else{
//                    tail1.next = node;
//                    tail1 = node;
//                }
//            }
//            size1++;
//            current = current.next;
//        }
//        linkedList.head = head1;
//        return linkedList;

//        Best Approach
        SingleNode current = this.head;
        this.tail = this.head;

        while(current != null){
            SingleNode next = current.next;
            if(current.data < x){
                current.next = head;
                this.head = current;
            }
            else{
                this.tail.next = current;
                this.tail = current;
                this.tail.next = null;
            }
            current = next;
        }
        LinkedList ll = new LinkedList();
        ll.head = this.head;
        return ll;
    }

    /*------- Question 4 -----------*/

    public LinkedList reverseSumList(LinkedList ll, LinkedList ll1){
//        Self Approach

//        int num1 = 0;
//        int num2 = 0;
//        int sum1 = 0;
//        int sum2 = 0;
//        SingleNode nodell = ll.head;
//        SingleNode nodell1 = ll1.head;
//
//        while(nodell != null) {
//            if (num1 == 0) {
//                num1 = nodell.data;
//            } else {
//                num1 = num1 * 10 + nodell.data;
//            }
//            nodell = nodell.next;
//        }
//
//        while(num1 != 0){
//            sum1 = sum1*10 + num1%10;
//            num1 = num1/10;
//        }
//
//        while(nodell1 != null){
//            if(num2==0){
//                num2 = nodell1.data;
//            }
//            else{
//                num2 = num2*10 + nodell1.data;
//            }
//            nodell1 = nodell1.next;
//        }
//
//        while(num2 != 0){
//            sum2 = sum2*10 + num2%10;
//            num2 = num2/10;
//        }
//        int sum = sum1 + sum2;
//        System.out.println(sum1 + " " + sum2 + " " + sum) ;
//
//        int pos = 0;
//        while(sum != 0){
//            SingleNode node = new SingleNode(sum%10);
//            node.next = null;
//            sum = sum/10;
//            if(pos == 0){
//                this.head = node;
//                this.tail = node;
//                pos++;
//            }
//            else{
//                this.tail.next = node;
//                this.tail = node;
//                pos++;
//            }
//
//        }
//        LinkedList ll2 = new LinkedList();
//        ll2.head = this.head;
//        return ll2;


//        Best Approach
        SingleNode n1 = ll.head;
        SingleNode n2 = ll1.head;
        int sum = 0;
        int pos = 0;

        while(n1 != null && n2 != null){
            sum = n1.data + n2.data + sum;
            SingleNode node = new SingleNode(sum%10);
            node.next = null;
            sum = sum/10;
            if(pos == 0){
                this.head = this.tail = node;
            }
            else {
                this.tail.next = node;
                this.tail = node;
            }
            pos++;
            n1 = n1.next;
            n2 = n2.next;
        }
        LinkedList ll2 = new LinkedList();
        ll2.head = this.head;
        return ll2;
    }


    public void traversal(){
        SingleNode node = head;
        while(node != null){
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.print("\n");
    }
//    public void traversal1(){
//        SingleNode node = head1;
//        while(node != null){
//            System.out.print(node.data + " -> ");
//            node = node.next;
//        }
//        System.out.print("\n");
//    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.createLL(1);
        linkedList.insertNode(9);
        linkedList.insertNode(5);
        linkedList.traversal();
        linkedList.insertNode(10);
        linkedList.insertNode(2);
        linkedList.traversal();
        linkedList.deleteDuplicates();
        linkedList.traversal();
        SingleNode n = linkedList.nthToLast(2);
        System.out.print(n.data);
        System.out.println("\n");
        linkedList = linkedList.partition(4);
        linkedList.traversal();

        LinkedList linkedList1 = new LinkedList();
        linkedList1.createLL(7);
        linkedList1.insertNode(1);
        linkedList1.insertNode(6);
        LinkedList linkedList2 = new LinkedList();
        linkedList2.createLL(5);
        linkedList2.insertNode(9);
        linkedList2.insertNode(2);

        linkedList = linkedList.reverseSumList(linkedList1, linkedList2);
        linkedList.traversal();

    }
}
