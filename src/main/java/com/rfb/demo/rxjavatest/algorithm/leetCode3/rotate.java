package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class rotate {

    public void rotate1(int[] nums, int k) {

        while (k > 0){
            int temp = nums[nums.length-1];
            for(int i = nums.length-1; i > 0; i--){
                nums[i] = nums[i-1];
            }
            nums[0] = temp;
            k--;
        }
    }

    public void rotate(int[] nums, int k) {
        int[] temp = new int[k];
        for(int i = nums.length-k-1; i < nums.length; i++){
            temp[i-nums.length+k+1] = nums[i];
        }

        for(int i = nums.length-1; i > k-1; i++){
            nums[i] = nums[i-k];
        }

        for(int i = 0; i < k; i++){
            nums[i] = temp[k-1-i];
        }
    }
}
