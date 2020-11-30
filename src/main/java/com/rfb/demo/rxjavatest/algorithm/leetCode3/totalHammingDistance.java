package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class totalHammingDistance {

    public int totalHammingDistance(int[] nums) {


        int[][] count = new int[32][2];
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            for(int j = 0; j < 32; j++){

                if((num & 0x1) == 1){
                    count[j][1]++;
                }else{
                    count[j][0]++;
                }

                num >>= 1;
            }
        }

        int sum = 0;

        for(int i = 0; i < 32; i++){
            sum += count[i][0]*count[i][1];
        }

        return sum;
    }

    public void test(){
        System.out.println(totalHammingDistance(new int[]{4, 14, 2}));
    }
}
