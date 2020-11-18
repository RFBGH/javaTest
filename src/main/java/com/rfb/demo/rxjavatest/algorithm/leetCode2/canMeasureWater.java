package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class canMeasureWater {

    private static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean canMeasureWater(int x, int y, int z) {

        if(x > y){
            int t = x;
            x = y;
            y = t;
        }

        Set<Node> set = new HashSet<>();
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
            Node newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }

            nextX = 0;
            newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }


            nextX = cur.x;
            nextY = cur.y;

            nextY = y;
            newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }


            nextY = 0;
            newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }


            nextX = 0;
            nextY = cur.y + cur.x;
            if(nextY > y){
                nextX = nextY-y;
                nextY = y;
            }
            newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }

            nextX = cur.y + cur.x;
            nextY = 0;
            if(nextX > x){
                nextY = nextX - x;
                nextX = x;
            }
            newNode = new Node(nextX, nextY);
            if(!set.contains(newNode)){
                set.add(newNode);
                queue.offer(newNode);
            }
        }

        return false;
    }

    public void test(){
        System.out.println(canMeasureWater(22003, 31237, 1));
    }
}
