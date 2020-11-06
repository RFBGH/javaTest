package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordDictionary {

    private static class TrieNode{
        char c;
        boolean isWord;
        Map<Character, TrieNode> sons = new HashMap<>();

        public TrieNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
        }
    }

    private TrieNode root = new TrieNode((char) 0, false);
    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    private void add(TrieNode root, String word){

        TrieNode cur = root;
        for(int i = 0, size = word.length(); i < size; i++){
            char c = word.charAt(i);
            TrieNode next = cur.sons.get(c);
            if(next == null){
                next = new TrieNode(c, false);
                cur.sons.put(c, next);
            }

            if(i == size-1){
                next.isWord = true;
            }
            cur = next;
        }
    }
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        add(root, word);
    }

    private boolean search(TrieNode root, String word, int cur){
        if(cur == word.length()){
            return root.isWord;
        }

        char c = word.charAt(cur);
        if(c == '.'){
            Set<Character> keys = root.sons.keySet();
            for(Character key : keys){
                TrieNode next = root.sons.get(key);
                if(search(next, word, cur+1)){
                    return true;
                }
            }
        }else{
            TrieNode next = root.sons.get(c);
            if(next == null){
                return false;
            }

            return search(next, word, cur+1);
        }
        return false;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }
}
