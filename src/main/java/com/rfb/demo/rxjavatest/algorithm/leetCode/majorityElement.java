package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class majorityElement {

    public int majorityElement(int[] nums) {
        int size = nums.length;
        int count = 0;
        int major = 0;
        for(int i = 0; i < size; i++){
            if(count == 0){
                major = nums[i];
            }

            if(major == nums[i]){
                count++;
            }else{
                count--;
            }
        }

        count = 0;
        for(int i = 0; i < size; i++){
            if(nums[i] == major){
                count++;
            }
        }

        if(count > size/2){
            return major;
        }
        return -1;
    }

}
