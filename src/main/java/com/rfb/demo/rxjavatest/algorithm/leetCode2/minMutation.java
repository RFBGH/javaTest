package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.LinkedList;
import java.util.Queue;

public class minMutation {

    private static class Node{
        String cur;
        int step;

        public Node(String cur, int step) {
            this.cur = cur;
            this.step = step;
        }
    }

    public int minMutation(String start, String end, String[] bank) {

        boolean[] gone = new boolean[bank.length];
        for(int i = 0, size = bank.length; i < size; i++){
            String temp = bank[i];
            if(temp.equals(start)){
                gone[i] = true;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i = 0, size = bank.length; i < size; i++){
                if(gone[i]){
                    continue;
                }

                int unsameCount = 0;
                for(int j = 0, len = cur.cur.length(); j < len; j++){
                    if(cur.cur.charAt(j) != bank[i].charAt(j)){
                        unsameCount++;
                        if(unsameCount > 1){
                            break;
                        }
                    }
                }

                if(unsameCount == 1){
                    gone[i] = true;
                    if(bank[i].equals(end)){
                        return cur.step+1;
                    }
                    queue.offer(new Node(bank[i], cur.step+1));
                }
            }
        }

        return -1;
    }

    public void test(){
        System.out.println(minMutation("AAAAAAAA", "CCCCCCCC", new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"}));
    }
}
