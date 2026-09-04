package org.leetcode.leetcode150.tree.bfs;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(n). It took 1ms. I used Level Order Traversal to traverse the tree in zigzag order.
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> node = new ArrayDeque<>();
        int count = 0;

        node.add(root);

        while(!node.isEmpty()){
            int size = node.size();
            count++;
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
            if(count%2 == 0) list = list.reversed();
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        ZigzagLevelOrder zigzagLevelOrder = new ZigzagLevelOrder();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = zigzagLevelOrder.zigzagLevelOrder(root);
        System.out.println(result); // Output: [[3], [20, 9], [15, 7]]

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        List<List<Integer>> result1 = zigzagLevelOrder.zigzagLevelOrder(root1);
        System.out.println(result1); // Output: [[1], [3, 2], [4, 5]]


    }
}
