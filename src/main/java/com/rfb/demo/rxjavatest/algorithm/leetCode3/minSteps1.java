package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.Queue;

public class minSteps1 {

    private static class Node{
        int count;
        int plast;
        Node prev;
        int step;


        public Node(int count, int plast, Node prev, int step) {
            this.count = count;
            this.plast = plast;
            this.prev = prev;
            this.step = step;
        }
    }

    public int minSteps(int n) {

        if(n == 1){
            return 0;
        }

        boolean[][] gone = new boolean[n+1][n+1];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1, null, 1));
        gone[1][0] = true;
        gone[1][1] = true;

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            int count = cur.count+cur.plast;
            if(count == n){
                return cur.step+1;
            }

            int plast = cur.plast;
            if(count <= n && !gone[count][plast]){
                gone[count][plast] = true;
                queue.offer(new Node(count, plast, cur, cur.step+1));
            }

            count = cur.count;
            plast = cur.count;
            if(count <= n && !gone[count][plast]){
                gone[count][plast] = true;
                queue.offer(new Node(count, plast, cur, cur.step+1));
            }
        }

        return -1;
    }

    public void test(){
        System.out.println(minSteps(3));
    }
}
