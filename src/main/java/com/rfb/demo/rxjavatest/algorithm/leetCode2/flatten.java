package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class flatten {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    private List<Node> result = new ArrayList<>();

    private void dfs(Node root){

        if(root == null){
            return;
        }

        result.add(root);
        dfs(root.child);
        dfs(root.next);
    }

    public Node flatten(Node head) {

        if(head == null){
            return null;
        }

        dfs(head);
        result.get(0).prev = null;
        result.get(result.size()-1).next = null;

        for(int i = 1, size = result.size(); i < size; i++){
            result.get(i-1).next = result.get(i);
            result.get(i).prev = result.get(i-1);
            result.get(i-1).child = null;
            result.get(i).child = null;
        }
        return result.get(0);
    }

}
