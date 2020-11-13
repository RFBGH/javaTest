package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class lengthOfLIS1 {
    public int lengthOfLIS(int[] nums) {

        if(nums.length == 0){
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int result = 1;
        for(int i = 1; i < n; i++){
            int max = 0;
            for(int j = i-1; j >= 0; j--){
                if(nums[i] > nums[j]){
                    if(max < dp[j]){
                        max = dp[j];
                    }
                }
            }
            dp[i] = max+1;
            if(result < dp[i]){
                result = dp[i];
            }
        }

        return result;
    }

    public void test(){
        System.out.println(lengthOfLIS(new int[]{0}));
    }
}
