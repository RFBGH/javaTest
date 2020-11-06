package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class longestConsecutive {

    public int longestConsecutive(int[] nums) {

        if(nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return 1;
        }

        int max = 0;

        Arrays.sort(nums);
        int count = 1;
        for(int i = 1, size = nums.length; i < size; i++){
            if(nums[i] == nums[i-1]){
                //do nothing...
            }else if(nums[i] - nums[i-1] == 1){
                count++;
            }else{
                if(count > max){
                    max = count;
                }
                count = 1;
            }
        }

        if(count > max){
            max = count;
        }

        return max;
    }

    public void test(){
        System.out.println(longestConsecutive(new int[]{1,2,0,1}));
    }
}
