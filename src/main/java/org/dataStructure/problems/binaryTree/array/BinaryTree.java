package org.dataStructure.problems.binaryTree.array;

public class BinaryTree {
    String[] treeArr;
    int lastUsedIndex;

    BinaryTree(int size){
        this.treeArr = new String[size + 1];
        this.lastUsedIndex = 0;
        System.out.println("Blank tree of size " + size + " has been created");
    }

    public boolean isFull(){
        return (treeArr.length - 1) == this.lastUsedIndex;
    }

    public void insert(String value){
        if(!isFull()){
            this.treeArr[this.lastUsedIndex+1] = value;
            this.lastUsedIndex++;
            System.out.println("The value " + value + " is inserted successfully");
        }
        else System.out.println("The array is Full");
    }

    public void preOrder(int index){
        if(index > this.lastUsedIndex){
            return;
        }
        else{
            System.out.print(this.treeArr[index] + " -> ");
            preOrder(2*index);
            preOrder(2*index+1);
        }
    }

    public void inOrder(int index){
        if(index > this.lastUsedIndex){
            return;
        }
        else{
            inOrder(2*index);
            System.out.print(this.treeArr[index] + " -> ");
            inOrder(2*index+1);
        }
    }

    public void postOrder(int index){
        if(index > this.lastUsedIndex){
            return;
        }
        else{
            postOrder(2*index);
            postOrder(2*index+1);
            System.out.print(this.treeArr[index] + " -> ");
        }
    }

    public void levelOrder(){
        for(int i=1; i<= this.lastUsedIndex; i++){
            System.out.print(this.treeArr[i] + " -> ");
        }
    }

    public void searchNodeInBinaryTree(String value){
        for(int i=1; i<= this.lastUsedIndex; i++){
            if(this.treeArr[i].equals(value)){
                System.out.println("The value "+value+" is found at index " + i);
                return;
            }
        }
        System.out.println("The value "+value+" does not exist");
    }

    public void deleteDeepestNode(){
        System.out.println("The Deepest Node is deleted successfully : " + this.treeArr[this.lastUsedIndex]);
        this.treeArr[this.lastUsedIndex] = null;
        this.lastUsedIndex--;
    }

    public void deleteTheGivenNode(String value){
        for(int i=1; i<= this.lastUsedIndex; i++) {
            if (this.treeArr[i].equals(value)) {
                System.out.println("The Node is deleted successfully : " + this.treeArr[i]);
                this.treeArr[i] = this.treeArr[this.lastUsedIndex];
                this.treeArr[this.lastUsedIndex] = null;
                this.lastUsedIndex--;
                return;
            }
        }
        System.out.println("The value does not exist");
    }

    public void deleteBinaryTree(){
        try {
            this.treeArr = null;
            System.out.println("The entire BT is deleted");
        } catch (Exception e) {
            System.out.println("There is an error deleting BT " + e);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(9);
        tree.insert("N1");
        tree.insert("N2");
        tree.insert("N3");
        tree.insert("N4");
        tree.insert("N5");
        tree.insert("N6");
        tree.insert("N7");
        tree.insert("N8");
        tree.insert("N9");

        System.out.println("PreOrder Traversal");
        tree.preOrder(1);

        System.out.println();
        System.out.println("InOrder Traversal");
        tree.inOrder(1);

        System.out.println();
        System.out.println("Post Traversal");
        tree.postOrder(1);

        System.out.println();
        System.out.println("LevelOrder Traversal");
        tree.levelOrder();

        /* PreOrder Traversal
            N1 -> N2 -> N4 -> N8 -> N9 -> N5 -> N3 -> N6 -> N7 ->
          InOrder Traversal
            N8 -> N4 -> N9 -> N2 -> N5 -> N1 -> N6 -> N3 -> N7 ->
          Post Traversal
            N8 -> N9 -> N4 -> N5 -> N2 -> N6 -> N7 -> N3 -> N1 ->
          LevelOrder Traversal
            N1 -> N2 -> N3 -> N4 -> N5 -> N6 -> N7 -> N8 -> N9 ->
         */

        System.out.println();
        System.out.println("Search Node In BinaryTree");
        tree.searchNodeInBinaryTree("N5");

        System.out.println();
        System.out.println("Search Node In BinaryTree");
        tree.searchNodeInBinaryTree("N10");

        System.out.println();
        System.out.println("Delete Deepest Node");
        tree.deleteDeepestNode();

        System.out.println();
        System.out.println("Delete the given node");
        tree.deleteTheGivenNode("N4");

        System.out.println();
        System.out.println("LevelOrder Traversal");
        tree.levelOrder();

        System.out.println();
        System.out.println("Delete entire node");
        tree.deleteBinaryTree();

        System.out.println();
        System.out.println("LevelOrder Traversal");
        tree.levelOrder();
    }
}
