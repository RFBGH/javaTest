package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class PredictTheWinner {

    private boolean minMax(int[] nums, int left, int right, boolean isMe, int sumMe, int sumOther){

        if(left > right){
            return sumMe >= sumOther;
        }

        if(isMe){
            if(minMax(nums, left+1, right, false, sumMe+nums[left], sumOther)){
                return true;
            }
            if(minMax(nums, left, right-1, false, sumMe+nums[right], sumOther)){
                return true;
            }
            return false;
        }else{
            if(!minMax(nums, left+1, right, true, sumMe, sumOther+nums[left])){
                return false;
            }
            if(!minMax(nums, left, right-1, true, sumMe, sumOther+nums[right])){
                return false;
            }
            return true;
        }
    }

    public boolean PredictTheWinner(int[] nums) {

        if(nums.length == 1 || nums.length == 0){
            return true;
        }

        return minMax(nums, 0, nums.length-1, true, 0, 0);
    }

    public void test(){
        System.out.println(PredictTheWinner(new int[]{1, 1}));
    }
}
