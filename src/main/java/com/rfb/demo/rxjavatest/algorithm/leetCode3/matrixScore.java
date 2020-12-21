package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class matrixScore {

    private Map<Long, Integer> map = new HashMap<>();
    private int dfs(int[][] A, int i, int j, long key){

        if(map.containsKey(key)){
            return map.get(key);
        }

        int max = 0;
        for(int ki = 0; ki < A.length; ki++){
            int sum = 0;
            int base = 1;
            for(int kj = A[0].length-1; kj >= 0; kj--){
                if(A[ki][kj] == 1){
                    sum += base;
                }
                base <<= 1;
            }
            max += sum;
        }

        if(i < A.length){
            int value = dfs(A, i+1, j, key << 2);
            if(max < value){
                max = value;
            }
            for(int k = 0; k < A[0].length; k++){
                A[i][k] = (A[i][k] + 1) % 2;
            }
            value = dfs(A, i+1, j, key<<2 + 1);
            for(int k = 0; k < A[0].length; k++){
                A[i][k] = (A[i][k] + 1) % 2;
            }
            if(max < value){
                max = value;
            }
        }

        if(j < A[0].length){
            int value = dfs(A, i, j+1, key<<2 + 2);
            if(max < value){
                max = value;
            }
            for(int k =0; k < A.length; k++){
                A[k][j] = (A[k][j]+1) % 2;
            }
            value = dfs(A, i, j+1, key <<2 + 3);
            for(int k =0; k < A.length; k++){
                A[k][j] = (A[k][j]+1) % 2;
            }
            if(max < value){
                max = value;
            }
        }

        map.put(key, max);
        return max;
    }

    public int matrixScore(int[][] A) {
        return dfs(A, 0, 0, 1);
    }

    public void test(){
        System.out.println(matrixScore(new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}}));
    }
}
