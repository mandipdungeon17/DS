package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/count-complete-tree-nodes/description/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O((log n)^2) and Space Complexity O(h) h is the height of the tree
public class CountNodes {

//    int count=0;
//    public int countNodes(TreeNode root) {
//        if(root == null) return count;
//        else count++;
//
//        countNodes(root.left);
//        countNodes(root.right);
//
//        return count;
//    }

//    public int countNodes(TreeNode root) {
//        if(root == null) return 0;
//        return 1 + countNodes(root.left) + countNodes(root.right);
//    }

    public int countNodes(TreeNode root) {
        if(root == null) return 0; // empty subtree => 0 nodes

        int leftHeight = getLeftHeight(root); // walk only left edge: O(h)
        int rightHeight = getRightHeight(root); // walk only right edge: O(h)

        if(leftHeight == rightHeight){ // perfect binary tree => 2^h - 1 nodes
            return (int)(Math.pow(2, rightHeight) - 1); // --- O(1)
        }
        // not perfect: count root + left subtree + right subtree
        // recurrence on complete tree: T(h) = T(h-1) + O(h)
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode root){
        int h = 0;
        while(root != null){
            h++;
            root = root.left;
        }
        return h;
    }

    private int getRightHeight(TreeNode root){
        int h = 0;
        while(root != null){
            h++;
            root = root.right;
        }
        return h;
    }

    public static void main(String[] args) {
        CountNodes countNodes = new CountNodes();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        int count = countNodes.countNodes(root);
        System.out.println(count);

        TreeNode root1 = new TreeNode(1);
        int count1 = countNodes.countNodes(root1);
        System.out.println(count1);
    }
}
