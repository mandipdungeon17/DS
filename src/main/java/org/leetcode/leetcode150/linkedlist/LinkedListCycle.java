package org.leetcode.leetcode150.linkedlist;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/linked-list-cycle/?envType=study-plan-v2&envId=top-interview-150
public class LinkedListCycle {
    //Time Complexity O(n) and Space Complexity O(n). It took 7ms.
//    public boolean hasCycle(ListNode head) {
//        if(head == null) return false;
//        Map<ListNode, Integer> mp = new HashMap<>();
//        int index = 0;
//        while(head.next!=null){
//            mp.put(head, index++);
//            head = head.next;
//            if(mp.containsKey(head)) return true;
//        }
//        return false;
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle llc = new LinkedListCycle();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        System.out.println(llc.hasCycle(head));
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
