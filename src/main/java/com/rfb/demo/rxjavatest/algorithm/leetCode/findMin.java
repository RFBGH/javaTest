package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class findMin {

    public int findMin(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        for(int i = 0, size = nums.length; i < size; i++){
            int last = (i-1);
            if(last == -1){
                last = size-1;
            }

            if(nums[i] < nums[last]){
                return nums[i];
            }
        }

        return nums[0];
    }

}
