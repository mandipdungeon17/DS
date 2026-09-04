package org.leetcode.leetcode150.linkedlist;

public class RotateRight {

    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode tail = head;
        int size = 1;
        while(tail.next != null){
            size++;
            tail = tail.next;
        }
        k = k % size;
        if(k == 0) return head;
        k = size - k;

        ListNode prev = head;
        size = 1;

        while(k != size){
            size++;
            prev = prev.next;
        }

        tail.next = head;
        head = prev.next;
        prev.next = null;

        return head;
    }

    public static void main(String[] args) {
        RotateRight rotateRight = new RotateRight();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode listNode = rotateRight.rotateRight(head, 5);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    static class ListNode {
        int val;
        RotateRight.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, RotateRight.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
