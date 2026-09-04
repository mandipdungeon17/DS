package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/submissions/2124618235/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n^2) and Space Complexity O(h) h is the height of the tree. It is giving TLE. I used recursive traversal to find the lowest common ancestor of a binary tree.
public class LowestCommonAncestor {
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if(root == null) return null;
//
//        while(true){
//            if(p == root || q == root) return root;
//
//            int lCount = getCount(root.left, p, q);
//            int rCount = getCount(root.right, p, q);
//
//            if(lCount == rCount) return root;
//            else if(lCount == 2) {
//                root = root.left;
//            } else {
//                root = root.right;
//            }
//        }
//    }
//
//    private int getCount(TreeNode node, TreeNode p, TreeNode q){
//        if (node == null) return 0;
//
//        int left = getCount(node.left, p, q);
//        if (left == 2) return 2;
//
//        int right = getCount(node.right, p, q);
//        if (right == 2) return 2;
//
//        int self = (node == p || node == q) ? 1 : 0;
//        return Math.min(2, left + right + self);
//    }

    // Time Complexity O(n) and Space Complexity O(h) h is the height of the tree. It took 1ms. I used recursive traversal to find the lowest common ancestor of a binary tree.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root; // p and q found in different sides
        return left != null ? left : right;             // both in one side (or none)
    }


    public static void main(String[] args) {
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode lca = lowestCommonAncestor.lowestCommonAncestor(root, root.left, root.left.right.right);
        System.out.println("Lowest Common Ancestor: " + lca.val);
    }
}
