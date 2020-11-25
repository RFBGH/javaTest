package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class numberOfArithmeticSlicesQ2 {

    private int numberOfArithmetic(List<Integer> list){

        int sum = 0;
        int size = list.size();

        int K = size/3;
        if(size % 3 != 0){
            K++;
        }

        for(int k = 1; k <= K; k++){
            int n = size / k;
            if(size % k != 0){
                n++;
            }

            sum += ((n-1) * (n-2) / 2) * (size%k == 0?k:size%k);
        }

        return sum;
    }

    public int numberOfArithmeticSlices(int[] A) {

        int sum = 0;
        int n = A.length;

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);


        System.out.println(numberOfArithmetic(list));
        return 0;
    }

    public void test(){
        System.out.println(numberOfArithmeticSlices(new int[]{}));
    }
}
