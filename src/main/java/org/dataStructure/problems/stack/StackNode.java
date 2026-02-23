package org.dataStructure.problems.stack;

public class StackNode {

    int data;
    StackNode next;
    StackNode prev;

    public StackNode(int data){
        this.data = data;
        this.next = this.prev = null;
    }
}
