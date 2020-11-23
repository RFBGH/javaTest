package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class levelOrder {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<List<Integer>> levelOrder(Node root) {

        if(root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        List<Node> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (true){

            List<Integer> list = new ArrayList<>();
            List<Node> nextLevel = new ArrayList<>();
            for(Node node : curLevel){
                list.add(node.val);
                nextLevel.addAll(node.children);
            }

            if(list.isEmpty()){
                break;
            }

            result.add(list);
            curLevel = nextLevel;
        }

        return result;
    }
}
