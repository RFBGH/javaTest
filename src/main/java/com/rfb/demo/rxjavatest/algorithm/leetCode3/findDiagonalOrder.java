package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class findDiagonalOrder {

    public int[] findDiagonalOrder(int[][] matrix) {

        if(matrix.length == 0 || matrix[0].length == 0){
            return new int[]{};
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[] result = new int[n*m];
        int resultCount = 0;

        int i = 0;
        int j = 0;
        boolean isUp = true;
        while (true){

            if(i == n-1 && j == m-1){
                result[resultCount] = matrix[i][j];
                break;
            }

            if(isUp){

                while (true){

                    result[resultCount++] = matrix[i][j];
                    i = i-1;
                    j = j+1;

                    if(i == -1 || j == m){
                        break;
                    }
                }

                if(i == -1 && j == m){
                    i = 1;
                    j = m-1;
                }else if(i == -1){
                    i = 0;
                }else{
                    i += 2;
                    j = m-1;
                }

            }else{

                while (true){

                    result[resultCount++] = matrix[i][j];
                    i = i+1;
                    j = j-1;

                    if(j == -1 || i == n){
                        break;
                    }
                }

                if(j == -1 && i == n){
                    i = n-1;
                    j = 1;
                }else if(j == -1){
                    j = 0;
                }else{
                    j += 2;
                    i = n-1;
                }
            }

            isUp = !isUp;
        }

        return result;
    }

    public void test(){
        findDiagonalOrder(new int[][]{
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }
        );
    }
}
