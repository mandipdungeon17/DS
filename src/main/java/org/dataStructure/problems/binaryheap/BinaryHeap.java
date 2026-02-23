package org.dataStructure.problems.binaryheap;

public class BinaryHeap {

    int[] arr;
    int sizeOfHeap;

    BinaryHeap(int size){
        this.arr = new int[size+1];
        this.sizeOfHeap = 0;
    }

    public boolean isEmpty(){
        return this.sizeOfHeap == 0;
    }

    public int peek(){
        return this.arr[1];
    }

    public void heapifyBottomToTop(int index, String heapType){
        int parentIndex = index/2;
        if(index <= 1) return;
        if(heapType.equals("Min")){
            if(this.arr[parentIndex] > this.arr[index]){
                int temp = this.arr[index];
                this.arr[index] = this.arr[parentIndex];
                this.arr[parentIndex] = temp;
            }
        }
        else{
            if(this.arr[parentIndex] < this.arr[index]){
                int temp = this.arr[index];
                this.arr[index] = this.arr[parentIndex];
                this.arr[parentIndex] = temp;
            }
        }
        heapifyBottomToTop(parentIndex, heapType);
    }

    public void heapifyTopToBottom(int index, String heapType){
        int left = index*2;
        int right = index*2 +1;
        int swapChild;
        if(this.sizeOfHeap < left) return;

        if(heapType.equals("Max")){
            if(this.sizeOfHeap == left){
                if(this.arr[left] > this.arr[index]){
                    int temp = this.arr[index];
                    this.arr[index] = this.arr[left];
                    this.arr[left] = temp;
                }
                return;
            }
            else{
                if(this.arr[left] > this.arr[right]) swapChild = left;
                else swapChild = right;
                if(this.arr[swapChild] > this.arr[index]){
                    int temp = this.arr[index];
                    this.arr[index] = this.arr[swapChild];
                    this.arr[swapChild] = temp;
                }
            }
        }
        else{
            if(this.sizeOfHeap == left){
                if(this.arr[left] < this.arr[index]){
                    int temp = this.arr[index];
                    this.arr[index] = this.arr[left];
                    this.arr[left] = temp;
                }
                return;
            }
            else{
                if(this.arr[left] < this.arr[right]) swapChild = left;
                else swapChild = right;
                if(this.arr[swapChild] < this.arr[index]){
                    int temp = this.arr[index];
                    this.arr[index] = this.arr[swapChild];
                    this.arr[swapChild] = temp;
                }
            }
        }
        heapifyTopToBottom(swapChild, heapType);
    }
    public void insert(int value, String heapType){
        this.arr[++this.sizeOfHeap] = value;
        heapifyBottomToTop(this.sizeOfHeap, heapType);
    }

    public int extractNode(String heapType){
        if(isEmpty()) return -1;
        else{
            int extractedNode = this.arr[1];
            this.arr[1] = this.arr[this.sizeOfHeap--];
            heapifyTopToBottom(1, heapType);
            return extractedNode;
        }
    }

    public void levelOrderTraversal(){
        for(int i=1; i<=this.sizeOfHeap; i++){
            System.out.print(this.arr[i] + " -> ");
        }
    }

    public void deleteTree(){
        this.arr = null;
    }

    public static void main(String[] args) {
        BinaryHeap binaryHeapMin = new BinaryHeap(5);
        binaryHeapMin.insert(5, "Min");
        binaryHeapMin.insert(15,"Min");
        binaryHeapMin.insert(10,"Min");
        binaryHeapMin.insert(7,"Min");
        binaryHeapMin.insert(1,"Min");
        System.out.println("Min Value : " + binaryHeapMin.peek());
        binaryHeapMin.levelOrderTraversal();
        System.out.println();
        System.out.println("Extracted Node : " + binaryHeapMin.extractNode("Min"));
        binaryHeapMin.levelOrderTraversal();
        System.out.println();

        BinaryHeap binaryHeapMax = new BinaryHeap(5);
        binaryHeapMax.insert(5, "Max");
        binaryHeapMax.insert(15,"Max");
        binaryHeapMax.insert(10,"Max");
        binaryHeapMax.insert(7,"Max");
        binaryHeapMax.insert(1,"Max");
        System.out.println("Max Value : " + binaryHeapMax.peek());
        binaryHeapMax.levelOrderTraversal();
        System.out.println();
        System.out.println("Extracted Node : " + binaryHeapMax.extractNode("Max"));
        binaryHeapMax.levelOrderTraversal();
        System.out.println();
    }
}
