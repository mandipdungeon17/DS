package org.interview.bst.solution;

import java.util.LinkedList;
import java.util.Queue;

public class MinimalTreeSolution {
        public int data;
        public MinimalTreeSolution left;
        public MinimalTreeSolution right;
        public MinimalTreeSolution parent;
        public int size;

        public MinimalTreeSolution(int d) {
            data = d;
            size = 1;
        }

        public boolean isBST() {
            if (left != null) {
                return data < left.data || left.isBST();
            }

            if (right != null) {
                return data >= right.data || right.isBST();
            }
            return false;
        }

        public int height() {
            int leftHeight = left != null ? left.height() : 0;
            int rightHeight = right != null ? right.height() : 0;
            return 1 + Math.max(leftHeight, rightHeight);
        }

        private void setLeftChild(MinimalTreeSolution left) {
            this.left = left;
            if (left != null) {
                left.parent = this;
            }
        }

        private void setRightChild(MinimalTreeSolution right) {
            this.right = right;
            if (right != null) {
                right.parent = this;
            }
        }


        private static MinimalTreeSolution createMinimalBST(int arr[], int start, int end){
            if (end < start) {
                return null;
            }
            int mid = (start + end) / 2;
            MinimalTreeSolution n = new MinimalTreeSolution(arr[mid]);
            n.setLeftChild(createMinimalBST(arr, start, mid - 1));
            n.setRightChild(createMinimalBST(arr, mid + 1, end));
            return n;
        }

        public static MinimalTreeSolution createMinimalBST(int[] array) {
            MinimalTreeSolution minimalTreeSolution;
            minimalTreeSolution = createMinimalBST(array, 0, array.length - 1);
            assert minimalTreeSolution != null;
            System.out.println(minimalTreeSolution.height());
            return minimalTreeSolution;
        }

    public void levelOrder(){
        if(this.parent== null){
            System.out.println("The root is null");
            return;
        }
        Queue<MinimalTreeSolution> MinimalTreeSolutionQueue = new LinkedList<>();
        MinimalTreeSolutionQueue.add(this.parent);

        while(!MinimalTreeSolutionQueue.isEmpty()){
            MinimalTreeSolution MinimalTreeSolution = MinimalTreeSolutionQueue.remove();
            System.out.print(MinimalTreeSolution.data + " ->");
            if(MinimalTreeSolution.left != null) MinimalTreeSolutionQueue.add(MinimalTreeSolution.left);
            if(MinimalTreeSolution.right != null) MinimalTreeSolutionQueue.add(MinimalTreeSolution.right);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Insert Node");
        int[] arr = {10,20,30,40,50,60,70,80,90,100};
        createMinimalBST(arr);
//        MinimalTreeSolution.levelOrder();
    }
}
