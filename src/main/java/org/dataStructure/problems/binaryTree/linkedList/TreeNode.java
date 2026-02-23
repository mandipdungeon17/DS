package org.dataStructure.problems.binaryTree.linkedList;

import java.util.ArrayList;

public class TreeNode {

    String data;
    ArrayList<TreeNode> children;

    public TreeNode(String data){
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode node){
        this.children.add(node);
    }

    public String printLevel(int level){
        StringBuilder ret;
        ret = new StringBuilder(" ".repeat(level) + this.data + "\n");

        for(TreeNode node : this.children){
            ret.append(node.printLevel(level + 1));
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        TreeNode drinks = new TreeNode("Drinks");
        TreeNode hot = new TreeNode("Hot");
        TreeNode cold = new TreeNode("Cold");
        TreeNode tea = new TreeNode("Tea");
        TreeNode coffee = new TreeNode("Coffee");
        TreeNode alcoholic = new TreeNode("Alcoholic");
        TreeNode nonAlcoholic = new TreeNode("Non-Alcoholic");
        drinks.addChild(hot);
        drinks.addChild(cold);
        hot.addChild(tea);
        hot.addChild(coffee);
        cold.addChild(alcoholic);
        cold.addChild(nonAlcoholic);
        System.out.println(drinks.printLevel(1));

    }
}
