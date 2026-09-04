package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/binary-search-tree-iterator/submissions/2124706141/?envType=study-plan-v2&envId=top-interview-150
public class BSTIterator {
    Deque<TreeNode> stack = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        if(root == null) return;
        TreeNode curr = root;
        while(curr != null){
            stack.push(curr);
            curr = curr.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if(node.right != null) {
            TreeNode curr = node.right;
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        //["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
        //[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]

        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator bstIterator = new BSTIterator(root);
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
    }
}
