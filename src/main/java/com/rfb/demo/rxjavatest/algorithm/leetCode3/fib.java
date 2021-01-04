package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class fib {

    public int fib(int n) {

        if(n < 2){
            return n;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-2]+dp[i-1];
        }
        return dp[n];
    }

    public void test(){
        System.out.println(fib(3));
    }
}
