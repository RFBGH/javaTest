package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class allPathsSourceTarget1 {

    private static class Node{
        int pos;
        Node pre;

        public Node(int pos, Node pre) {
            this.pos = pos;
            this.pre = pre;
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, null));
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.pos == graph.length-1){

                List<Integer> path = new ArrayList<>();
                while (cur != null){
                    path.add(cur.pos);
                    cur = cur.pre;
                }
                Collections.reverse(path);
                result.add(path);
                continue;
            }

            for(int next : graph[cur.pos]){
                queue.offer(new Node(next, cur));
            }
        }

        return result;
    }
}
