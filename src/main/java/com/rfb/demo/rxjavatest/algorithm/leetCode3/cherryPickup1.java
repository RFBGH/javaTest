package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class cherryPickup1 {

    private static class Node{
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    int ans = 0;
    private void dfs(int[][] grid, int i, int j, List<Node> path){

        if(i == grid.length-1 && j == grid[0].length-1){

            for(Node node : path){
                ans += grid[node.i][node.j];
                grid[node.i][node.j] = 0;
            }
            return;
        }

        if(i < grid.length-1 && grid[i+1][j] != -1){
            path.add(new Node(i+1, j));
            dfs(grid, i+1, j, path);
            path.remove(path.size()-1);
        }

        if(j < grid[0].length-1 && grid[i][j+1] != -1){
            path.add(new Node(i, j+1));
            dfs(grid, i, j+1, path);
            path.remove(path.size()-1);
        }
    }

    public int cherryPickup(int[][] grid) {

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0, 0));
        dfs(grid, 0, 0, nodes);
        return ans;
    }

    public void test(){
        System.out.println(cherryPickup(new int[][]
                {
                        { 0, 1, 1, 0, 0},
                        { 1, 1, 1, 1, 0},
                        {-1, 1, 1, 1,-1},
                        { 0, 1, 1, 1, 0},
                        { 1, 0,-1, 0, 0}}));
    }

}
