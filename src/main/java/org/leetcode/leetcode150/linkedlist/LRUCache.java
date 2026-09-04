package org.leetcode.leetcode150.linkedlist;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/lru-cache/?envType=study-plan-v2&envId=top-interview-150
//Time Complexity O(1) for get and put operations, Space Complexity O(n). It took 42ms.
public class LRUCache {
    private final Map<Integer, Node> map;
    Node head, tail;
    private final int size;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.size = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) return -1;

        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToFront(node);
            return;
        }
        if(map.size() == size){
            Node lru = this.tail.prev;
            removeNode(lru);
            map.remove(lru.key);
        }
        node = new Node(key, value);
        map.put(key, node);
        insertAfterHead(node);
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void insertAfterHead(Node node){
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        insertAfterHead(node);
    }

    static class Node {
        int key,val;
        Node next;
        Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);

        while (lruCache.head.next != lruCache.tail) {
            System.out.println("Key: " + lruCache.head.next.key + ", Value: " + lruCache.head.next.val);
            lruCache.head = lruCache.head.next;
        }
    }
}
