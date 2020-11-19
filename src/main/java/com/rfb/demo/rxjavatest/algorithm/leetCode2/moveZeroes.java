package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class moveZeroes {

    public void moveZeroes(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        int resultCount = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                result[resultCount++] = nums[i];
            }
        }

        for(int i = 0; i < n; i++){
            nums[i] = result[i];
        }
    }
}
