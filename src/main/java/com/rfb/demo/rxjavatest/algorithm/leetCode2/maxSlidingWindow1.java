package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class maxSlidingWindow1 {

    public int[] maxSlidingWindow(int[] nums, int K) {

        int n = nums.length;
        int resultCount = 0;
        int[] result = new int[n - K + 1];

        int k = 1;
        int count = 0;
        while (k <= n){
            k *= 2;
            count++;
        }

        int[][] dp = new int[count][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = nums[i];
        }

        for(int i = 1; i < count; i++){
            for(int j = 0; j < n; j++){
                if(j + (1<<(i-1)) < n){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j + (1<<(i-1))]);
                }
            }
        }

        k = 1;
        count = 0;
        while (k <= K){
            k *= 2;
            count++;
        }
        count--;

        for(int i = 0; i < n-K+1; i++){
            result[resultCount++] = Math.max(dp[count][i], dp[count][i+K-(1<<count)]);
        }

        return result;
    }

    public void test(){
        maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }
}
