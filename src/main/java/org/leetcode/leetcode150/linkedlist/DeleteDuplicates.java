package org.leetcode.leetcode150.linkedlist;
//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/submissions/2088789871/?envType=study-plan-v2&envId=top-interview-150
public class DeleteDuplicates {

    //Time Complexity O(n) and Space Complexity O(n). It took 0ms.
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode tail = head.next;
        ListNode prev = head;
        ListNode res = new ListNode(0, null);
        ListNode unique = res;

        while(tail != null){
            if(prev.val == tail.val){
                while(tail!= null && prev.val == tail.val){
                    tail = tail.next;
                    prev = prev.next;
                }
                prev = prev.next;
                if(tail == null) break;
                tail = tail.next;
                continue;
            }
            unique.next = new ListNode(prev.val, null);
            unique = unique.next;
            prev = prev.next;
            tail = tail.next;
        }
        unique.next = new ListNode(prev.val, null);
        return res.next;
    }

    public static void main(String[] args) {
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        ListNode res = deleteDuplicates.deleteDuplicates(head);
        while(res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    static class ListNode {
        int val;
        DeleteDuplicates.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, DeleteDuplicates.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
