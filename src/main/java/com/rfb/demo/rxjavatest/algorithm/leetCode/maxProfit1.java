package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class maxProfit1 {

    public int maxProfit(int[] prices) {

        int k = 2;
        int n = prices.length;
        if(k >= n/2){
            int sum = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i] - prices[i-1] > 0){
                    sum += prices[i] - prices[i-1];
                }
            }
            return sum;
        }

        int[][] dp = new int[k+1][2];
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
            dp[i][1] = - prices[0];
        }

        for(int i = 1; i < n; i++){
            for(int j = k; j > 0; j--){
                dp[j-1][1] = Math.max(dp[j-1][1], dp[j-1][0]-prices[i]);
                dp[j][0] = Math.max(dp[j][0], dp[j-1][1]+prices[i]);
            }
        }

        return dp[k][0];
    }

}
