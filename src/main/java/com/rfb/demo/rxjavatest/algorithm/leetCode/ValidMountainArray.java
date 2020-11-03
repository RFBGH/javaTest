package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class ValidMountainArray {

    public boolean validMountainArray(int[] A) {

        if(A.length < 3){
            return false;
        }

        int i = 0;
        int size = A.length;
        while (i != size - 1 && A[i] < A[i + 1]) {
            i++;
        }

        if(i == size-1 || i == 0){
            return false;
        }

        while (i != size-1){
            if(A[i] <= A[i+1]){
                return false;
            }
            i++;
        }
        return true;
    }

    public void test(){
        System.out.println(validMountainArray(new int[]{0,3,2,1}));
    }
}
