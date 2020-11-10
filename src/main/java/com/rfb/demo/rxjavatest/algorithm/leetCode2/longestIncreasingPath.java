package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class longestIncreasingPath {

    private int[][] result;

    private int dfs(int[][] matrix, int i, int j){

        if(result[i][j] != 0){
            return result[i][j];
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] move = new int[][]{{0,1}, {1,0}, {0,-1},{-1,0}};
        int max = 0;
        for(int k = 0; k < 4; k++){
            int nextI = i + move[k][0];
            int nextJ = j + move[k][1];
            if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m){
                continue;
            }

            if(matrix[nextI][nextJ] <= matrix[i][j]){
                continue;
            }

            int temp = dfs(matrix, nextI, nextJ);
            if(max < temp){
                max = temp;
            }
        }

        result[i][j] = max+1;
        return max+1;
    }

    public int longestIncreasingPath(int[][] matrix) {

        if(matrix.length == 0){
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        result = new int[n][m];

        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int temp = dfs(matrix, i, j);
                if(max < temp){
                    max = temp;
                }
            }
        }

        return max;
    }
}
