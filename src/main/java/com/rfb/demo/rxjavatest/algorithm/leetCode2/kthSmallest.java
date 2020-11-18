package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class kthSmallest {

    private int[] merge(int[][] matrix, int from, int to){

        if(from == to){
            return matrix[from];
        }

        int mid = (from + to) / 2;
        int[] top = merge(matrix, from, mid);
        int[] bottom = merge(matrix, mid+1, to);
        int i = 0;
        int j = 0;
        int resultCount = 0;
        int[] result = new int[matrix.length*(to-from+1)];

        while (i < top.length && j < bottom.length){
            if(top[i] < bottom[j]){
                result[resultCount++] = top[i++];
            }else{
                result[resultCount++] = bottom[j++];
            }
        }

        while (i < top.length ){
            result[resultCount++] = top[i++];
        }

        while (j < bottom.length){
            result[resultCount++] = bottom[j++];
        }

        return result;
    }


    public int kthSmallest(int[][] matrix, int k) {

        int[] result = merge(matrix, 0, matrix.length-1);
        return result[k-1];
    }

    public void test(){
        System.out.println(kthSmallest(new int[][]{
                { 1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
    }
}
