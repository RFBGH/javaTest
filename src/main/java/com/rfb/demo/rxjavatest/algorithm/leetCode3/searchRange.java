package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class searchRange {

    public int[] searchRange(int[] nums, int target) {

        if(nums.length == 0){
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = nums.length - 1;
        while (true){

            if(left == right){
                break;
            }

            int mid = (left + right)/2;
            if(nums[mid] < target){
                left = mid+1;
            }else{
                right = mid;
            }
        }

        if(nums[left] != target){
            return new int[]{-1, -1};
        }

        for(right = left + 1; right < nums.length; right++){
            if(nums[right] != nums[left]){
                break;
            }
        }

        return new int[]{left, right-1};
    }

    public void test(){

        searchRange(new int[]{5,7,7,8,8,10}, 8);
    }
}
