package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(h) h is the height of the tree. It took 0ms. I used reverse preorder traversal to flatten the tree.
public class Flatten {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        flattenTree(root);
    }

    private void flattenTree(TreeNode root) {
        if (root == null) {
            return;
        }

        flattenTree(root.right);
        flattenTree(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }


    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten.flattenTree(root);

        System.out.println(root);
    }
}
