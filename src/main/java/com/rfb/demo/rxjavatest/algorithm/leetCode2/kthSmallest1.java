package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class kthSmallest1 {

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        int[] indexs = new int[n];
        while (k > 0){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for(int i = 0; i < n; i++){

                if(indexs[i] == n){
                    continue;
                }

                if(matrix[i][indexs[i]] < min){
                    min = matrix[i][indexs[i]];
                    minIndex = i;
                }
            }

            indexs[minIndex]++;
            k--;

            if(k == 0){
                return min;
            }
        }

        return 0;
    }

    public void test(){
        System.out.println(kthSmallest(new int[][]{
                { 1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        }, 6));
    }
}
