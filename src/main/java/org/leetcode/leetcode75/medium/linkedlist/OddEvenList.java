package org.leetcode.leetcode75.medium.linkedlist;

public class OddEvenList {
    int val;
    OddEvenList next;
    OddEvenList() {}
    OddEvenList(int val) { this.val = val; }
    OddEvenList(int val, OddEvenList next) { this.val = val; this.next = next; }
}
class OddEvenListSolution {
    //Time complexity: O(n) and Space complexity: O(n). It took 0 ms.
//    public OddEvenList oddEvenList(OddEvenList head) {
//        if(head == null) return null;
//        if(head.next == null) return head;
//        OddEvenList odd = head;
//        List<OddEvenList> evenList = new ArrayList<>();
//        while(odd.next!= null && odd.next.next != null){
//            evenList.add(odd.next);
//            odd.next = odd.next.next;
//            odd = odd.next;
//        }
//        if(odd.next != null){
//            evenList.add(odd.next);
//        }
//        int i=0;
//        while(i<evenList.size()){
//            odd.next = evenList.get(i);
//            odd = odd.next;
//            i++;
//        }
//        odd.next = null;
//        return head;
//    }

    //Time complexity: O(n) and Space complexity: O(1). It took 0 ms.
    public OddEvenList oddEvenList(OddEvenList head) {
        if(head == null) return null;
        if(head.next == null) return head;
        OddEvenList odd = new OddEvenList(-1);
        OddEvenList even = new OddEvenList(-1);
        OddEvenList oddDummy = odd;
        OddEvenList evenDummy = even;
        OddEvenList currentNode = head;
        int count = 1;
        while(currentNode != null){
            if(count%2 == 1){
                odd.next = currentNode;
                odd = odd.next;
            }
            else{
                even.next = currentNode;
                even = even.next;
            }
            count++;
            currentNode = currentNode.next;
        }
        even.next = null;
        odd.next = evenDummy.next;

        return oddDummy.next;
    }

    public static void main(String[] args) {
        OddEvenList head = new OddEvenList(1);
        OddEvenList second = new OddEvenList(2);
        OddEvenList third = new OddEvenList(3);
        OddEvenList fourth = new OddEvenList(4);
        OddEvenList fifth = new OddEvenList(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        OddEvenListSolution solution = new OddEvenListSolution();
        OddEvenList ans = solution.oddEvenList(head);
        while(ans != null){
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
