package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;
//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
public class MaxDepth {

    // Time Complexity O(n) and Space Complexity O(n). It took 3ms. Used Level Order Traversal to find the maximum depth of the binary tree.
//    public int maxDepth(TreeNode root) {
//        int count=0;
//        if(root == null) return count;
//        Queue<TreeNode> node = new LinkedList<>();
//        node.add(root);
//        while(!node.isEmpty()){
//            int size = node.size();
//            for(int i=0;i<size;i++){
//                TreeNode presentNode = node.remove();
//                if(presentNode.left !=null){
//                    node.add(presentNode.left);
//                }
//                if(presentNode.right !=null){
//                    node.add(presentNode.right);
//                }
//            }
//            count++;
//        }
//        return count;
//    }

    // Time Complexity O(n) and Space Complexity O(n). It took 0ms. Used Recursion to find the maximum depth of the binary tree.
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftNode = maxDepth(root.left);
        int rightNode = maxDepth(root.right);
        return 1+ Math.max(leftNode, rightNode);
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth.maxDepth(root)); // Output: 3
    }
}
