package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class hitBricks {

    private static class Node{
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private int[][] move = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {

        List<Integer> result = new ArrayList<>();

        int n = grid.length;
        int m = grid[0].length;

        for(int[] p : hits){

            if(grid[p[0]][p[1]] == 0){
                result.add(0);
                continue;
            }

            grid[p[0]][p[1]] = 0;
            boolean[][] gone = new boolean[n][m];
            int flow = 0;
            for(int k = 0; k < 4; k++){
                int i = p[0] + move[k][0];
                int j = p[1] + move[k][1];

                if(i < 0 || i >= n || j < 0 || j >= m){
                    continue;
                }

                if(grid[i][j] == 0){
                    continue;
                }

                if(gone[i][j]){
                    continue;
                }
                gone[i][j] = true;
                List<Node> queue = new ArrayList<>();
                queue.add(new Node(i, j));
                int front = 0;
                boolean keep = i == 0;
                while (front < queue.size()){

                    Node cur = queue.get(front);
                    for(int t = 0; t < 4; t++){

                        int nextI = cur.i + move[t][0];
                        int nextJ = cur.j + move[t][1];
                        if(nextI < 0 || nextJ < 0 || nextI >= n || nextJ >= m){
                            continue;
                        }

                        if(grid[nextI][nextJ] == 0){
                            continue;
                        }

                        if(gone[nextI][nextJ]){
                            continue;
                        }

                        gone[nextI][nextJ] = true;
                        if(nextI == 0){
                            keep = true;
                        }

                        queue.add(new Node(nextI, nextJ));
                    }

                    front++;
                }

                if(!keep){
                    flow += queue.size();
                    for(Node node : queue){
                        grid[node.i][node.j] = 0;
                    }
                }
            }

            result.add(flow);
        }

        int[] a = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            a[i] = result.get(i);
        }
        return a;
    }

    public void test(){
        hitBricks(new int[][]{{1,0,0,0},{1,1,1,0}}, new int[][]{{1, 0}});
    }
}
