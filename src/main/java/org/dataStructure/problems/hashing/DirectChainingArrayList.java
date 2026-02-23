package org.dataStructure.problems.hashing;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DirectChainingArrayList {

    List<List<String>> hashtable;

    DirectChainingArrayList(int size){
        this.hashtable = new ArrayList<>(Collections.nCopies(size, null));
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
        int newIndex = modASCIIHashFunction(word, this.hashtable.size());
        if(this.hashtable.get(newIndex) == null){
            this.hashtable.set(newIndex, new LinkedList<>());
        }
        this.hashtable.get(newIndex).add(word);
        System.out.println("The word : " + word + " is inserted successfully");
    }

    public void displayHashTable(){
        if(this.hashtable == null){
            System.out.println("Hash table is Empty");
            return;
        }
        for (int i=0; i<this.hashtable.size(); i++) {
            System.out.println("Index " + i +" Key : " + this.hashtable.get(i));
        }
    }

    public boolean search(String word){
        if(this.hashtable == null){
            System.out.println("Hash table is Empty");
            return false;
        }
        int index = modASCIIHashFunction(word, this.hashtable.size());
        if(this.hashtable.get(index) != null && this.hashtable.get(index).contains(word)){
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
            int index = modASCIIHashFunction(word, this.hashtable.size());
            this.hashtable.get(index).remove(word);
        }
        else System.out.println("The word doesn't exist");

    }

    public static void main(String[] args) {
        DirectChainingArrayList chaining = new DirectChainingArrayList(13);
        chaining.insert("The");
        chaining.insert("quick");
        chaining.insert("brown");
        chaining.insert("fox");
        chaining.insert("over");
        chaining.insert("The");
        chaining.insert("quick");
        chaining.insert("brown");
        chaining.insert("fox");
        chaining.insert("over");
        chaining.insert("The");
        chaining.insert("quick");
        chaining.insert("brown");
        chaining.insert("fox");
        chaining.insert("over");
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
