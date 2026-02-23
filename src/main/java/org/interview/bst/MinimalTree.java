package org.interview.bst;

import java.util.LinkedList;
import java.util.Queue;

public class MinimalTree {

//    MinimalTree root;
//
//    MinimalTree(){
//        this.root = null;
//    }
//
//    public void createMinimalBST(int[] array) {
//        for(int i : array){
//            if(this.root == null){
//                this.root = new MinimalTree(i);
//            }
//            else{
//                if(this.root.data > i ) {
//                    this.root.left = setLeft(this.root.left, i);
//                }
//                else {
//                    this.root.right = setRight(this.root.right, i);
//                }
//            }
//        }
//    }
//
//    private MinimalTree setRight(MinimalTree node, int i) {
//        if(node == null) node = new MinimalTree(i);
//        else{
//            if(node.data < i) node.right = setRight(node.right, i);
//            else node.left = setLeft(node.left, i);
//        }
//        return node;
//    }
//
//    private MinimalTree setLeft(MinimalTree node, int i) {
//        if(node == null) node = new MinimalTree(i);
//        else{
//            if(node.data > i) node.left = setLeft(node.left, i);
//            else node.right = setRight(node.right, i);
//        }
//        return node;
//    }
//
//    public void levelOrder(){
//        if(this.root == null){
//            System.out.println("The root is null");
//            return;
//        }
//        Queue<MinimalTree> treeNodeQueue = new LinkedList<>();
//        treeNodeQueue.add(this.root);
//
//        while(!treeNodeQueue.isEmpty()){
//            MinimalTree treeNode = treeNodeQueue.remove();
//            System.out.print(treeNode.data + " ->");
//            if(treeNode.left != null) treeNodeQueue.add(treeNode.left);
//            if(treeNode.right != null) treeNodeQueue.add(treeNode.right);
//        }
//    }
//
//    public static void main(String[] args) {
//        MinimalTree minimalTree = new MinimalTree();
//        System.out.println();
//        System.out.println("Insert Node");
//        int[] arr = {70,50,30,20,40,60,90,80,100};
//        minimalTree.createMinimalBST(arr);
//        minimalTree.levelOrder();
//    }
    int data;
    static MinimalTree root = null;
    MinimalTree left;
    MinimalTree right;
    int size;

    MinimalTree(int data){
        this.data = data;
        this.size = 1;
    }
    

    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static MinimalTree createMinimalBST(int[] array) {
        root = createBST(array, 0, array.length-1);
        System.out.println(root.height());
        System.out.println("The numbers are inserted successfully");
        return root;
    }

    private static MinimalTree createBST(int[] array, int start, int end) {
//        10,20,30,40,50,60,70,80,90,100
        if(end < start) return null;
        int mid = (end + start)/2;
        MinimalTree node = new MinimalTree(array[mid]);

        node.left = createBST(array, start, mid-1);
        node.right = createBST(array, mid+1, end);

        return node;
    }

    public static void levelOrder(){
        if(root == null){
            System.out.println("The root is null");
            return;
        }
        Queue<MinimalTree> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(root);

        while(!treeNodeQueue.isEmpty()){
            MinimalTree treeNode = treeNodeQueue.remove();
            System.out.print(treeNode.data + " ->");
            if(treeNode.left != null) treeNodeQueue.add(treeNode.left);
            if(treeNode.right != null) treeNodeQueue.add(treeNode.right);
        }
    }

        public static void main(String[] args) {
        System.out.println();
        System.out.println("Insert Node");
        int[] arr = {10,20,30,40,50,60,70,80,90,100};
        createMinimalBST(arr);
        levelOrder();
        System.out.println();
    }
}
