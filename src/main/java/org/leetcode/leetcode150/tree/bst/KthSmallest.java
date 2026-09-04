package org.leetcode.leetcode150.tree.bst;

import org.leetcode.leetcode150.tree.TreeNode;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(n). It took 0ms. I used Inorder Traversal to find the kth smallest element in the BST.
public class KthSmallest {
//    List<Integer> list = new ArrayList<>();
//
//    public int kthSmallest(TreeNode root, int k) {
//        return kthSmallest(root).get(k-1);
//    }
//
//    private List<Integer> kthSmallest(TreeNode root) {
//        if(root == null){
//            return list;
//        }
//        kthSmallest(root.left);
//        list.add(root.val);
//        kthSmallest(root.right);
//
//        return list;
//    }

//    int count = 0;
//    int res = 0;
//    public int kthSmallest(TreeNode root, int k) {
//        if(root == null){
//            return res;
//        }
//        kthSmallest(root.left, k);
//        count++;
//        if(count == k){
//            res = root.val;
//            return res;
//        }
//        kthSmallest(root.right, k);
//
//        return res;

    int count = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        res = 0;
        inorder(root, k);
        return res;
    }

    private void inorder(TreeNode root, int k){
        if(root == null || count >= k ) return;

        inorder(root.left, k);

        if(count >= k) return;
        count++;
        if(count == k){
            res = root.val;
            return;
        }

        inorder(root.right, k);
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(4);
//        root.left.right = new TreeNode(2);
//
//        int result = kthSmallest.kthSmallest(root, 1);
//        System.out.println(result); // Output: 1

//        kthSmallest.list = new ArrayList<>();
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.right = new TreeNode(4);
        root1.left.left = new TreeNode(2);
        root1.left.left.left = new TreeNode(1);

        int result1 = kthSmallest.kthSmallest(root1, 3);
        System.out.println(result1); // Output: 3


    }
}
