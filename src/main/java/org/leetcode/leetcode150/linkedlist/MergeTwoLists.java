package org.leetcode.leetcode150.linkedlist;

public class MergeTwoLists {
    //Time Complexity O(n) and Space Complexity O(1). It took 0ms.
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        else if(list2 == null) return list1;
        else{
            ListNode res = new ListNode(0);
            ListNode tail = res;
            while(list1 != null && list2 != null){
                if(list1.val == list2.val){
                    tail.next = new ListNode(list1.val, null);
                    tail.next.next = new ListNode(list2.val, null);
                    tail = tail.next.next;
                    list1 = list1.next;
                    list2 = list2.next;
                } else if(list1.val > list2.val){
                    tail.next = new ListNode(list2.val, null);
                    tail = tail.next;
                    list2 = list2.next;
                } else {
                    tail.next = new ListNode(list1.val, null);
                    tail = tail.next;
                    list1 = list1.next;
                }
            }
            if(list1 != null){
                tail.next = list1;
            } else if(list2 != null){
                tail.next = list2;
            }
            return res.next;
        }
    }

    public static void main(String[] args) {
        MergeTwoLists mtl = new MergeTwoLists();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode mergedList = mtl.mergeTwoLists(list1, list2);
        while (mergedList != null) {
            System.out.println(mergedList.val);
            mergedList = mergedList.next;
        }
    }

    static class ListNode {
        int val;
        MergeTwoLists.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, MergeTwoLists.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
