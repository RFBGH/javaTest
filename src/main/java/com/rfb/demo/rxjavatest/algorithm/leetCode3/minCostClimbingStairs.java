package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class minCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < n; i++){
            if(dp[i-1] > dp[i-2]){
                dp[i] = dp[i-2]+cost[i];
            }else{
                dp[i] = dp[i-1]+cost[i];
            }
        }

        if(dp[n-2] < dp[n-1]){
            return dp[n-2];
        }

        return dp[n-1];
    }

    public void test(){
        System.out.println(minCostClimbingStairs(new int[]{0,0,1,1}));
    }
}
