package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class cherryPickup {

    private static class Node{
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i &&
                    j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    private List<Node> path = new ArrayList<>();
    private Map<Node, Integer> map = new HashMap<>();

    private int dfs(int[][] grid, int i, int j){
        Node node = new Node(i, j);
        if(map.containsKey(node)){
            return map.get(node);
        }

        if(i == grid.length-1 && j == grid[0].length-1){
            path.add(node);
            map.put(node, grid[i][j]);
            return grid[i][j];
        }

        int value = -1;
        if(i < grid.length-1 && grid[i+1][j] != -1){
            value = dfs(grid, i+1, j);
        }

        if(j < grid[0].length-1 && grid[i][j+1] != -1){
            int temp = dfs(grid, i, j+1);
            if(temp > value){
                value = temp;
            }
        }

        if(value < 0){
            map.put(node, value);
            return value;
        }

        path.add(node);
        map.put(node, value + grid[i][j]);
        return value + grid[i][j];
    }

    private int dfs1(int[][] grid, int i, int j){

        if(i == 0 && j == 0){
            int value = grid[i][j];
            grid[i][j] = 0;
            return value;
        }

        int value = -1;
        if(i > 0 && grid[i-1][j] != -1){
            value = dfs1(grid, i-1, j);
        }

        if(j > 0 && grid[i][j-1] != -1){
            int temp = dfs1(grid, i, j-1);
            if(temp > value){
                value = temp;
            }
        }

        if(value < 0){
            return value;
        }

        int temp = grid[i][j];
        grid[i][j] = 0;
        return value + temp;
    }

    public int cherryPickup(int[][] grid) {

        int ans = dfs(grid, 0, 0);
        if(ans < 0){
            return ans;
        }

//        ans += dfs1(grid, grid.length-1, grid[0].length-1);
        return ans;
    }

    public void test(){
        System.out.println(cherryPickup(new int[][]
                {
                        {0, 1, -1},
                        {1, 0, -1},
                        {1, 1,  1}}));
    }

}
