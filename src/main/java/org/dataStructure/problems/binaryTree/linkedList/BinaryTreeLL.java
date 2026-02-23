package org.dataStructure.problems.binaryTree.linkedList;

import org.dataStructure.problems.linkedList.Node;

import java.util.LinkedList;
import java.util.Queue;

// Binary Tree using LinkedList.
// Binary Tree is a non-linear data structure where each node can have at most 2 children.
// Binary Tree is a recursive data structure.
// Binary Tree is a collection of nodes where each node has a value and a reference to two other nodes.
// Binary Tree is used to implement binary search trees and binary heaps.
// Binary Tree is used to implement expression parsing, expression evaluation, and expression tree.
// Binary Tree is used to implement the file system.
// Binary Tree is used to implement the huffman coding algorithm.
// Binary Tree is used to implement the A* algorithm.
// Binary Tree is used to implement the decision tree.
// Binary Tree is used to implement the game tree.
// Binary Tree is used to implement the trie.
// Binary Tree is used to implement the radix tree.
// Binary Tree is used to implement the suffix tree.
// Binary Tree is used to implement the binary space partition.
// BFS (Breadth-First Search) and DFS (Depth-First Search) are the two main algorithms to traverse the tree.
public class BinaryTreeLL {

    BinaryNode root;
    Node<BinaryNode> head;
    Node<BinaryNode> tail;
    int size = 0;
    BinaryTreeLL(BinaryNode node){
        this.root = node;
    }

    /* DFS stands for Depth-First Search. It is an algorithm for traversing or searching tree or graph data structures.
    The algorithm starts at the root (selecting some arbitrary node as the root in the case of a graph)
    and explores as far as possible along each branch before backtracking.
    */

