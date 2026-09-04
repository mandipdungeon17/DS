package org.leetcode.leetcode150.tree.bst;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/validate-binary-search-tree/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(h) h is the height of the tree. It took 0ms. I used Inorder Traversal to check if the tree is a valid BST.
public class IsValidBST {
    long prev = Long.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        boolean left = isValidBST(root.left);
        if(prev != Long.MAX_VALUE && prev >= root.val){
            return false;
        }
        prev = root.val;
        boolean right = isValidBST(root.right);

        return left && right;
    }

    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        isValidBST.prev = Long.MAX_VALUE;
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(isValidBST.isValidBST(root)); // true

        isValidBST.prev = Long.MAX_VALUE;

        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.right.left = new TreeNode(3);
        root1.right.right = new TreeNode(6);
        System.out.println(isValidBST.isValidBST(root1)); // false

        isValidBST.prev = Long.MAX_VALUE;

        TreeNode root2 = new TreeNode(2147483647);
        root2.left = new TreeNode(2147483647);
        System.out.println(isValidBST.isValidBST(root2)); // false


    }
}
