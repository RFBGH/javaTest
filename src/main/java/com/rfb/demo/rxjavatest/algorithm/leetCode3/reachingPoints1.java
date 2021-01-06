package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.Queue;

public class reachingPoints1 {

    private static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy));

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            int x = cur.x+cur.y;
            int y = cur.y;
            if(x == tx && y == ty){
                return true;
            }

            if(x <= tx && y <= ty){
                queue.offer(new Node(x, y));
            }


            x = cur.x;
            y = cur.y+cur.x;
            if(x == tx && y == ty){
                return true;
            }

            if(x <= tx && y <= ty){
                queue.offer(new Node(x, y));
            }
        }

        return false;
    }

    public void test(){
        System.out.println(reachingPoints(35, 13, 455955547, 420098884));
    }
}
