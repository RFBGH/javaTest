package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class countRangeSum1 {

    public int countRangeSum(int[] nums, int lower, int upper) {

        if(nums.length == 0){
            return 0;
        }

        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for(int i = 1, size = nums.length; i < size; i++){
            sum[i] = sum[i-1] + nums[i];
        }

        int result = 0;
        for(int i = 0, size = nums.length; i < size; i++){
            for(int j = i;j < size; j++){

                long t = sum[j]-sum[i]+nums[i];
                if(t <= upper && t >= lower){
                    result ++;
                }

            }
        }
        return result;
    }

    public void test(){

        System.out.println(countRangeSum(new int[]{-2147483647,0,-2147483647,2147483647}, -564, 3864));

    }
}
