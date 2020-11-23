package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class flatten1 {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };



    public Node flatten(Node head) {

        if(head == null){
            return null;
        }

        List<Node> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (true){

            if(cur == null){
                if(stack.isEmpty()){
                    break;
                }
                cur = stack.pop();
            }
            result.add(cur);
            if(cur.child != null){
                if(cur.next != null){
                    stack.push(cur.next);
                }
                cur = cur.child;
            }else{
                cur = cur.next;
            }
        }

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
