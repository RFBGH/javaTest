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
            for(int j = i+1; j < size; j++){
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
            Arrays.sort(nums, i+1, size);

            find = true;
            break;
        }

        if(!find){

            int[] temp = new int[size];
            for(int i = 0; i < size; i++){
                temp[i] = nums[i];
            }

            for(int i = 0; i < size; i++){
                nums[size-1-i] = temp[i];
            }
        }
    }

    public void test(){

        nextPermutation(new int[]{2,3,1});

    }
}
