package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.Queue;

public class minimumLengthEncoding {

    private static class TrieNode {
        char c;
        TrieNode[] nexts = new TrieNode[26];

        public TrieNode(char c) {
            this.c = c;
        }
    }

    private void append(TrieNode root, String word){

        TrieNode cur = root;
        for(int i = word.length()-1; i >= 0; i--){
            char c = word.charAt(i);
            if (cur.nexts[c-'a'] == null) {
                cur.nexts[c-'a'] = new TrieNode(c);
            }

            cur = cur.nexts[c-'a'];
        }
    }

    private static class Node{
        TrieNode trieNode;
        int level;

        public Node(TrieNode trieNode, int level) {
            this.trieNode = trieNode;
            this.level = level;
        }
    }

    public int minimumLengthEncoding(String[] words) {

        TrieNode root = new TrieNode('#');
        for(String word : words){
            append(root, word);
        }

        int sum = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            boolean isLeaf = true;
            for(int i = 0; i < 26; i++){
                if(cur.trieNode.nexts[i] == null){
                    continue;
                }

                isLeaf = false;
                queue.offer(new Node(cur.trieNode.nexts[i], cur.level+1));
            }

            if(isLeaf){
                sum += cur.level;
            }
        }

        return sum;
    }

    public void test(){
        System.out.println(minimumLengthEncoding(new String[]{"time", "me", "bell"}));
    }
}
