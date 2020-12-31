package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordFilter {

    private static class Node{
        List<Integer> prioritis = new ArrayList<>();
        public Node[] son = new Node[26];
    }

    private Node root = new Node();
    private void add(int priority, String word){

        Node cur = root;
        cur.prioritis.add(priority);
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            Node next = cur.son[c-'a'];
            if(next == null){
                next = new Node();
                cur.son[c-'a'] = next;
            }
            next.prioritis.add(priority);
            cur = next;
        }
    }

    private Node rRoot = new Node();
    private void addR(int priority, String word){

        Node cur = rRoot;
        cur.prioritis.add(priority);
        for(int i = word.length()-1; i >= 0; i--){
            char c = word.charAt(i);
            Node next = cur.son[c-'a'];
            if(next == null){
                next = new Node();
                cur.son[c-'a'] = next;
            }
            next.prioritis.add(priority);
            cur = next;
        }
    }

    private List<Integer> queryPrefix(String prefix){

        Node cur = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            cur = cur.son[c-'a'];
            if(cur == null){
                return null;
            }
        }

        return cur.prioritis;
    }

    private List<Integer> querySuffix(String suffix){

        Node cur = rRoot;
        for(int i = suffix.length()-1; i >= 0; i--){
            char c = suffix.charAt(i);
            cur = cur.son[c-'a'];
            if(cur == null){
                return null;
            }
        }

        return cur.prioritis;
    }

    public WordFilter(String[] words) {

        List<Integer> prioritis = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        prioritis.add(words.length-1);
        wordList.add(words[words.length-1]);

        for(int i = words.length-2; i >= 0; i--){
            if(words[i].equals(words[i+1])){
                continue;
            }
            prioritis.add(i);
            wordList.add(words[i]);
        }

        for(int i = 0; i < prioritis.size(); i++){
            add(prioritis.get(i), wordList.get(i));
            addR(prioritis.get(i), wordList.get(i));
        }
    }

    public int f(String prefix, String suffix) {
        List<Integer> pre = queryPrefix(prefix);
        if(pre == null){
            return -1;
        }

        List<Integer> suf = querySuffix(suffix);
        if(suf == null){
            return -1;
        }

        Set<Integer> set = new HashSet<>(pre);
        for(int i = 0; i < suf.size(); i++){
            Integer priority = suf.get(i);
            if(set.contains(priority)){
                return priority;
            }
        }
        return -1;
    }

    public static void test(){

        WordFilter wordFilter = new WordFilter(new String[]{"apple"});
        System.out.println(wordFilter.f("a", "e"));
        System.out.println(wordFilter.f("b", ""));
    }
}
