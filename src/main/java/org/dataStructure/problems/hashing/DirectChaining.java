package org.dataStructure.problems.hashing;


import java.util.LinkedList;

public class DirectChaining {

    LinkedList<String>[] hashtable;

    DirectChaining(int size){
        this.hashtable = new LinkedList[size];
    }

    public int modASCIIHashFunction(String word, int hashSize){
        char[] ch = word.toCharArray();
        int sum = 0;
        for (char c : ch) {
            sum += c;
        }
        return sum % hashSize;
    }

    public void insert(String word){
        int newIndex = modASCIIHashFunction(word, this.hashtable.length);
        if(this.hashtable[newIndex] == null){
            this.hashtable[newIndex] = new LinkedList<>();
        }
        this.hashtable[newIndex].add(word);
        System.out.println("The word : " + word + " is inserted successfully");
    }

    public void displayHashTable(){
        if(this.hashtable == null){
            System.out.println("Hash table is Empty");
            return;
        }
        for (int i=0; i<this.hashtable.length; i++) {
            System.out.println("Index " + i +" Key : " + this.hashtable[i]);
        }
    }

    public boolean search(String word){
        if(this.hashtable == null){
            System.out.println("Hash table is Empty");
            return false;
        }
        int index = modASCIIHashFunction(word, this.hashtable.length);
        if(this.hashtable[index] != null && this.hashtable[index].contains(word)){
            System.out.println("The word exist at index : " + index);
            return true;
        }
        else{
            System.out.println("The word doesn't exist : " + word);
            return false;
        }
    }

    public void delete(String word){
        boolean exist = search(word);
        if(exist){
            int index = modASCIIHashFunction(word, this.hashtable.length);
            this.hashtable[index].remove(word);
        }
        else System.out.println("The word doesn't exist");

    }

    public static void main(String[] args) {
        DirectChaining chaining = new DirectChaining(13);
        chaining.insert("The");
        chaining.insert("quick");
        chaining.insert("brown");
        chaining.insert("fox");
        chaining.insert("over");
        chaining.displayHashTable();
        chaining.search("fox");
        chaining.search("ox");
        chaining.delete("fox");
        chaining.displayHashTable();
        chaining.delete("The");
        chaining.displayHashTable();
        chaining.insert("The");
        chaining.displayHashTable();

    }
}
