package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class numberOfArithmeticSlices {

    public int numberOfArithmeticSlices(int[] A) {

        int n = A.length;
        if(n == 0 || n == 1 || n == 2){
            return 0;
        }

        int sum = 0;
        int cut = A[1] - A[0];
        int len = 2;
        for(int i = 2; i < n; i++){
            if(A[i]-A[i-1] == cut){
                len++;
            }else{

                if(len > 2){
                    for(int k = 3; k <= len; k++){
                        sum += len - k + 1;
                    }
                }

                cut = A[i]-A[i-1];
                len = 2;
            }
        }

        if(len > 2){
            for(int k = 3; k <= len; k++){
                sum += len - k + 1;
            }
        }

        return sum;
    }

    public void test(){
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    }
}
