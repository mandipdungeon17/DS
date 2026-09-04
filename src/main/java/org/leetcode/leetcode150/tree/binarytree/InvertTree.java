package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/invert-binary-tree/submissions/2119163427/?envType=study-plan-v2&envId=top-interview-150
public class InvertTree {

    // Time Complexity O(n) and Space Complexity O(n). It took 0ms. I used Level Order Traversal to invert the tree. I created a new tree and added the nodes in reverse order.
//    public TreeNode invertTree(TreeNode root) {
//        if(root == null) return null;
//        TreeNode resNode = new TreeNode(root.val);
//        Queue<TreeNode> node = new LinkedList<>();
//        Queue<TreeNode> temp = new LinkedList<>();
//
//        node.add(root);
//        temp.add(resNode);
//        while(!node.isEmpty()){
//            TreeNode presentNode = node.remove();
//            TreeNode presentResNode = temp.remove();
//            if(presentNode.left !=null){
//                presentResNode.right = new TreeNode(presentNode.left.val);
//                node.add(presentNode.left);
//                temp.add(presentResNode.right);
//            }
//            if(presentNode.right !=null){
//                presentResNode.left = new TreeNode(presentNode.right.val);
//                node.add(presentNode.right);
//                temp.add(presentResNode.left);
//            }
//        }
//        return resNode;
//    }

    // Time Complexity O(n) and Space Complexity O(n). It took 0ms. I used Level Order Traversal to invert the tree.
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        Queue<TreeNode> node = new LinkedList<>();
        node.add(root);

        while(!node.isEmpty()) {
            TreeNode presentNode = node.remove();

            TreeNode temp = presentNode.left;
            presentNode.left = presentNode.right;
            presentNode.right = temp;

            if (presentNode.left != null) {
                node.add(presentNode.left);
            }
            if (presentNode.right != null) {
                node.add(presentNode.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {

        InvertTree invertTree = new InvertTree();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode invertedRoot = invertTree.invertTree(root);
        System.out.println("Inverted tree root value: " + invertedRoot.val); // Should print 4
    }
}
