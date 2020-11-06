package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class minSubArrayLen3 {

    public int minSubArrayLen(int s, int[] nums) {

        if(nums.length == 0){
            return 0;
        }

        if(nums[0] >= s){
            return 1;
        }

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i = 1, size = nums.length; i < size; i++){

            if(nums[i] >= s){
                return 1;
            }
            sum[i] = sum[i-1] + nums[i];
        }

        if(sum[nums.length-1] < s){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0, size = nums.length; i < size; i++){
            int left = i;
            int right = size-1;
            int mid;
            while (true){

                mid = (left + right) / 2;
                int value = nums[i] + sum[mid]-sum[i];
                if(value == s){
                    break;
                }

                if(left == right){
                    break;
                }

                if(value > s){
                    right = mid;
                }else{
                    left = mid+1;
                }
            }

            int value = nums[i] + sum[mid] - sum[i];
            if(value >= s){
                if(min > mid - i + 1){
                    min = mid - i + 1;
                }
            }

        }
        return min;
    }

    public void test(){
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

}
