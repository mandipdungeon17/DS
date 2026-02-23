package org.dataStructure.problems.avlTree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {

    public BinaryNode root;
    AvlTree() {
        root = null;
    }

    //Traversal
    public void preOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null){
            System.out.print(node.data + " -> "); // O(1)
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + " -> "); // O(1)
            inOrder(node.right);
        }
    }

    public void postOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " -> "); // O(1)
        }
    }

    public void levelOrder(){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(this.root);

        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();
            System.out.print(presentNode.data + " -> "); // O(1)
//            System.out.println();
            if(presentNode.left != null) queue.add(presentNode.left);
            if(presentNode.right != null) queue.add(presentNode.right);
        }
    }

    public void searchNode(BinaryNode node, int value){
        if(this.root == null || node == null){
            System.out.println("Either root is null or the value doesn't exist");
        }
        else if(node.data == value){
            System.out.println("The value is found in the BST " + value);
        }
        else if(node.data > value) searchNode(node.left, value);
        else searchNode(node.right, value);
    }

    private BinaryNode rotateRight(BinaryNode disBalancedNode){
        BinaryNode newRoot = disBalancedNode.left;
//        disBalancedNode.left = disBalancedNode.left.right;
        disBalancedNode.left = newRoot.right;
        newRoot.right = disBalancedNode;
        disBalancedNode.height = 1 + Math.max(disBalancedNode.getHeight(disBalancedNode.left), disBalancedNode.getHeight(disBalancedNode.right));
        newRoot.height = 1 + Math.max(newRoot.getHeight(newRoot.left), newRoot.getHeight(newRoot.right));
        return newRoot;
    }

    private BinaryNode rotateLeft(BinaryNode disBalancedNode){
        BinaryNode newRoot = disBalancedNode.right;
//        disBalancedNode.right = disBalancedNode.right.left;
        disBalancedNode.right = newRoot.left;
        newRoot.left = disBalancedNode;
        disBalancedNode.height = 1 + Math.max(disBalancedNode.getHeight(disBalancedNode.left), disBalancedNode.getHeight(disBalancedNode.right));
        newRoot.height = 1 + Math.max(newRoot.getHeight(newRoot.left), newRoot.getHeight(newRoot.right));
        return newRoot;
    }
    public int getBalance(BinaryNode node){
        return node == null ? 0 : (node.getHeight(node.left) - node.getHeight(node.right));
    }

    private BinaryNode insertNodeRecursively(BinaryNode node, int data){
        if(node == null){
            node = new BinaryNode(data);
            System.out.println("The value : " + data + " inserted successfully");
            return node;
        }
        else{
            if(node.data > data) node.left = insertNodeRecursively(node.left, data);
            else node.right = insertNodeRecursively(node.right, data);
        }
        node.height = 1 + Math.max(node.getHeight(node.left), node.getHeight(node.right));
        int balance = getBalance(node);

        if(balance > 1 && data < node.left.data){
           return rotateRight(node);
        }
        if(balance > 1 && data > node.left.data){
            node.left = rotateLeft(node);
            return rotateRight(node);
        }
        if(balance < -1 && data > node.right.data){
            return rotateLeft(node);
        }
        if(balance < -1 && data < node.right.data){
            node.right = rotateRight(node);
            return rotateLeft(node);
        }
        return node;
    }

    public void insertNode(int data){
        if(this.root == null){
            this.root = new BinaryNode(data);
            System.out.println("The value " + data + " inserted successfully in the root node"); // O(1)
            return;
        }
        this.root = insertNodeRecursively(this.root, data);
    }

    public BinaryNode minimumNodeSuccessor(BinaryNode node){
        if(node.left == null) return node;
        else return minimumNodeSuccessor(node.left);
    }

    public BinaryNode deleteRecursively(BinaryNode node, int data){
        if(node == null){
            System.out.println("Either the root is null or the value doesn't exist");
            return null;
        }
        else{
            if(data > node.data) node.right = deleteRecursively(node.right, data);
            else if(data < node.data) node.left = deleteRecursively(node.left, data);
            else{
                if(node.left != null && node.right != null){
                    node.data = minimumNodeSuccessor(node.right).data;
                    node.right = deleteRecursively(node.right, node.data);
                }
                else if(node.left != null){
                    node.data = node.left.data;
                    node = node.left;
                }
                else if(node.right != null){
                    node.data = node.right.data;
                    node = node.right;
                }
                else{
                    node = null;
                }
            }
        }

        if(node == null) return node;

        int balance = getBalance(node);
        if(balance > 1 && getBalance(node.left) >= 0){
            return rotateRight(node);
        }
        if(balance > 1 && getBalance(node.left) < 0){
            node.left = rotateLeft(node);
            return rotateRight(node);
        }
        if(balance < -1 && getBalance(node.right) <= 0){
            return rotateLeft(node);
        }
        if(balance < -1 && getBalance(node.left) > 0){
            node.right = rotateRight(node);
            return rotateLeft(node);
        }
        return node;
    }

    public void deleteGivenNode(int value){
        this.root = deleteRecursively(this.root, value);
    }

    public void deleteAVLTree(){
        this.root = null;
        System.out.println("The AVL is deleted");
    }

    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        System.out.println();
        System.out.println("Insert Node");
        avlTree.insertNode(20);
        avlTree.insertNode(30);
        avlTree.insertNode(40);
        avlTree.insertNode(50);
        avlTree.insertNode(60);
        avlTree.insertNode(70);
        avlTree.insertNode(80);
        avlTree.insertNode(90);
        avlTree.insertNode(100);
        avlTree.insertNode(110);

        System.out.println("PreOrder Traversal");
        avlTree.preOrder(avlTree.root);

        System.out.println();
        System.out.println("InOrder Traversal");
        avlTree.inOrder(avlTree.root);

        System.out.println();
        System.out.println("Post Traversal");
        avlTree.postOrder(avlTree.root);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        avlTree.levelOrder();

//        PreOrder Traversal
//        70 -> 50 -> 30 -> 20 -> 40 -> 60 -> 90 -> 80 -> 100 ->
//        InOrder Traversal
//        20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> 90 -> 100 ->
//        Post Traversal
//        20 -> 40 -> 30 -> 60 -> 50 -> 80 -> 100 -> 90 -> 70 ->
//        LevelOrder Traversal
//        70 -> 50 -> 90 -> 30 -> 60 -> 80 -> 100 -> 20 -> 40 ->

        // Using AVL Tree logic
//        PreOrder Traversal
//        50 -> 30 -> 20 -> 40 -> 70 -> 60 -> 90 -> 80 -> 100 ->
//        InOrder Traversal
//        20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> 90 -> 100 ->
//        Post Traversal
//        20 -> 40 -> 30 -> 60 -> 80 -> 100 -> 90 -> 70 -> 50 ->
//        LevelOrder Traversal
//        50 -> 30 -> 70 -> 20 -> 40 -> 60 -> 90 -> 80 -> 100 ->

        System.out.println();
        System.out.println("Search Node");
        avlTree.searchNode(avlTree.root,70);

        System.out.println();
        System.out.println("Search Node");
        avlTree.searchNode(avlTree.root,30);

        System.out.println();
        System.out.println("Search Node");
        avlTree.searchNode(avlTree.root,110);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        avlTree.levelOrder();
    }
    
}
