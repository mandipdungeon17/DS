package org.dataStructure.problems.stack.question;

public class ThreeStacksInOneArray {
    int numberOfStacks = 3;
    int stackCapacity;
    int[] values;
    int[] sizes;
    public ThreeStacksInOneArray(int stackSize){
        this.stackCapacity = stackSize;
        this.values = new int[stackSize*3];
        this.sizes = new int[this.numberOfStacks];
    }
    public boolean isFull(int stackIndex){
        return this.sizes[stackIndex] == this.stackCapacity;
    }
    public boolean isEmpty(int stackIndex){
        return this.sizes[stackIndex] == 0;
    }

    public int topOfIndex(int stackIndex){
        /* 0*3+1-1 = 0; 0*3+2-1 = 1; 1*3+1-1 = 3; 2*3+3-1 = 8; Max size = 3, Max stackCapacity = 3
        * Total Array size = 9 */
        return (stackIndex * this.stackCapacity) + this.sizes[stackIndex] - 1;
    }
    public void push(int stackIndex, int data){
        if(isFull(stackIndex)) System.out.println("The Stack is Full");
        else {
            this.sizes[stackIndex]++;
            this.values[this.topOfIndex(stackIndex)] = data;
        }
    }
    public void pop(int stackIndex){
        if(isEmpty(stackIndex)) System.out.println("The Stack is Empty");
        else{
            int topIndex = this.topOfIndex(stackIndex);
            int value = this.values[topIndex];
            this.values[topIndex] = Integer.MIN_VALUE;
            this.sizes[stackIndex]--;
            System.out.println("The Stack for stackIndex : " + stackIndex + " is deleted successfully with value : " + value);
        }
    }

    public void peek(int stackIndex){
        if(isEmpty(stackIndex)) System.out.println("The Stack is Empty");
        else{
            System.out.println("The Top Stack of stackIndex :" + stackIndex + " is " + this.values[this.topOfIndex(stackIndex)]);
        }
    }

    public static void main(String[] args) {
        ThreeStacksInOneArray threeStacksInOneArray = new ThreeStacksInOneArray(3);
        threeStacksInOneArray.push(0,1);
        threeStacksInOneArray.peek(0);
        threeStacksInOneArray.push(0,2);
        threeStacksInOneArray.peek(0);
        threeStacksInOneArray.push(0,3);
        threeStacksInOneArray.peek(0);
        threeStacksInOneArray.push(0,4);
        threeStacksInOneArray.peek(0);
        threeStacksInOneArray.push(1,5);
        threeStacksInOneArray.peek(1);
        threeStacksInOneArray.push(2,6);
        threeStacksInOneArray.peek(2);
        threeStacksInOneArray.push(1,7);
        threeStacksInOneArray.peek(1);
        threeStacksInOneArray.pop(1);
        threeStacksInOneArray.peek(1);
        threeStacksInOneArray.push(2,8);
        threeStacksInOneArray.peek(2);
        threeStacksInOneArray.push(1,1);
        threeStacksInOneArray.peek(1);

    }
}
