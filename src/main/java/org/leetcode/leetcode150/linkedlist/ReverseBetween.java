package org.leetcode.leetcode150.linkedlist;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/reverse-linked-list-ii/?envType=study-plan-v2&envId=top-interview-150
public class ReverseBetween {

    //Time Complexity O(n) and Space Complexity O(n). It took 0ms.
    public ListNode reverseBetween(ListNode head, int left, int right) {
        List<ListNode> list = new ArrayList<>();
        ListNode tail = head;
        int pos = 1;
        while(tail != null){
            if(pos == left){
                while(pos != right){
                    list.add(tail);
                    tail = tail.next;
                    pos++;
                }
                list.add(tail);
                break;
            }
            pos++;
            tail = tail.next;
        }
        tail = head;
        pos = 0;
        int size = list.size()-1;
        ListNode res = new ListNode(0, null);
        ListNode resTail = res;

        while(tail != null && tail.next != null) {
            if(pos+1 == left){
                while(size > -1){
                    resTail.next = new ListNode(list.get(size).val, null);
                    resTail = resTail.next;
                    tail = tail.next;
                    size--;
                    pos++;
                }
            } else{
                resTail.next = new ListNode(tail.val, null);
                resTail = resTail.next;
                tail = tail.next;
                pos++;
            }
        }
        if(tail != null) resTail.next = new ListNode(tail.val, null);

        return res.next;
    }

    public static void main(String[] args) {
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode head = new ListNode(1);
        head.next = new ReverseBetween.ListNode(2);
        head.next.next = new ReverseBetween.ListNode(3);
        head.next.next.next = new ReverseBetween.ListNode(4);
        head.next.next.next.next = new ReverseBetween.ListNode(5);
        ReverseBetween.ListNode listNode = reverseBetween.reverseBetween(head, 2, 4);
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
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
