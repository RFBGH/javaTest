package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class WordBreak2 {

    private static class DicTree{

        char c;
        boolean isWord;
        Map<Character, DicTree> sons = new HashMap<>();

        public DicTree(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
        }

        public void addSon(DicTree son){
            sons.put(son.c, son);
        }
    }

    private static class Node{
        String word;
        int from;
        int to;
        List<Node> pres = new ArrayList<>();

        public Node(String word, int from, int to) {
            this.word = word;
            this.from = from;
            this.to = to;
        }

        public void appendPre(Node pre){
            pres.add(pre);
        }
    }

    private void addWord(DicTree root, String word){

        DicTree cur = root;

        for(int i = 0, size = word.length(); i < size; i++){

            char c = word.charAt(i);
            DicTree next = cur.sons.get(c);

            boolean isWord = i == size-1;

            if(next == null){
                next = new DicTree(c, isWord);
                cur.addSon(next);
            }else{
                if(isWord){
                    next.isWord = true;
                }
            }
            cur = next;
        }
    }

    private List<Node> getFindWords(DicTree root, String s, int start, Node[][] nodeMap, Node cur){

        List<Node> findNodes = new ArrayList<>();

        DicTree curDic = root;
        for(int i = start, size = s.length(); i < size; i++){

            char c = s.charAt(i);
            DicTree nextDic = curDic.sons.get(c);
            if(nextDic == null){
                break;
            }

            if(nextDic.isWord){

                Node node = nodeMap[start][i];
                if(node == null){
                    node = new Node(s.substring(start, i+1), start, i);
                    nodeMap[start][i] = node;

                    if(cur != null){
                        node.appendPre(cur);
                    }
                    findNodes.add(node);
                }else{
                    if(cur != null){
                        node.appendPre(cur);
                    }
                }
            }

            curDic = nextDic;
        }

        return findNodes;
    }

    private void dfs(List<String> sentences, List<String> words, Node cur){

        if(cur.pres.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(int i = words.size()-1; i >= 0; i--){
                String word = words.get(i);
                sb.append(word);
                if(i != 0){
                    sb.append(" ");
                }
            }

            sentences.add(sb.toString());
            return;
        }

        for(Node next : cur.pres){
            words.add(next.word);
            dfs(sentences, words, next);
            words.remove(words.size()-1);
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {

        DicTree root = new DicTree('#', false);
        for(String word : wordDict){
            addWord(root, word);
        }

        List<String> result = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        List<Node> target = new ArrayList<>();

        Node[][] nodeMap = new Node[s.length()][s.length()];

        List<Node> nodes = getFindWords(root, s, 0, nodeMap, null);
        if(nodes.isEmpty()){
            return result;
        }

        queue.addAll(nodes);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            nodes = getFindWords(root, s, cur.to+1, nodeMap, cur);
            queue.addAll(nodes);

            if(cur.to+1 == s.length()){
                target.add(cur);
            }

        }

        if(target.isEmpty()){
            return result;
        }

        for(Node node : target){

            List<String> words = new ArrayList<>();
            words.add(node.word);

            dfs(result, words, node);
        }

        return result;
    }

    public void test(){

//        String[] words = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//        List<String> wordDict = new ArrayList<>(Arrays.asList(words));
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        List<String> sentences = wordBreak("catsanddog", wordDict);

//        List<String> sentences = wordBreak("aaaaaaaaaaaaaaaaaaaaaa", wordDict);
//        List<String> sentences = wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", wordDict);
        for(String s : sentences){
            System.out.println(s);
        }

    }
}
