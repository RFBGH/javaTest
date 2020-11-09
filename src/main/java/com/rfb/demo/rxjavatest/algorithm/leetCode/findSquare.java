package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class findSquare {

    public int[] findSquare(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] columnSum = new int[n][m];
        for(int j = 0; j < m; j++){
            columnSum[0][j] = matrix[0][j];
            for(int i = 1; i < n; i++){
                columnSum[i][j] = columnSum[i-1][j] + matrix[i][j];
            }
        }

        int[][] rowSum = new int[n][m];
        for(int i = 0; i < n; i++){
            rowSum[i][0] = matrix[i][0];
            for(int j = 1; j < m; j++){
                rowSum[i][j] = rowSum[i][j-1] + matrix[i][j];
            }
        }

        int max = 0;
        int r = 0;
        int c = 0;

        for(int i = 0; i < n; i++){
            for(int ii = i; ii < n; ii++) {

                int cut = ii - i;
                for(int j = 0, len = m - cut; j < len; j++){

                    int t = columnSum[ii][j] - columnSum[i][j] + matrix[i][j];
                    if(t != 0){
                        continue;
                    }

                    t = columnSum[ii][j+cut] - columnSum[i][j+cut] + matrix[i][j+cut];
                    if(t != 0){
                        continue;
                    }

                    t = rowSum[i][j+cut] - rowSum[i][j] + matrix[i][j+cut];
                    if(t != 0){
                        continue;
                    }

                    t = rowSum[ii][j+cut] - rowSum[ii][j] + matrix[ii][j+cut];
                    if(t != 0){
                        continue;
                    }

                    if(cut+1 > max){
                        max = cut+1;
                        c = j;
                        r = i;
                    }
                }
            }
        }

        if(max == 0){
            return new int[]{};
        }

        return new int[]{r, c, max};
    }

    public void test(){


        findSquare(new int[][]{
                {1,0,1},
                {0,0,1},
                {0,0,1}
        });

//        findSquare(new int[][]{
//                        {1, 1, 1, 1, 0, 1, 0, 1, 1, 1},
//                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
//                        {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
//                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
//                        {1, 1, 0, 0, 1, 0, 1, 0, 0, 1},
//                        {0, 0, 0, 1, 1, 1, 0, 1, 0, 1},
//                        {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
//                        {1, 0, 1, 0, 1, 1, 0, 1, 1, 1},
//                        {1, 1, 1, 0, 1, 0, 0, 1, 1, 1}
//                }
//
//        );

    }
}
