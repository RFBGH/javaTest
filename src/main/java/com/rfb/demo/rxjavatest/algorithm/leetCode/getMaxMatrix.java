package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class getMaxMatrix {

    public int[] getMaxMatrix(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] columnSum = new int[n][m];
        for(int i = 0; i < m; i++){
            columnSum[0][i] = matrix[0][i];
        }

        for(int j = 0; j < m; j++){
            for(int i = 1; i < n; i++){
                columnSum[i][j] = columnSum[i-1][j] + matrix[i][j];
            }
        }

        int allMax = Integer.MIN_VALUE;
        int allTargetFrom = 0;
        int allTargetTo = 0;
        int allTargetI = 0;
        int allTargetII = 0;
        for(int i = 0; i < n; i++){
            for(int ii = i; ii < n; ii++){

                int sum = 0;
                int max = Integer.MIN_VALUE;
                int from = 0;
                int targetFrom = 0;
                int targetTo = 0;
                for(int j = 0; j < m; j++){
                    int t = columnSum[ii][j] - columnSum[i][j] + matrix[i][j];

                    if(sum < 0){
                        sum = 0;
                        from = j;
                    }

                    sum += t;
                    if(sum > max){
                        max = sum;
                        targetFrom = from;
                        targetTo = j;
                    }

                }

                if(allMax < max){
                    allTargetFrom = targetFrom;
                    allTargetTo = targetTo;
                    allTargetI = i;
                    allTargetII = ii;
                    allMax = max;
                }
            }
        }

        return new int[]{allTargetI,allTargetFrom, allTargetII, allTargetTo};
    }


    public void test(){

        getMaxMatrix(new int[][]{{-1, 0}, {0, -1}});

    }
}
