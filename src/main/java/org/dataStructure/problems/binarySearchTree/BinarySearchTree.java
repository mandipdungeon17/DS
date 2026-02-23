package org.dataStructure.problems.binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    BinaryNode root;

    public BinarySearchTree(){
        this.root = null;
    }

    public void insertNodeSelf(int value){
        BinaryNode newNode = new BinaryNode(value);
        if(this.root == null){
            this.root = newNode;
            System.out.println("The value " + value + " inserted successfully in the root node"); // O(1)
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(this.root);
        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();
            if(presentNode.value > value){
                if(presentNode.left == null){
                    presentNode.left = newNode;
                    System.out.println("The value " + value + " inserted successfully in the left node"); // O(1)
                    return;
                }
                else{
                    queue.add(presentNode.left);
                }
            }
            else{
                if(presentNode.right == null){
                    presentNode.right = newNode;
                    System.out.println("The value " + value + " inserted successfully in the right node"); // O(1)
                    return;
                }
                else{
                    queue.add(presentNode.right);
                }
            }

        }
    }

    public BinaryNode insertNodeRecursively(BinaryNode currentNode, int value){
        if(currentNode == null){
            currentNode = new BinaryNode(value);
            System.out.println("The value : " + value + " inserted successfully");
        }
        else{
            if(value <= currentNode.value){
              currentNode.left = insertNodeRecursively(currentNode.left, value);
            }
            else{
                currentNode.right = insertNodeRecursively(currentNode.right, value);
            }
        }
        return currentNode;
    }

    public void insertNode(int value){
        if(this.root == null){
            this.root = new BinaryNode(value);
            System.out.println("The value " + value + " inserted successfully in the root node"); // O(1)
            return;
        }
       insertNodeRecursively(this.root, value);
    }

    public void preOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null) {
            System.out.print(node.value + " -> "); // O(1)
            preOrder(node.left); // O(n/2)
            preOrder(node.right); // O(n/2)
        }
    }

    public void inOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null){
            inOrder(node.left);
            System.out.print(node.value + " -> "); // O(1)
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
            System.out.print(node.value + " -> "); // O(1)
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
            System.out.print(presentNode.value + " -> "); // O(1)

            if(presentNode.left != null) queue.add(presentNode.left);
            if(presentNode.right != null) queue.add(presentNode.right);
        }
    }

    public void searchNodeSelf(int value){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(this.root.value == value){
            System.out.println("The value is found in the root");
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(this.root);

        while(!queue.isEmpty()){
            BinaryNode presentNode = queue.remove();
            if(presentNode.value == value){
                System.out.println("The value is found in the BST " + value);
                return;
            }
            if(presentNode.left != null && presentNode.value >= value) queue.add(presentNode.left);
            else if(presentNode.right != null) queue.add(presentNode.right);
        }
        System.out.println("The value is not found in BST " + value);
    }

    public void searchNode(BinaryNode node, int value){
        if(this.root == null || node == null){
            System.out.println("Either root is null or the value doesn't exist");
        }
        else if(node.value == value){
            System.out.println("The value is found in the BST " + value);
        }
        else if(node.value > value) searchNode(node.left, value);
        else searchNode(node.right, value);
    }

    public BinaryNode minimumNodeSuccessor(BinaryNode node){
        if(node == null){
            return null;
        }
        else{
            if(node.left != null){
                node = minimumNodeSuccessor(node.left);
            }
        }
        return node;
    }

    public BinaryNode deleteNode(BinaryNode node, int value){
        if(node == null){
            System.out.println("Either the root is null or the value doesn't exist");
        }
        else{
            if(node.value > value){
                node.left = deleteNode(node.left, value);
            }
            else if(node.value < value){
                node.right = deleteNode(node.right, value);
            }
            else{
                if(node.left != null && node.right != null){
                    BinaryNode successor = minimumNodeSuccessor(node.right);
                    node.value = successor.value;
                    node.right = deleteNode(node.right, successor.value);
                }
                else if(node.left != null){
                    node.value = node.left.value;
                    node = node.left;
                }
                else if(node.right != null){
                    node.value = node.right.value;
                    node = node.right;
                }
                else{
                    node = null;
                }
            }
        }
        return node;
    }

    public void deleteBST(){
        this.root = null;
        System.out.println("The BST is deleted");
    }

    public static void main(String[] args) {
        BinarySearchTree searchTree = new BinarySearchTree();
        System.out.println();
        System.out.println("Insert Node");
        searchTree.insertNode(70);
        searchTree.insertNode(50);
        searchTree.insertNode(90);
        searchTree.insertNode(30);
        searchTree.insertNode(60);
        searchTree.insertNode(80);
        searchTree.insertNode(100);
        searchTree.insertNode(20);
        searchTree.insertNode(40);

        System.out.println("PreOrder Traversal");
        searchTree.preOrder(searchTree.root);

        System.out.println();
        System.out.println("InOrder Traversal");
        searchTree.inOrder(searchTree.root);

        System.out.println();
        System.out.println("Post Traversal");
        searchTree.postOrder(searchTree.root);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();

//        PreOrder Traversal
//        70 -> 50 -> 30 -> 20 -> 40 -> 60 -> 90 -> 80 -> 100 ->
//        InOrder Traversal
//        20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80 -> 90 -> 100 ->
//        Post Traversal
//        20 -> 40 -> 30 -> 60 -> 50 -> 80 -> 100 -> 90 -> 70 ->
//        LevelOrder Traversal
//        70 -> 50 -> 90 -> 30 -> 60 -> 80 -> 100 -> 20 -> 40 ->

        System.out.println();
        System.out.println("Search Node");
        searchTree.searchNode(searchTree.root,70);

        System.out.println();
        System.out.println("Search Node");
        searchTree.searchNode(searchTree.root,30);

        System.out.println();
        System.out.println("Search Node");
        searchTree.searchNode(searchTree.root,110);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();

        // Deleting root node
        System.out.println();
        System.out.println("Delete the given node");
        searchTree.deleteNode(searchTree.root, 70);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();



        //Deleting Leaf node
        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();

        // Deleting root node
        System.out.println();
        System.out.println("Delete the given node");
        searchTree.deleteNode(searchTree.root, 20);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();


        //Deleting middle node
        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();

        // Deleting root node
        System.out.println();
        System.out.println("Delete the given node");
        searchTree.deleteNode(searchTree.root, 70);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();


        System.out.println();
        System.out.println("Delete entire node");
        searchTree.deleteBST();

        System.out.println();
        System.out.println("LevelOrder Traversal");
        searchTree.levelOrder();


    }
}
