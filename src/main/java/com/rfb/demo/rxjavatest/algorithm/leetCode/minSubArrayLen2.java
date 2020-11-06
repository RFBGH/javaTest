package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class minSubArrayLen2 {

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

        int min = Integer.MAX_VALUE;

        int to = 0;
        int size = nums.length;
        while (to < size){

            if(sum[to] >= s){
                break;
            }

            to++;
        }

        if(to == size){
            return 0;
        }

        min = to + 1;
        int from = 0;
        while (true){

            while (to < size){
                if (sum[to] - sum[from] + nums[from] >= s) {
                    break;
                }
                to++;
            }

            if(to == size){
                break;
            }

            while (sum[to] - sum[from] + nums[from] >= s){
                if(to - from + 1 < min){
                    min = to - from + 1;
                }
                from++;
            }
        }

        return min;
    }

    public void test(){
        System.out.println(minSubArrayLen(11, new int[]{1,2,3,4,5}));
    }

}
