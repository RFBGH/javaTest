package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class canMeasureWater {

    private static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean canMeasureWater(int x, int y, int z) {

        if(x > y){
            int t = x;
            x = y;
            y = t;
        }

        boolean[][] gone = new boolean[x+1][y+1];
        gone[0][0] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int nextX = cur.x;
            int nextY = cur.y;

            if(nextX == z || nextY == z || nextX + nextY == z){
                return true;
            }

            nextX = x;
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }

            nextX = 0;
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }

            nextX = cur.x;
            nextY = cur.y;

            nextY = y;
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }

            nextY = 0;
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }

            nextX = 0;
            nextY = cur.y + cur.x;
            if(nextY > y){
                nextX = nextY-y;
                nextY = y;
            }
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }

            nextX = cur.y + cur.x;
            nextY = 0;
            if(nextX > x){
                nextY = nextX - x;
                nextX = x;
            }
            if(!gone[nextX][nextY]){
                gone[nextX][nextY] = true;
                queue.offer(new Node(nextX, nextY));
            }
        }

        return false;
    }

    public void test(){
        System.out.println(canMeasureWater(22003, 31237, 1));
    }
}
