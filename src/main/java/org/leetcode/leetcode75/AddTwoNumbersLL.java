package org.leetcode.leetcode75;

public class AddTwoNumbersLL {
    //Time complexity: O(n) and Space complexity: O(1)
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode resultNode = new ListNode(-1);;
//        ListNode resultNodeDummy = resultNode;
//        int sum = 0;
//        while(l1!= null && l2!=null){
//            sum+= l1.val + l2.val;
//            if(sum > 9){
//                resultNode.next = new ListNode(sum-10);
//                resultNode = resultNode.next;
//                sum = 1;
//            }
//            else{
//                resultNode.next = new ListNode(sum);
//                resultNode = resultNode.next;
//                sum = 0;
//            }
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        if(l1!= null) {
//            while (l1 != null) {
//                sum += l1.val;
//                if (sum > 9) {
//                    resultNode.next = new ListNode(sum-10);
//                    resultNode = resultNode.next;
//                    sum = 1;
//                } else {
//                    resultNode.next = new ListNode(sum);
//                    resultNode = resultNode.next;
//                    sum = 0;
//                }
//                l1 = l1.next;
//            }
//        }
//        else{
//            while (l2 != null) {
//                sum += l2.val;
//                if (sum > 9) {
//                    resultNode.next = new ListNode(sum-10);
//                    resultNode = resultNode.next;
//                    sum = 1;
//                } else {
//                    resultNode.next = new ListNode(sum);
//                    resultNode = resultNode.next;
//                    sum = 0;
//                }
//                l2 = l2.next;
//            }
//        }
//        if(sum == 1){
//            resultNode.next = new ListNode(sum);
//        }
//        return resultNodeDummy.next;
//    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(-1);;
        ListNode resultNodeDummy = resultNode;
        int sum = 0;
        while(l1!= null || l2!=null){
            if(l1 != null){
                sum+=l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum+=l2.val;
                l2 = l2.next;
            }
            resultNode.next = new ListNode(sum%10);
            resultNode = resultNode.next;
            sum/=10;
        }

        if(sum == 1){
            resultNode.next = new ListNode(sum);
        }
        return resultNodeDummy.next;
    }

    public static void main(String[] args) {
        AddTwoNumbersLL addTwoNumbersLL = new AddTwoNumbersLL();
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(9);
//        ListNode l1 = new ListNode(0);
//        ListNode l2 = new ListNode(0);


        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListNode node = addTwoNumbersLL.addTwoNumbers(l1, l2);
        while(node!=null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}
