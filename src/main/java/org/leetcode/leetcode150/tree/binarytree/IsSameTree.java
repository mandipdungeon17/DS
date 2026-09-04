package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

//https://leetcode.com/problems/same-tree/description/?envType=study-plan-v2&envId=top-interview-150
public class IsSameTree {

    // Time complexity O(n) and space complexity O(n). It took 0ms. I used Level Order Traversal
//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        if(p == null && q == null) return true;
//        if (p == null || q == null) return false;
//        if (p.val != q.val) return false;
//
//        Queue<TreeNode> pNode = new LinkedList<>();
//        Queue<TreeNode> qNode = new LinkedList<>();
//        pNode.add(p);
//        qNode.add(q);
//        while(!pNode.isEmpty() && !qNode.isEmpty()){
//            TreeNode presentPNode = pNode.remove();
//            TreeNode presentQNode = qNode.remove();
//
//            if((presentPNode.left != null && presentQNode.left == null) ||
//                    (presentPNode.left == null && presentQNode.left != null)) return false;
//
//            //One of the condition can be removed as we are already checking for null in the above condition. But keeping it for better readability.
//            if(presentPNode.left != null && presentQNode.left != null){
//                if(presentPNode.left.val != presentQNode.left.val) return false;
//                pNode.add(presentPNode.left);
//                qNode.add(presentQNode.left);
//            }
//            if((presentPNode.right != null && presentQNode.right == null) ||
//                    (presentPNode.right == null && presentQNode.right != null)) return false;
//
//            if(presentPNode.right != null && presentQNode.right != null){
//                if(presentPNode.right.val != presentQNode.right.val) return false;
//                pNode.add(presentPNode.right);
//                qNode.add(presentQNode.right);
//            }
//        }
//        return true;
//    }

    // Time complexity O(n) and space complexity O(n). It took 0ms. I used DFS.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        System.out.println(isSameTree.isSameTree(p, q)); // Output: true

        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);

        TreeNode s = new TreeNode(1);
        s.right = new TreeNode(2);

        System.out.println(isSameTree.isSameTree(r, s)); // Output: false
    }
}
