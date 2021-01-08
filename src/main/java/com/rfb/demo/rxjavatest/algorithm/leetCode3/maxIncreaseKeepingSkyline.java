package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class maxIncreaseKeepingSkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int[] maxInRow = new int[grid.length];
        int[] maxInColumn = new int[grid[0].length];

        for(int i = 0; i < grid.length; i++){
            int max = 0;
            for(int j = 0; j < grid[0].length; j++){
                if(max < grid[i][j]){
                    max = grid[i][j];
                }
            }
            maxInRow[i] = max;
        }

        for(int j = 0; j < grid[0].length; j++){
            int max = 0;
            for(int i = 0; i < grid.length; i++){
                if(max < grid[i][j]){
                    max = grid[i][j];
                }
            }
            maxInColumn[j] = max;
        }

        int sum = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                int height = Math.min(maxInRow[i], maxInColumn[j]);
                sum += height - grid[i][j];
            }
        }
        return sum;
    }
}
