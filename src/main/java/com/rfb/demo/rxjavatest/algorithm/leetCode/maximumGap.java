package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Arrays;

public class maximumGap {
    public int maximumGap(int[] nums) {

        if(nums.length < 2){
            return 0;
        }

        Arrays.sort(nums);
        int max = 0;
        for(int i = 1, size = nums.length; i < size; i++){
            if(nums[i] - nums[i-1] > max){
                max = nums[i] - nums[i-1];
            }
        }
        return max;
    }
}
