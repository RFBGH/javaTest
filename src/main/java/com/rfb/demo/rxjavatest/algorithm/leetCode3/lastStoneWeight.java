package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.Arrays;

public class lastStoneWeight {

    public int lastStoneWeight(int[] stones) {

        if(stones.length == 0){
            return 0;
        }

        if(stones.length == 1){
            return stones[0];
        }

        int lastIndex = stones.length;
        while (true){
            Arrays.sort(stones, 0, lastIndex);
            stones[lastIndex-2] = Math.abs(stones[lastIndex-2]-stones[lastIndex-1]);
            lastIndex--;

            if(lastIndex == 1){
                break;
            }
        }

        return stones[0];
    }

    public void test(){
        System.out.println(lastStoneWeight(new int[]{2,7,4,1,8,1}));
    }
}
