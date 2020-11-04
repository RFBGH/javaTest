package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class maxProfit {

    public int maxProfit(int[] prices) {

        if(prices == null || prices.length == 1){
            return 0;
        }

        List<Integer> cut = new ArrayList<>();

        int sum = 0;
        for(int i = 1, size = prices.length; i < size; i++){
            if(prices[i] > prices[i-1]){
                sum += prices[i] - prices[i-1];
            }else{

            }
        }
        return sum;
    }

}
