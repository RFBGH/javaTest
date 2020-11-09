package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class missingTwo {

    public int[] missingTwo(int[] nums) {

        int size = nums.length + 2;
        int sum = (size + 1)*size/2;
        for(int i : nums){
            sum -= i;
        }

        int find = -1;
        for(int i = 1, len = nums.length; i < len; i++){
            if(nums[i] != nums[i-1]+1){
                break;
            }

        }
        return null;
    }
}
