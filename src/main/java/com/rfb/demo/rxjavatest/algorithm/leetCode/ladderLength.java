package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class ladderLength {

    private static class Node{
        String word;
        int step;

        public Node(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    private boolean isArrive(String s, String e){

        int count = 0;
        for(int i = 0, size = s.length(); i < size; i++){
            if(s.charAt(i) != e.charAt(i)){
                count++;
            }

            if(count > 1){
                return false;
            }
        }

        return count == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(wordList.isEmpty()){
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(beginWord, 1));
        boolean[] used = new boolean[wordList.size()];

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i = 0, size = wordList.size(); i < size; i++){
                String next = wordList.get(i);

                if(used[i]){
                    continue;
                }

                if(!isArrive(cur.word, next)){
                    continue;
                }

                used[i] = true;
                if(next.equals(endWord)){
                    return cur.step+1;
                }

                queue.offer(new Node(next, cur.step+1));
            }
        }
        return 0;
    }

    public void test(){

        List<String> words = new ArrayList<>();
        words.add("hot");
        words.add("dot");
        words.add("dog");
        words.add("lot");
        words.add("log");
        words.add("cog");

        System.out.println(ladderLength("hit", "cog", words));
    }

}
