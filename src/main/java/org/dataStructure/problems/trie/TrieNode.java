package org.dataStructure.problems.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Map<Character, TrieNode> trieNodeMap;
    boolean endOfString;

    TrieNode(){
        this.trieNodeMap = new HashMap<>();
        this.endOfString = false;
    }
}
