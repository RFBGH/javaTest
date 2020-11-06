package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class rob {

    public int rob(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }

        int n = nums.length;
        int[] dp = new int[n];
        dp[1] = nums[1];
        for(int i = 1; i < n-1; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i+1]);
        }

        int max = dp[n-1];
        dp = new int[n];
        dp[0] = nums[0];
        dp[1] = dp[0];
        for(int i = 1; i < n-2; i++){
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i+1]);
        }

        if(max < dp[n-2]){
            max = dp[n-2];
        }
        return max;
    }

    public void test(){
        System.out.println(rob(new int[]{2,3,2}));

//        System.out.println(rob(new int[]{2,3,2}));
    }

}
