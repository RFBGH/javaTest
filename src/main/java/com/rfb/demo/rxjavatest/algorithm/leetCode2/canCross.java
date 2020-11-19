package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class canCross {

    private static class Node{
        int i;
        int k;

        public Node(int x, int k) {
            this.i = x;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i &&
                    k == node.k;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, k);
        }
    }

    public boolean canCross(int[] stones) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0, size = stones.length; i < size; i++){
            map.put(stones[i], i);
        }

        int n = stones.length-1;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(0, 0);
        queue.offer(root);
        Set<Node> set = new HashSet<>();
        set.add(root);

        int[] move = new int[]{-1, 0, 1};
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int x = stones[cur.i];
            for(int k = 0; k < 3; k++){
                int nextK = cur.k + move[k];
                int nextX = x + nextK;
                Integer nextI = map.get(nextX);
                if(nextI == null){
                    continue;
                }

                if(nextX < x){
                    continue;
                }

                if(nextI == n){
                    return true;
                }

                Node next = new Node(nextI, nextK);
                if(set.contains(next)){
                    continue;
                }

                set.add(next);
                queue.offer(next);
            }
        }

        return false;
    }

    public void test(){
        System.out.println(canCross(new int[]{0,1,3,6,10,13,15,18}));
    }
}
