package org.leetcode.leetcode75.easy.linkedlist;

public class ReverseList {
    int val;
    ReverseList next;
    ReverseList() {}
    ReverseList(int val) { this.val = val; }
    ReverseList(int val, ReverseList next) { this.val = val; this.next = next; }
}
class ReverseListSolution {
    //Time complexity: O(n) and Space complexity: O(1)
    public ReverseList reverseList(ReverseList head) {
        ReverseList currentNode = head;
        ReverseList nextNode = head;
        ReverseList prevNode = null;
        while(nextNode != null){
            nextNode = nextNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        return prevNode;
    }

    public static void main(String[] args) {
        ReverseList head = new ReverseList(1);
        ReverseList second = new ReverseList(2);
        ReverseList third = new ReverseList(3);
        ReverseList fourth = new ReverseList(4);
        ReverseList fifth = new ReverseList(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        ReverseListSolution solution = new ReverseListSolution();
        ReverseList ans = solution.reverseList(head);
        while(ans != null){
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
