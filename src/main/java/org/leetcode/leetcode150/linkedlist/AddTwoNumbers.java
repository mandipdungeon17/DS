package org.leetcode.leetcode150.linkedlist;

public class AddTwoNumbers {
    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode res = null;
//        ListNode tail = res;
//        int carry = 0;
//        while(l1!= null && l2!= null){
//            int val = l1.val + l2.val + carry;
//            if(val > 9){
//                val = val%10;
//                carry = 1;
//            } else {
//                carry = 0;
//            }
//            if(res != null) {
//                tail.next = new ListNode(val, null);
//                tail = tail.next;
//            }
//            else {
//                res = new ListNode(val, null);
//                tail = res;
//            }
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        while(l1 != null){
//            int val = l1.val + carry;
//            if(val > 9){
//                val = val%10;
//                carry = 1;
//            } else {
//                carry = 0;
//            }
//            if(res != null) {
//                tail.next = new ListNode(val, null);
//                tail = tail.next;
//            }
//            else {
//                res = new ListNode(val, null);
//                tail = res;
//            }
//            l1 = l1.next;
//        }
//        while(l2 != null){
//            int val = l2.val + carry;
//            if(val > 9){
//                val = val%10;
//                carry = 1;
//            } else {
//                carry = 0;
//            }
//            if(res != null) {
//                tail.next = new ListNode(val, null);
//                tail = tail.next;
//            }
//            else {
//                res = new ListNode(val, null);
//                tail = res;
//            }
//            l2 = l2.next;
//        }
//        if(carry != 0){
//            tail.next = new ListNode(carry, null);
//            tail = tail.next;
//        }
//        return res;
//    }

    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            tail.next = new ListNode(sum % 10, null);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);

        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(6);
        head1.next.next = new ListNode(4);

        ListNode res = addTwoNumbers.addTwoNumbers(head,head1);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
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
