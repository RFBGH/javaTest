package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class maxCoins {

    private int dfs(int[]nums, int left, int right){

        if(left == right){
            return nums[left];
        }

        if(left + 1 == right){
            if(nums[left] > nums[right]){
                return nums[left]*nums[right] + nums[left];
            }else{
                return nums[left]*nums[right] + nums[right];
            }
        }

        int temp = 0;
        for(int i = left+1; i < right; i++){

            int valueLeft = dfs(nums, left, i-1);
            int valueRight = dfs(nums, i+1, right);
            int value = nums[i-1]*nums[i+1]*nums[i];
            int all = valueLeft + valueRight + value;
            if(temp < all){
                temp = all;
            }
        }

        return temp;
    }

    public int maxCoins(int[] nums) {
        return dfs(nums, 0, nums.length-1);
    }

    public void test(){
        System.out.println(maxCoins(new int[]{3,1,5,8}));
    }

}
