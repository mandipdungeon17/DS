package org.leetcode.bst;

import java.util.ArrayList;
import java.util.List;

public class FindTarget {

    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> list = arrayNode(root, new ArrayList<>());
        System.out.println(list);
        for(int i=0; i< list.size(); i++){
            for(int j=i+1; j< list.size(); j++){
                if(list.get(i) + list.get(j) == k) return true;
            }
        }
        return false;
    }

    private static List<Integer> arrayNode(TreeNode node, List<Integer> list){
        if(node == null) return list;
        else{
            list.add(node.val);
            arrayNode(node.left, list);
            arrayNode(node.right, list);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.insert(5);
        treeNode.insert(3);
        treeNode.insert(6);
        treeNode.insert(2);
        treeNode.insert(4);
        treeNode.insert(7);
        treeNode.preOrderTraversal(treeNode.root);
        System.out.println(findTarget(treeNode.root, 9));
    }
}

class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode root = null;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    TreeNode() {
    }

    public void insert(int val){
        if(this.root == null){
            this.root = new TreeNode(val);
        }
        else{
            insertNodeRecursively(this.root, val);
        }
    }

    private TreeNode insertNodeRecursively(TreeNode root, int val) {
        if(root == null){
            root = new TreeNode(val);
            System.out.println("The value : " + val + " inserted successfully");
        } else{
            if(val <= root.val){
                root.left = insertNodeRecursively(root.left, val);
            }
            else root.right = insertNodeRecursively(root.right, val);
        }
        return root;
    }

    public void preOrderTraversal(TreeNode treeNode){
        if(this.root == null) return;

        if(treeNode != null) {
            System.out.print(treeNode.val + " ->");
            preOrderTraversal(treeNode.left);
            preOrderTraversal(treeNode.right);
        }
    }
}
