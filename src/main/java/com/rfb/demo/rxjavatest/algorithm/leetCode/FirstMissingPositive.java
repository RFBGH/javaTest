package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.Arrays;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        if(nums.length == 0){
            return 1;
        }

        Arrays.sort(nums);

        if(nums[nums.length-1] <= 0){
            return 1;
        }

        if(nums[0] > 1){
            return 1;
        }

        for(int i = 0, size = nums.length; i < size-1; i++){

            int start = nums[i]+1;
            int end = nums[i+1];
            if(start <= 0){
                start = 1;
            }

            if(start >= end){
                continue;
            }

            return start;
        }

        return nums[nums.length-1]+1;
    }

    public void test(){
        System.out.println(firstMissingPositive(new int[]{7,8,9,11,12}));
    }


}
