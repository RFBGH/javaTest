package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class IslandPerimeter {

    private static class Node{
        int x;
        int y;

        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public int islandPerimeter(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] gone = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                gone[i][j] = false;
            }
        }

        int sum = 0;

        Queue<Node> queue = new LinkedList<>();

        int move[][] = {{0,-1},{0,1},{-1,0},{1,0}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(grid[i][j] == 0){
                    continue;
                }

                if(gone[i][j]){
                    continue;
                }

                gone[i][j] = true;
                queue.offer(new Node(i, j));
                while (!queue.isEmpty()){

                    Node cur = queue.poll();
                    for(int k = 0; k < 4; k++){
                        int x = cur.x + move[k][0];
                        int y = cur.y + move[k][1];

                        if(x < 0 || x >= m || y < 0 || y >= n){
                            sum++;
                            continue;
                        }

                        if(gone[y][x]){
                            continue;
                        }

                        if(grid[y][x] == 0){
                            sum++;
                            continue;
                        }

                        gone[y][x] = true;
                        queue.offer(new Node(y, x));
                    }
                }
            }
        }

        return sum;
    }

    public void test(){

        System.out.println(islandPerimeter(new int[][]{{0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}}));
    }

}
