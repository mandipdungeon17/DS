package org.leetcode.leetcode150.tree.bfs;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-interview-150

public class RightSideView {

    // Time Complexity O(n) and Space Complexity O(n). It took 1ms. I used Level Order Traversal to traverse the tree and get the right side view of the tree.
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> node = new ArrayDeque<>();

        node.add(root);

        while(!node.isEmpty()){
            int size = node.size();
            TreeNode pNode = null;
            for(int i=0; i<size; i++){
                pNode = node.remove();

                if(pNode.left != null){
                    node.add(pNode.left);
                }
                if(pNode.right != null){
                    node.add(pNode.right);
                }
            }
            res.add(pNode.val);
        }
        return res;
    }

    public static void main(String[] args) {
        RightSideView rightSideView = new RightSideView();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<Integer> result = rightSideView.rightSideView(root);
        System.out.println(result); // Output: [3, 20, 7]
    }
}
