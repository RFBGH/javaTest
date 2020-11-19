package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class moveZeroes1 {

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int zeroCount = 0;
        for(int i = 0; i < n-zeroCount; i++){
            if(nums[i] != 0){
                continue;
            }
            for(int j = i; j < n-1-zeroCount; j++){
                nums[j] = nums[j+1];
            }
            nums[n-1-zeroCount] = 0;
            zeroCount++;
            i--;
        }
    }

    public void test(){
        moveZeroes(new int[]{0,0,1});
    }
}
