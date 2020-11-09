package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class maxProfit1 {

    public int maxProfit(int[] prices) {

        int sum = 0;
        for(int i = 1, size = prices.length; i < size; i++){
            if(prices[i-1] < prices[i]){
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }

}
