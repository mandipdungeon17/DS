package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/path-sum/submissions/2119742803/?envType=study-plan-v2&envId=top-interview-150
public class HasPathSum {

    // Time Complexity O(n) and Space Complexity O(n). It took 0ms.
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        HasPathSum hasPathSum = new HasPathSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);

        System.out.println(hasPathSum.hasPathSum(root, 22)); // true
        System.out.println(hasPathSum.hasPathSum(root, 26)); // true
        System.out.println(hasPathSum.hasPathSum(root, 18)); // true
    }
}
