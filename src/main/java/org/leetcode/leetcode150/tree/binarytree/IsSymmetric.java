package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/symmetric-tree/?envType=study-plan-v2&envId=top-interview-150
public class IsSymmetric {

    // Time Complexity O(n) and Space Complexity O(n). It took 0ms.
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) return false;

        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    public static void main(String[] args) {
        IsSymmetric isSymmetric = new IsSymmetric();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric.isSymmetric(root));
    }
}
