package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {

        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rowSums = new int[n][m];
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == '1'){
                    sum++;
                }
                rowSums[i][j] = sum;
            }
        }

        int[][] columnSums = new int[n][m];
        for(int j = 0; j < m; j++) {
            int sum = 0;
            for(int i = 0; i < n; i++) {
                if(matrix[i][j] == '1'){
                    sum++;
                }
                columnSums[i][j] = sum;
            }
        }


        int[][] rectSums = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int sum = 0;
                for(int k = 0; k <= i; k++){
                    sum += rowSums[k][j];
                }
                rectSums[i][j] = sum;
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for(int ki = i; ki < n; ki++){

                        if(i > 0){
                            if(columnSums[ki][j] - columnSums[i-1][j] != (ki - i + 1)){
                                break;
                            }
                        }

                    for(int kj = j; kj < m; kj++){

                        int rect = (ki-i+1)*(kj-j+1);
                        int sum = rectSums[ki][kj];
                        if(i > 0){
                            sum -= rectSums[i-1][kj];
                        }
                        if(j > 0){
                            sum -= rectSums[ki][j-1];
                        }
                        if(i > 0 && j > 0){
                            sum += rectSums[i-1][j-1];
                        }

                        if(sum == rect){
                            if(sum > max){
                                max = sum;
                            }
                        }else{
                            break;
                        }
                    }
                }

            }
        }

        return max;
    }

    public void test(){
        System.out.println(maximalRectangle(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }

}
