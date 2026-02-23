package org.dataStructure.problems.avlTree;

public class BinaryNode {
    public int height;
    public int data;
    public BinaryNode left;
    public BinaryNode right;

    BinaryNode(int data){
        this.data = data;
        this.height = 1;
    }

    public int getHeight(BinaryNode node){
        return node == null ? 0 : node.height;
    }
}
