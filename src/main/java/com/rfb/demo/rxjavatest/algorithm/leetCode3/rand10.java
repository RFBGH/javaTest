package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class rand10 {

    public int rand10() {

        int sum = 0;
        for(int i = 0; i < 10; i++){
            sum += rand7();
        }

        return sum / 7;
    }

    public int rand7() {
        return 1;
    }
}
