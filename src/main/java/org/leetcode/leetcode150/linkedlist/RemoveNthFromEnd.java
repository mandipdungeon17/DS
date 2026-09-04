package org.leetcode.leetcode150.linkedlist;

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150
public class RemoveNthFromEnd {
    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null) return null;

        ListNode tail = head;
        ListNode prev = head;;
        while(tail != null){
            if(n == -1){
                prev = prev.next;
            } else n--;
            tail = tail.next;
        }
        if(n > -1){
            return head.next;
        }
        if(prev.next != null && prev.next.next != null)
            prev.next = prev.next.next;
        else prev.next = null;
        return head;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = removeNthFromEnd.removeNthFromEnd(head, 2);
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    static class ListNode {
        int val;
        RemoveNthFromEnd.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, RemoveNthFromEnd.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
