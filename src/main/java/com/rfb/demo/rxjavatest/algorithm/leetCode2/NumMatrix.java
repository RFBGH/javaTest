package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class NumMatrix {

    private int[][] sum;
    private int n;
    private int m;
    public NumMatrix(int[][] matrix) {
        n = matrix.length;
        if(n == 0){
            return;
        }

        m = matrix[0].length;
        if(m == 0){
            return;
        }

        sum = new int[n][m];

        int[][] rowSum = new int[n][m];
        for(int i = 0; i < n; i++){
            rowSum[i][0] = matrix[i][0];
            for(int j = 1; j < m; j++){
                rowSum[i][j] = rowSum[i][j-1] + matrix[i][j];
            }
        }

        for(int j = 0; j < m; j++){
            sum[0][j] = rowSum[0][j];
        }

        for(int j = 0; j < m; j++){
            for(int i = 1; i < n; i++){
                sum[i][j] = sum[i-1][j] + rowSum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int s = sum[row2][col2];
        row1--;
        col1--;
        if(row1 == -1 && col1 == -1){
            return s;
        }

        if(row1 == -1){
            return s - sum[row2][col1];
        }

        if(col1 == -1){
            return s - sum[row1][col2];
        }

        return s + sum[row1][col1] - sum[row2][col1] - sum[row1][col2];
    }

}
