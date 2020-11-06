package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class calculateMinimumHP {

    private static class Node{
        int i;
        int j;
        int hold;
        int sum;

        public Node(int i, int j, int hold, int sum) {
            this.i = i;
            this.j = j;
            this.hold = hold;
            this.sum = sum;
        }
    }

    public int calculateMinimumHP(int[][] dungeon) {


        Queue<Node> queue = new LinkedList<>();
        int hold = 0;
        if(dungeon[0][0] < 0){
            hold = dungeon[0][0];
        }
        queue.add(new Node(0, 0, hold, dungeon[0][0]));

        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int i = cur.i;
            int j = cur.j;

            if(i == dungeon.length-1 && j == dungeon[0].length - 1){
                if(cur.hold > max){
                    max = cur.hold;
                }
            }

            if(i < dungeon.length-1){
                int nextI = i+1;
                int sum = cur.sum + dungeon[nextI][j];
                hold = cur.hold;
                if(sum < hold){
                    hold = sum;
                }

                if(hold > max){
                    queue.add(new Node(nextI, j, hold, sum));
                }
            }

            if(j < dungeon[0].length-1){
                int nextJ = j+1;
                int sum = cur.sum + dungeon[i][nextJ];
                hold = cur.hold;
                if(sum < hold){
                    hold = sum;
                }

                if(hold > max){
                    queue.add(new Node(i, nextJ, hold, sum));
                }
            }
        }

        return -max+1;
    }

    public void test(){
        System.out.println( calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));
    }



}
