package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.Map;

public class fourSumCount {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        int n = A.length;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int temp = C[i] + D[j];
                Integer count = map.get(temp);
                if(count == null){
                    count = 1;
                }else{
                    count++;
                }
                map.put(temp, count);
            }
        }

        int sum = 0;
        for(int i = 0;  i < n; i++){
            for(int j = 0; j < n; j++){
                int temp = A[i] + B[j];
                Integer count = map.get(-temp);
                if(count == null){
                    count = 0;
                }
                sum += count;
            }
        }
        return sum;
    }

}
