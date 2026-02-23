package org.interview.bst;

public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    int height;

    TreeNode(int data){
        this.data = data;
        this.left = this.right = null;
        this.height = 1;
    }
}
