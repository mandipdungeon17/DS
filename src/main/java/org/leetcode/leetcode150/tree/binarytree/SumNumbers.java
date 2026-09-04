package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(h) h is the height of the tree. It took 5ms. I used recursive traversal and String to find the sum of all root to leaf numbers.
public class SumNumbers {

//    List<String> list = null;
//    String str = null;
//    public int sumNumbers(TreeNode root) {
//        list = new ArrayList<>();
//        str = "";
//        recursive(root, str);
//        int sum = 0;
//        for(String st : list){
//            sum+= Integer.parseInt(st);
//        }
//
//        return sum;
//    }
//
//    private void recursive(TreeNode root, String str){
//        if(root == null) return;
//        else if(root.left == null && root.right == null){
//            list.add(str+root.val);
//            return;
//        }
//        str+= String.valueOf(root.val);
//        recursive(root.left, str);
//        recursive(root.right, str);
//    }

//    List<String> list = null;
//    StringBuilder str = null;
//    public int sumNumbers(TreeNode root) {
//        list = new ArrayList<>();
//        str = new StringBuilder();
//        recursive(root, str);
//        int sum = 0;
//        for(String st : list){
//            sum+= Integer.parseInt(st);
//        }
//
//        return sum;
//    }
//
//    private void recursive(TreeNode root, StringBuilder str){
//        if(root == null) return;
//        else if(root.left == null && root.right == null){
//            list.add(str.toString()+root.val);
//            return;
//        }
//        str.append(root.val);
//        recursive(root.left, str);
//        recursive(root.right, str);
//        str.deleteCharAt(str.length() - 1);
//    }

    // Time Complexity O(n) and Space Complexity O(h) h is the height of the tree. It took 1ms. I used recursive traversal and int to find the sum of all root to leaf numbers.
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        int curr = 0;
        recursive(root, curr);
        return sum;
    }

    private void recursive(TreeNode root, int curr){
        if(root == null) return;
        else if(root.left == null && root.right == null){
            sum+= curr*10 + root.val;
            return;
        }
        curr = curr*10 + root.val;
        recursive(root.left, curr);
        recursive(root.right, curr);
    }

    public static void main(String[] args) {
        SumNumbers sumNumbers = new SumNumbers();
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        System.out.println(sumNumbers.sumNumbers(root));

        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(1);
        System.out.println(sumNumbers.sumNumbers(root1));


    }
}
