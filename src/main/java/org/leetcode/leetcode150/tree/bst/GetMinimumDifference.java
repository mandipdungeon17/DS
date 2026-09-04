package org.leetcode.leetcode150.tree.bst;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/minimum-absolute-difference-in-bst/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(n). It took 0ms.
public class GetMinimumDifference {

    int ans = Integer.MAX_VALUE;
    int prev = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root == null){
            return ans;
        }
        int leftMin = getMinimumDifference(root.left);
        if(prev != Integer.MAX_VALUE) {
            ans = Math.min(ans, Math.abs(root.val - prev));
        }
        prev = root.val;
        int rightMin = getMinimumDifference(root.right);

        return Math.min(leftMin, rightMin);
    }

    public static void main(String[] args) {
        GetMinimumDifference getMinimumDifference = new GetMinimumDifference();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

//        int result = getMinimumDifference.getMinimumDifference(root);
//        System.out.println(result); // Output: 1

        getMinimumDifference.ans = Integer.MAX_VALUE;
        getMinimumDifference.prev = Integer.MAX_VALUE;

        TreeNode root1 = new TreeNode(236);
        root1.left = new TreeNode(104);
        root1.right = new TreeNode(701);
        root1.left.right = new TreeNode(227);
        root1.right.right = new TreeNode(911);
        int result1 = getMinimumDifference.getMinimumDifference(root1);
        System.out.println(result1); // Output: 9
    }
}
