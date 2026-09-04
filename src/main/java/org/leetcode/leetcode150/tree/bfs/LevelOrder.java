package org.leetcode.leetcode150.tree.bfs;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> node = new ArrayDeque<>();
        node.add(root);

        while(!node.isEmpty()){
            int size = node.size();
            List<Integer> list = new ArrayList<>();

            for(int i=0; i<size; i++){
                TreeNode pNode = node.remove();
                list.add(pNode.val);
                if(pNode.left != null) {
                    node.add(pNode.left);
                }
                if(pNode.right != null) {
                    node.add(pNode.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        LevelOrder levelOrder = new LevelOrder();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = levelOrder.levelOrder(root);
        System.out.println(result); // Output: [[3], [9, 20], [15, 7]]
    }
}
