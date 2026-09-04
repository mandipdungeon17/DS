package org.leetcode.leetcode150.tree.bfs;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
public class AverageOfLevels {

    // Time Complexity O(n) and Space Complexity O(n). It took 2ms.
    public List<Double> averageOfLevels(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Queue<TreeNode> node = new LinkedList<>();
        node.add(root);
        List<Double> result = new ArrayList<>();

        while(!node.isEmpty()){
            int size = node.size();
            double val = 0.0;
            for(int i=0; i<size; i++){
                TreeNode presentNode = node.remove();
                val+=presentNode.val;
                if(presentNode.left != null){
                    node.add(presentNode.left);
                }
                if(presentNode.right != null){
                    node.add(presentNode.right);
                }
            }
            result.add(val/size);
        }
        return result;
    }

    public static void main(String[] args) {
        AverageOfLevels averageOfLevels = new AverageOfLevels();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<Double> result = averageOfLevels.averageOfLevels(root);
        System.out.println(result); // Output: [3.0, 14.5, 11.0]
    }
}
