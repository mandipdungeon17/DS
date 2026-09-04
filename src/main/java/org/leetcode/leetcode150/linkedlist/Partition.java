package org.leetcode.leetcode150.linkedlist;
//https://leetcode.com/problems/partition-list/description/?source=submission-noac
public class Partition {
//    public ListNode partition(ListNode head, int x) {
//        if(head == null) return head;
//        ListNode res = head;
//        ListNode current = head;
//        ListNode prev = head;
//        boolean flag = false;
//        while(head != null){
//            if(head.val == x){
//                flag = true;
//                prev = head;
//                head = head.next;
//                continue;
//            }
//            if(flag){
//                if(head.val < x){
//                    if(current.val >= x){
//                        prev.next = head.next;
//                        head.next = current;
//                        res = head;
//                        head = prev.next;
//                        current = res;
//                    } else {
//                        while (current.next.val <= x) {
//                            current = current.next;
//                        }
//                        prev.next = head.next;
//                        head.next = current.next;
//                        current.next = head;
//                        head = prev.next;
//                    }
//                    continue;
//                }
//            }
//            prev = head;
//            head = prev.next;
//
//        }
//        return res;
//    }

    //Time Complexity O(n) and Space Complexity O(n). It took 0ms.
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode min = new ListNode(0, null);
        ListNode minTail = min;
        ListNode max = new ListNode(0, null);
        ListNode maxTail = max;

        while(head != null){
            if(head.val < x){
                minTail.next = new ListNode(head.val, null);
                minTail = minTail.next;
            } else {
                maxTail.next = new ListNode(head.val, null);
                maxTail = maxTail.next;
            }
            head = head.next;
        }
        minTail.next = max.next;
        return min.next;
    }

    public static void main(String[] args) {
        Partition partition = new Partition();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        head = partition.partition(head, 3);
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
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
