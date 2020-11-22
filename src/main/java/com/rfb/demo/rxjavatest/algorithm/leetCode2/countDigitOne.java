package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class countDigitOne {

    public int countDigitLess100(int n) {

        if(n < 10){
            return 1;
        }

        if(n < 20){
            return n-8;
        }

        if(n < 100){
            n = n - 20;
            int lastN = n % 10;
            if(lastN > 1){
                lastN = 1;
            }
            return 12 + n / 10 + lastN;
        }
        return 0;
    }

    public int countDigitOne(int n) {

        if(n < 10){
            return 1;
        }

        if(n < 20){
            return n-8;
        }

        if(n < 100){
            n = n - 20;
            int lastN = n % 10;
            if(lastN > 1){
                lastN = 1;
            }
            return 12 + n / 10 + lastN;
        }

        return 0;
    }
}
