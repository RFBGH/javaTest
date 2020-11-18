package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.LinkedList;
import java.util.Queue;

public class allCellsDistOrder {

    private static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private int[][] move = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        int resultCount = 0;
        int[][] result = new int[R*C][2];
        boolean[][] gone = new boolean[R][C];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r0, c0));
        gone[r0][c0] = true;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            result[resultCount][0] = cur.r;
            result[resultCount][1] = cur.c;
            resultCount++;

            for(int i = 0; i < 4; i++){
                int r = cur.r + move[i][0];
                int c = cur.c + move[i][1];
                if(r < 0 || r >= R || c < 0 || c >= C){
                    continue;
                }

                if(gone[r][c]){
                    continue;
                }

                gone[r][c] = true;
                queue.offer(new Node(r, c));
            }
        }

        return result;
    }

    public void test(){
        allCellsDistOrder(2, 3, 1, 2);
    }
}
