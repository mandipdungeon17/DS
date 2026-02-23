package org.dataStructure.problems.trie;

public class Trie {

    private final TrieNode root;

    Trie(){
        this.root = new TrieNode();
    }

    public void insert(String word){
        TrieNode currentNode = this.root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            TrieNode node = currentNode.trieNodeMap.get(ch);
            if(null == currentNode.trieNodeMap.get(ch)){
                node = new TrieNode();
                currentNode.trieNodeMap.put(ch, node);
            }
            currentNode = node;
        }
        currentNode.endOfString = true;
        System.out.println("The word : " + word + " is inserted successfully in the trie");
    }

    public void search(String word){
        TrieNode currentNode = this.root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            TrieNode node = currentNode.trieNodeMap.get(ch);
            if(null == node){
                System.out.println("The word : " + word + " doesn't exist");
                return;
            }
            currentNode = node;
        }
        if(currentNode.endOfString) System.out.println("The word : " + word + " exist in the trie");
        else System.out.println("The word : " + word + " doesn't exist but it's a prefix of the word");
    }

    public void deleteSelf(String word){
        TrieNode currentNode = this.root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            TrieNode node = currentNode.trieNodeMap.get(ch);
            if(null == node){
                System.out.println("The word : " + word + " doesn't exist");
                return;
            }
            currentNode = node;
        }
        if(currentNode.endOfString){
            currentNode.endOfString = false;
            System.out.println("The word : " + word + " is deleted successfully");
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("APP");
        trie.search("PP");
        trie.search("AP");
        trie.search("APPS");
        trie.insert("APPS");
        trie.search("APPS");
        trie.insert("AP");
        trie.search("AP");
        trie.deleteSelf("APP");
        trie.search("APP");
        trie.deleteSelf("AP");
        trie.search("AP");
    }
}
