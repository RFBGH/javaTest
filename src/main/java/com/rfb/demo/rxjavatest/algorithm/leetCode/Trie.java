package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private static class TrieNode{
        public char c;
        public Map<Character, TrieNode> sons = new HashMap<>();
        public boolean isWord;

        public TrieNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
        }
    }

    private TrieNode root = new TrieNode((char) 0, false);

    /** Initialize your data structure here. */
    public Trie() {

    }

    private void addWord(TrieNode root, String word){
        TrieNode cur = root;
        for(int i = 0, size = word.length(); i < size; i++){
            char c = word.charAt(i);
            TrieNode next = cur.sons.get(c);
            if(next == null){
                next = new TrieNode(c, false);
                cur.sons.put(c, next);
            }

            cur = next;
            if(i == size-1){
                next.isWord = true;
            }
        }
    }

    private boolean contain(TrieNode root, String word){
        TrieNode cur = root;
        for(int i = 0, size = word.length(); i < size; i++){
            char c = word.charAt(i);
            TrieNode next = cur.sons.get(c);
            if(next == null){
                return false;
            }
            cur = next;
        }

        return cur.isWord;
    }

    private boolean prefix(TrieNode root, String prefix){
        TrieNode cur = root;
        for(int i = 0, size = prefix.length(); i < size; i++){
            char c = prefix.charAt(i);
            TrieNode next = cur.sons.get(c);
            if(next == null){
                return false;
            }
            cur = next;
        }

        return true;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        addWord(root, word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return contain(root,word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return prefix(root, prefix);
    }
}
