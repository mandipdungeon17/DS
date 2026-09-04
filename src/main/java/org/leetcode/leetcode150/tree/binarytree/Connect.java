package org.leetcode.leetcode150.tree.binarytree;

import org.leetcode.leetcode150.tree.Node;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/?envType=study-plan-v2&envId=top-interview-150
// Time Complexity O(n) and Space Complexity O(n). It took 1ms. I used level order traversal to connect the next pointers.
public class Connect {
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> node = new ArrayDeque<>();
        node.add(root);

        while (!node.isEmpty()) {
            int size = node.size();
            Node prevRoot = null;

            for (int i = 0; i < size; i++) {
                Node pRoot = node.remove();

                if (pRoot.left != null) {
                    node.add(pRoot.left);
                }
                if (pRoot.right != null) {
                    node.add(pRoot.right);
                }

                if (prevRoot != null) {
                    prevRoot.next = pRoot;
                }
                prevRoot = pRoot;
            }
            prevRoot.next = null;
        }
        return root;
    }

    public static void main(String[] args) {

        Connect connect = new Connect();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        connect.connect(root);

        System.out.println(root);
    }
}
