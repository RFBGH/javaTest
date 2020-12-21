package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.Queue;

public class swimInWater {

    private static class Node{
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static int[][] move = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    private boolean canReach(int[][] grid, int height){

        int n = grid.length;
        boolean[][] gone = new boolean[n][n];
        Queue<Node> queue = new LinkedList<>();
        if(grid[0][0] > height){
            return false;
        }

        queue.offer(new Node(0, 0));
        gone[0][0] = true;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(int k = 0; k < 4; k++){
                int i = cur.i + move[k][0];
                int j = cur.j + move[k][1];
                if(i < 0 || i >= n || j < 0 || j >= n){
                    continue;
                }

                if(grid[i][j] > height){
                    continue;
                }

                if(gone[i][j]){
                    continue;
                }

                if(i == n-1 && j == n-1){
                    return true;
                }

                gone[i][j] = true;
                queue.offer(new Node(i, j));
            }
        }

        return false;
    }

    public int swimInWater(int[][] grid) {

        int n = grid.length;
        int left = 0;
        int right = n*n;

        while (true){

            if(left == right){
                break;
            }

            int mid = (left + right) / 2;
            if(!canReach(grid, mid)){
                left = mid+1;
            }else{
                right = mid;
            }
        }

        return left;
    }

    public void test(){
        System.out.println(swimInWater(new int[][]{{0,2},{1,3}}));
    }
}
