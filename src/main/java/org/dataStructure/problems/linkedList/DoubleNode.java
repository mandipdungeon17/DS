package org.dataStructure.problems.linkedList;

public class DoubleNode<T> {
    public T data;
    public DoubleNode<T> next;
    public DoubleNode<T> prev;

    public DoubleNode(T data){
        this.data = data;
    }
}
