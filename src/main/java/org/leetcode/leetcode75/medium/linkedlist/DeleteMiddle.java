    package org.leetcode.leetcode75.medium.linkedlist;

    public class DeleteMiddle {
              int val;
              DeleteMiddle next;
              DeleteMiddle() {}
              DeleteMiddle(int val) { this.val = val; }
              DeleteMiddle(int val, DeleteMiddle next) { this.val = val; this.next = next; }
    }
     class Solution {
//        public DeleteMiddle deleteMiddle(DeleteMiddle head) {
//            if(head.next == null) return null;
//            DeleteMiddle currentNode = head;
//            int size = 0;
//            while(currentNode != null){
//                size++;
//                currentNode = currentNode.next;
//            }
//            int mid = (int) Math.floor(size/2.0);
//            currentNode = head;
//            DeleteMiddle prevNode = null;
//            while(currentNode != null){
//                if(mid == 0){
//                    prevNode.next = currentNode.next;
//                    break;
//                }
//                prevNode = currentNode;
//                currentNode = currentNode.next;
//                mid--;
//            }
//            return head;
//        }

         //Time complexity: O(n) and Space complexity: O(1)
         public DeleteMiddle deleteMiddle(DeleteMiddle head) {
             if(head == null || head.next == null) return null;
             DeleteMiddle fast = head;
             DeleteMiddle slow = head;
             DeleteMiddle prev = null;
             while(fast!=null && fast.next!=null){
                 prev = slow;
                 slow = slow.next;
                 fast = fast.next.next;
             }
             prev.next = slow.next;
             return head;
         }

         public static void main(String[] args) {
            DeleteMiddle head = new DeleteMiddle(1);
            DeleteMiddle second = new DeleteMiddle(2);
            DeleteMiddle third = new DeleteMiddle(3);
            DeleteMiddle fourth = new DeleteMiddle(4);
            DeleteMiddle fifth = new DeleteMiddle(5);
            head.next = second;
            second.next = third;
            third.next = fourth;
            fourth.next = fifth;
            Solution solution = new Solution();
            DeleteMiddle ans = solution.deleteMiddle(head);
            while(ans != null){
                System.out.println(ans.val);
                ans = ans.next;
            }
         }
    }
