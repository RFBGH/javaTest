package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class reorganizeString {

    private static class Node{
        int count;
        char c;

        public Node(int count, char c) {
            this.count = count;
            this.c = c;
        }
    }

    public String reorganizeString(String S) {

        List<Node> nodes = new ArrayList<>(26);
        for(int i = 0; i < 26; i++){
            Node node = new Node(0, (char)('a'+i));
            nodes.add(node);
        }

        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            int index = c-'a';
            Node node = nodes.get(index);
            node.count++;
        }

        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.count > o2.count){
                    return -1;
                }

                if(o1.count < o2.count){
                    return 1;
                }

                return 0;
            }
        });

        char[] result = new char[S.length()];
        for(int i = 0; i < S.length(); i += 2){
            for(Node node : nodes){
                if(node.count == 0){
                    continue;
                }

                node.count--;
                result[i] = node.c;
                break;
            }
        }

        for(int i = 1; i < S.length(); i += 2){
            for(Node node : nodes){
                if(node.count == 0){
                    continue;
                }

                node.count--;
                result[i] = node.c;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length()-1; i++){
            if(result[i] == result[i+1]){
                return "";
            }
            sb.append(result[i]);
        }

        sb.append(result[S.length()-1]);
        return sb.toString();
    }

    public void test(){
        System.out.println(reorganizeString("vvvlo"));
    }
}
