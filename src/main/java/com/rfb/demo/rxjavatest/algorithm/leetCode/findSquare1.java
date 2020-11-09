package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class findSquare1 {

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

        int max = 0;
        int r = 0;
        int c = 0;

        for(int i = 0; i < n; i++){
            for(int ii = i; ii < n; ii++) {

                int start = 0;
                int to = start;

                while (start < m){

                    int t = columnSum[ii][start] - columnSum[i][start] + matrix[i][start];
                    if(t != 0){
                        start++;
                        continue;
                    }

                    to = start;
                    while (to < m && to - start < ii - i){

                        if(matrix[i][to] == 0 && matrix[ii][to] == 0){
                            to++;
                            continue;
                        }
                        break;
                    }

                    if(to != m && to - start == ii - i){
                        t = columnSum[ii][to] - columnSum[i][to] + matrix[i][to];
                        if(t == 0){
                            if(to - start +1 > max){
                                max = to - start + 1;
                                r = i;
                                c = start;
                            }
                        }
                    }

                    start++;
                }
            }
        }

        if(max == 0){
            return new int[]{};
        }

        return new int[]{r, c, max};
    }

    public void test(){


//        findSquare(new int[][]{
//                {1,0,1},
//                {0,0,1},
//                {0,0,1}
//        });

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
