package org.dataStructure.problems.binaryTree.linkedList;

public class BinaryNode {
    String data;
    BinaryNode left;
    BinaryNode right;
    int height;

    BinaryNode (String data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
