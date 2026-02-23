package org.leetcode.dailyProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Time Complexity: O(nxm) where n is the length of the word
//https://leetcode.com/problems/sum-of-prefix-scores-of-strings/submissions/1403725359/?envType=daily-question&envId=2024-09-25
public class SumPrefixScores {
    private final TrieNode rootNode;
    SumPrefixScores(){
        this.rootNode = new TrieNode();
    }
    public void insert(List<String> words){
        for(String word : words) {
            TrieNode currentNode = this.rootNode;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = currentNode.trieNodeMap.get(ch);
                if (null == node) {
                    node = new TrieNode();
                    currentNode.trieNodeMap.put(ch, node);
                    System.out.println("The word : " + ch + " is inserted successfully in the trie");
                }
                node.counter += 1;
                currentNode = node;
            }
        }
    }

    public List<Integer> calculateScoresForWords(List<String> words) {
        List<Integer> scores = new ArrayList<>();
        for (String word : words) {
            scores.add(calculateScoreForWord(word));
        }
        return scores;
    }

    private int calculateScoreForWord(String word) {
        TrieNode currentNode = this.rootNode;
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = currentNode.trieNodeMap.get(ch);
            if (node != null) {
                score += node.counter;
                currentNode = node;
            } else {
                break;
            }
        }
        return score;
    }

    public void printTrie(TrieNode node, String prefix) {
        for (Map.Entry<Character, TrieNode> entry : node.trieNodeMap.entrySet()) {
            String newPrefix = prefix + entry.getKey();
            System.out.println("Prefix: " + newPrefix + ", Counter: " + entry.getValue().counter);
            printTrie(entry.getValue(), newPrefix);
        }
    }

    public static void main(String[] args) {
        SumPrefixScores sumPrefixScores = new SumPrefixScores();
        List<String> words = List.of("abc","ab","bc","b");
        sumPrefixScores.insert(words);
        List<Integer> scores = sumPrefixScores.calculateScoresForWords(words);
        System.out.println("Scores: " + scores);
        sumPrefixScores.printTrie(sumPrefixScores.rootNode, "");


    }
}

class TrieNode{
    Map<Character, TrieNode> trieNodeMap;
    int counter;

    TrieNode(){
        this.trieNodeMap = new HashMap<>();
        this.counter = 0;
    }
}
