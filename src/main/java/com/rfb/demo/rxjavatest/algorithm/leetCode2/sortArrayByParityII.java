package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class sortArrayByParityII {

    public int[] sortArrayByParityII(int[] A) {

        int n = A.length;
        int[] result = new int[n];
        int fromOdd = 1;
        int fromEven = 0;
        for(int i = 0; i < n; i++){
            if(A[i] % 2 == 0){
                result[fromEven] = A[i];
                fromEven += 2;
            }else{
                result[fromOdd] = A[i];
                fromOdd += 2;
            }
        }
        return result;
    }

}

