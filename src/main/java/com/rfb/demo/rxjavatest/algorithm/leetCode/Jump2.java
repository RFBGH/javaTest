package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Jump2 {

    private static class Node{
        int pos;
        int step;

        public Node(int pos, int step) {
            this.pos = pos;
            this.step = step;
        }
    }

    public int jump(int[] nums) {

        if(nums.length == 1){
            return 0;
        }

        boolean[] used = new boolean[nums.length];
        for(int i = 0, size = nums.length; i < size; i++){
            used[i] = false;
        }

        int size = nums.length;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0));

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(int i = 1; i <= nums[cur.pos]; i++){
                int pos = cur.pos + i;
                if(used[pos]){
                    continue;
                }

                used[pos] = true;
                int step = cur.step + 1;
                if(pos == size-1){
                    return step;
                }

                queue.offer(new Node(pos, step));
            }
        }

        return 0;
    }

    public void test(){
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }

}
