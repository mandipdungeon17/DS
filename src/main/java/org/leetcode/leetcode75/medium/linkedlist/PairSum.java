package org.leetcode.leetcode75.medium.linkedlist;

public class PairSum {
    int val;
    PairSum next;
    PairSum() {}
    PairSum(int val) { this.val = val; }
    PairSum(int val, PairSum next) { this.val = val; this.next = next; }
}
class PairSumSolution {
    //Time complexity: O(n) and Space complexity: O(n). It took 9 ms.
//    public int pairSum(PairSum head) {
//        PairSum current = head;
//        List<Integer> list = new ArrayList<>();
//        while(current!=null){
//            list.add(current.val);
//            current = current.next;
//        }
//        int max = 0;
//        int start=0;
//        int end = list.size()-1;
//
//        while(start < end){
//            int sum = list.get(start) + list.get(end);
//            max = Math.max(sum, max);
//            start++;
//            end--;
//        }
//        return max;
//    }

    //Time complexity: O(n) and Space complexity: O(1). It took 4 ms.
    public int pairSum(PairSum head) {
        int max = 0;
        PairSum fast=head;
        PairSum slow=head;
        PairSum prev = null;
        PairSum curr = head;
        while(fast!= null && fast.next!= null){
            slow=slow.next;
            fast = fast.next.next;
            curr.next = prev;
            prev = curr;
            curr = slow;
        }
        while(slow != null && prev != null){
            max = Math.max(prev.val+ slow.val, max);
            slow = slow.next;
            prev = prev.next;
        }
        return max;
    }

    public static void main(String[] args) {
        PairSum head = new PairSum(1);
        PairSum second = new PairSum(2);
        PairSum third = new PairSum(3);
        PairSum fourth = new PairSum(4);
        PairSum fifth = new PairSum(5);
        PairSum sixth = new PairSum(6);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        PairSumSolution solution = new PairSumSolution();
        int ans = solution.pairSum(head);
        System.out.println("Pair Sum: " + ans);
    }
}
