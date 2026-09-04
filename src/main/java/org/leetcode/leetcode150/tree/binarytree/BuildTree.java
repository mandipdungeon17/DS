package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {
    int preIndx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();

        for(int i=0; i<inorder.length; i++){
            inOrderMap.put(inorder[i], i);
        }

        return build(0, inorder.length-1, preorder, inOrderMap);

    }

    private TreeNode build(int inLeft, int inRight, int[] preorder, Map<Integer, Integer> inOrderMap){
        if(inLeft > inRight) return null;

        int rootVal = preorder[preIndx++];
        TreeNode root = new TreeNode(rootVal);

        int mid = inOrderMap.get(rootVal);

        root.left = build(inLeft, mid-1, preorder, inOrderMap);
        root.right = build(mid+1, inRight, preorder, inOrderMap);

        return root;
    }

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree.buildTree(preorder, inorder);
        System.out.println(root);
    }
}
