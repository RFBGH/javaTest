package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import com.rfb.demo.rxjavatest.classtest.A;

import java.util.HashMap;
import java.util.Map;

public class AllOne {

    private static class Node{
        String key;
        int count;
        Node prev;
        Node next;
    }

    private Node head;
    private Node end;
    private Map<String, Node> map = new HashMap<>();
    /** Initialize your data structure here. */
    public AllOne() {

    }

    private void switchNode(Node node0, Node node1){

        if(node0 == null || node1 == null){
            return;
        }

        if(node0 == node1){
            return;
        }

        int count = node0.count;
        node0.count = node1.count;
        node1.count = count;

        String key = node0.key;
        node0.key = node1.key;
        node1.key = key;

        map.put(node0.key, node0);
        map.put(node1.key, node1);
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {

        Node node = map.get(key);
        if(node == null){

            node = new Node();
            node.count = 1;
            node.key = key;
            if(head == null){
                head = node;
                end = node;
            }else{
                node.next = head;
                head.prev = node;

                head = node;
            }
            map.put(key, node);
        }else{
            node.count++;
            Node cur = node.next;
            Node prev = null;
            while (cur != null && node.count > cur.count){
                prev = cur;
                cur = cur.next;
            }

            switchNode(node, prev);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = map.get(key);
        if(node == null){
            return;
        }

        node.count--;
        if(node.count == 0){
            if(node.prev != null){
                node.prev.next = node.next;
            }else{
                head = node.next;
            }
            if(node.next != null){
                node.next.prev = node.prev;
            }else{
                end = node.prev;
            }
            map.remove(key);
        }else{
            Node cur = node.prev;
            Node next = null;
            while (cur != null && node.count < cur.count){
                next = cur;
                cur = cur.prev;
            }

            switchNode(node, next);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(end != null){
            return end.key;
        }
        return "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head != null){
            return head.key;
        }
        return "";
    }

    public static void test(){
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");

        allOne.dec("b");
        allOne.dec("b");
        allOne.dec("a");
    }
}
