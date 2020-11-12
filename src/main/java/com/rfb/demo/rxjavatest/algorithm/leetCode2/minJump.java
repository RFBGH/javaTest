package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.LinkedList;
import java.util.Queue;

public class minJump {

    private static class Node{
        int index;
        int step;

        public Node(int index, int step) {
            this.index = index;
            this.step = step;
        }
    }

    public int minJump(int[] jump) {
        int n = jump.length;

        boolean[] gone = new boolean[n+1];

        gone[0] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));
        int last = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int index = cur.index;
            int next = index + jump[index];
            if(next >= n){
                return cur.step+1;
            }

            if(!gone[next]){
                gone[next] = true;
                queue.offer(new Node(next, cur.step+1));
            }
            for(int i = last; i < index; i++){
                if(gone[i]){
                    continue;
                }
                gone[i] = true;
                queue.offer(new Node(i, cur.step+1));
            }
            last = Math.max(last, index+1);
        }

        return 0;
    }

    public void test(){
        System.out.println(minJump(new int[]{3,7,6,1,4,3,7,8,1,2,8,5,9,8,3,2,7,5,1,1}));
    }
}
