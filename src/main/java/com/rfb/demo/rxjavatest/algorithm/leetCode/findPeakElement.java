package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class findPeakElement {

    public int findPeakElement(int[] nums) {

        for(int i = 0, size = nums.length; i < size; i++){
            int last = i-1;
            int next = i+1;
            if(last >= 0){
                if(nums[last] > nums[i]){
                    continue;
                }
            }

            if(next < size){
                if(nums[next] > nums[i]){
                    continue;
                }
            }

            return i;
        }

        return 0;
    }
}