    // PreOrder Traversal (Root, Left, Right); Time Complexity - O(n) & Space Complexity - O(n)
    public void preOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null) {
            System.out.print(node.data + " -> "); // O(1)
            preOrder(node.left); // O(n/2)
            preOrder(node.right); // O(n/2)
        }
    }

    // InOrder Traversal (Left, Root, Right); Time Complexity - O(n) & Space Complexity - O(n)
    public void inOrder(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null) {
            inOrder(node.left); // O(n/2)
            System.out.print(node.data + " -> "); // O(1)
            inOrder(node.right); // O(n/2)
        }
    }

    // PostOrder Traversal (Left, Right, Root); Time Complexity - O(n) & Space Complexity - O(n)
    public void postTraversal(BinaryNode node){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        if(node != null){
            postTraversal(node.left); // O(n/2)
            postTraversal(node.right); // O(n/2)
            System.out.print(node.data + " -> "); // O(1)
        }
    }

    /* BFS stands for Breadth-First Search. It is an algorithm for traversing or searching tree or graph data structures.
    It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a 'search key')
    and explores the neighbor nodes at the present depth prior to moving on to nodes at the next depth level.
    */
    // Level Order Traversal (BFS); Time Complexity - O(n) & Space Complexity - O(n)
    public void levelOrderTraversal(){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>(); // O(1)
        queue.add(this.root); // O(1)

        while(!queue.isEmpty()){ // O(n)
            BinaryNode presentNode = queue.remove(); // O(1)
            System.out.print(presentNode.data + " -> "); // O(1)

            if(presentNode.left != null) queue.add(presentNode.left); // O(1)
            if(presentNode.right != null) queue.add(presentNode.right); // O(1)
        }
    }

    // Search Node. Time Complexity - O(n) & Space Complexity - O(n). Here we are using BFS to search the node  because it is the best way to search the node in the tree as it is faster than DFS.
    public void searchNode(String value){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(this.root);

        while(!queue.isEmpty()){
            BinaryNode binaryNode = queue.remove();
            if(value.equals(binaryNode.data)) {
                System.out.println("The value is found in tree " + value);
                return;
            }
            else{
                if(binaryNode.left != null) queue.add(binaryNode.left);
                if(binaryNode.right != null) queue.add(binaryNode.right);
            }
        }
        System.out.println("The value is not found in tree " + value);

    }

    public void levelOrderTraversalSelf(){
        if(this.root == null){
            System.out.println("The root is null");
            return;
        }
        insertNodeInQueue(this.root);
        while(head != null){
            BinaryNode presentNode = removeNodeFromQueue();
            System.out.print(presentNode.data + " -> ");
            if(presentNode.left != null){
                insertNodeInQueue(presentNode.left);
            }
            if(presentNode.right != null){
                insertNodeInQueue(presentNode.right);
            }
        }

    }

    public void insertNodeInQueue(BinaryNode binaryNode){
        Node<BinaryNode> node = new Node<>(binaryNode);
        if(this.head == null){
            this.head = this.tail = node;
        }
        else{
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public BinaryNode removeNodeFromQueue(){
        BinaryNode node = head.data;
        this.head = head.next;
        if(this.head == null) this.tail = null;
        this.size--;
        return node;

    }

    // Insert Binary Tree. Time Complexity - O(n) & Space Complexity - O(n)
    public void insertBinaryTree(String value){
        BinaryNode newNode = new BinaryNode(value);
        if(this.root == null){ // O(1)
            this.root = newNode; // O(1)
            System.out.println("The value " + value + " inserted successfully in the root node"); // O(1)
            return;
        }
            Queue<BinaryNode> queue = new LinkedList<>(); // O(1)
            queue.add(this.root); // O(1)
            while(!queue.isEmpty()){ // O(n)
                BinaryNode node = queue.remove(); // O(1)
                if(node.left == null){  // O(1)
                    node.left = newNode; // O(1)
                    System.out.println("The value " + value + " inserted successfully in the left node"); // O(1)
                    return;
                }
                else if(node.right == null){ // O(1)
                    node.right = newNode; // O(1)
                    System.out.println("The value " + value + " inserted successfully in the right node"); // O(1)
                    return;
                }
                else{
                    queue.add(node.left); // O(1)
                    queue.add(node.right); // O(1)
                }
            }
    }

    // Find the deepest node in the tree. Time Complexity - O(n) & Space Complexity - O(n)
    public BinaryNode findDeepestNode(){
        Queue<BinaryNode> queue = new LinkedList<>(); // O(1)
        queue.add(this.root); // O(1)

        BinaryNode presentNode = null;
        while(!queue.isEmpty()){ // O(n)
            presentNode = queue.remove(); // O(1)

            if(presentNode.left != null) queue.add(presentNode.left); // O(1)
            if(presentNode.right != null) queue.add(presentNode.right); // O(1)
        }
        return presentNode;
    }

    // Delete the deepest node in the tree. Time Complexity - O(n) & Space Complexity - O(n)
    public void deleteDeepestNode(){
        Queue<BinaryNode> queue = new LinkedList<>(); // O(1)
        queue.add(this.root); // O(1)

        BinaryNode previousNode, presentNode = null;
        while(!queue.isEmpty()){ // O(n)
            previousNode = presentNode; // O(1)
            presentNode = queue.remove(); // O(1)
            if(presentNode.left == null){ // O(1)
                System.out.println("The previous node is : " + previousNode.data); // O(1)
                System.out.println("The node is deleted successfully : " + previousNode.right.data); // O(1)
                previousNode.right = null; // O(1)
                return;
            }
            else if(presentNode.right == null){ // O(1)
                System.out.println("The present node is : " + presentNode.data); // O(1)
                System.out.println("The node is deleted successfully : " + presentNode.left.data); // O(1)
                presentNode.left = null; // O(1)
                return; // O(1)
            }
            queue.add(presentNode.left); // O(1)
            queue.add(presentNode.right); // O(1)
        }
    }

    // Delete the given node in the tree. Time Complexity - O(n) & Space Complexity - O(n)
    public void deleteTheGivenNode(String value){
        Queue<BinaryNode> queue = new LinkedList<>(); // O(1)
        queue.add(this.root); // O(1)

        while (!queue.isEmpty()){ // O(n)
            BinaryNode node = queue.remove(); // O(1)
            if(node.data.equals(value)){ // O(1)
                node.data = findDeepestNode().data; // O(n)
                deleteDeepestNode(); // O(n)
                System.out.println("The Node is deleted successfully : " + value); // O(1)
                return; // O(1)
            }
            if(node.left != null) queue.add(node.left); // O(1)
            if(node.right != null) queue.add(node.right); // O(1)
        }
        System.out.println("The node " + value + " doesn't exist"); // O(1)
    }

    // Delete the entire node. Time Complexity - O(1) & Space Complexity - O(1)
    public void deleteBinaryTree(){
        this.root = null; // O(1)
        System.out.println("The entire BT is deleted");
    }

    public static void main(String[] args) {
        BinaryNode n1 = new BinaryNode("N1");
        BinaryNode n2 = new BinaryNode("N2");
        BinaryNode n3 = new BinaryNode("N3");
        BinaryNode n4 = new BinaryNode("N4");
        BinaryNode n5 = new BinaryNode("N5");
        BinaryNode n6 = new BinaryNode("N6");
        BinaryNode n7 = new BinaryNode("N7");
        BinaryNode n8 = new BinaryNode("N8");
        BinaryNode n9 = new BinaryNode("N9");
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;

        BinaryTreeLL binaryTreeLL = new BinaryTreeLL(n1);
        System.out.println("PreOrder Traversal");
        binaryTreeLL.preOrder(binaryTreeLL.root);

        System.out.println();
        System.out.println("InOrder Traversal");
        binaryTreeLL.inOrder(binaryTreeLL.root);

        System.out.println();
        System.out.println("Post Traversal");
        binaryTreeLL.postTraversal(binaryTreeLL.root);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        binaryTreeLL.levelOrderTraversal();

        /* PreOrder Traversal
            N1 -> N2 -> N4 -> N8 -> N9 -> N5 -> N3 -> N6 -> N7 ->
          InOrder Traversal
            N8 -> N4 -> N9 -> N2 -> N5 -> N1 -> N6 -> N3 -> N7 ->
          Post Traversal
            N8 -> N9 -> N4 -> N5 -> N2 -> N6 -> N7 -> N3 -> N1 ->
          LevelOrder Traversal
            N1 -> N2 -> N3 -> N4 -> N5 -> N6 -> N7 -> N8 -> N9 ->
         */

        System.out.println();
        System.out.println("LevelOrder Traversal Self");
        binaryTreeLL.levelOrderTraversalSelf();

        System.out.println();
        System.out.println("Search Node");
        binaryTreeLL.searchNode("N5");

        binaryTreeLL.root = null;

        System.out.println();
        System.out.println("Insert Node");
        binaryTreeLL.insertBinaryTree("N1");
        binaryTreeLL.insertBinaryTree("N2");
        binaryTreeLL.insertBinaryTree("N3");
        binaryTreeLL.insertBinaryTree("N4");
        binaryTreeLL.insertBinaryTree("N5");
        binaryTreeLL.insertBinaryTree("N6");
        binaryTreeLL.insertBinaryTree("N7");
        binaryTreeLL.insertBinaryTree("N8");

        System.out.println();
        System.out.println("LevelOrder Traversal");
        binaryTreeLL.levelOrderTraversal();

        System.out.println();
        System.out.println("Deepest Node");
        System.out.println("The deepest node is : " + binaryTreeLL.findDeepestNode().data);

        System.out.println();
        System.out.println("Delete Deepest Node");
        binaryTreeLL.deleteDeepestNode();

        System.out.println();
        System.out.println("Deepest Node");
        System.out.println("The deepest node is : " + binaryTreeLL.findDeepestNode().data);

        System.out.println();
        System.out.println("Delete Deepest Node");
        binaryTreeLL.deleteDeepestNode();

        System.out.println();
        System.out.println("Deepest Node");
        System.out.println("The deepest node is : " + binaryTreeLL.findDeepestNode().data);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        binaryTreeLL.levelOrderTraversal();

        System.out.println();
        System.out.println("Delete the given node");
        binaryTreeLL.deleteTheGivenNode("N4");

        System.out.println();
        System.out.println("LevelOrder Traversal");
        binaryTreeLL.levelOrderTraversal();

        System.out.println();
        System.out.println("Delete entire node");
        binaryTreeLL.deleteBinaryTree();

        System.out.println();
        System.out.println("LevelOrder Traversal");
        binaryTreeLL.levelOrderTraversal();




    }
}
