package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.Arrays;

public class nextPermutation {

    public void nextPermutation(int[] nums) {

        if(nums == null || nums.length == 1){
            return;
        }

        int size = nums.length;
        boolean find = false;
        for(int i = size-1; i >= 0; i--){
            int index = -1;
            int min = Integer.MAX_VALUE;
            for(int j = size-1; j > i; j--){
                if(nums[j] > nums[i] && min > nums[j]){
                    min = nums[j];
                    index = j;
                }
            }

            if(index == -1){
                continue;
            }

            nums[index] = nums[i];
            nums[i] = min;

            int left = i+1;
            int right = size-1;
            while (left < right){
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                left++;
                right--;
            }

            find = true;
            break;
        }

        if(!find){

            int left = 0;
            int right = size-1;
            while (left < right){
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                left++;
                right--;
            }
        }
    }

    public void test(){

        nextPermutation(new int[]{2,3,1,3,3});

    }
}
