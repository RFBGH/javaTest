package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class numIslands {

    private static class Node{
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] gone = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(gone[i][j] || grid[i][j] == '0'){
                    continue;
                }

                sum++;
                gone[i][j] = true;
                queue.add(new Node(i, j));
                while (!queue.isEmpty()){
                    Node cur = queue.poll();
                    for(int k = 0; k < 4; k++){
                        int nextI = cur.i + move[k][0];
                        int nextJ = cur.j + move[k][1];
                        if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m){
                            continue;
                        }

                        if(grid[nextI][nextJ] == '0'){
                            continue;
                        }

                        if(gone[nextI][nextJ]){
                            continue;
                        }

                        gone[nextI][nextJ] = true;
                        queue.add(new Node(nextI, nextJ));
                    }
                }
            }
        }

        return sum;
    }

    public void test(){
        System.out.println(numIslands(new char[][]{
                        {'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}
                }

        ));
    }

}
