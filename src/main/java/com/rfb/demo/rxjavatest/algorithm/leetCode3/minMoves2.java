package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class minMoves2 {

    public int minMoves2(int[] nums) {

        if(nums == null || nums.length == 1){
            return 0;
        }



        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            Integer count = map.get(nums[i]);
            if(count == null){
                count = 1;
            }else{
                count = count+1;
            }

            map.put(nums[i], count);
        }

        long min = Integer.MAX_VALUE;
        Set<Integer> keys = map.keySet();
        for(Integer i : keys){
            long sum = 0;
            for(Integer k : keys){
                sum += Math.abs((k - i)) * map.get(k);
                if(sum > min){
                    break;
                }

            }
            if(sum < min){
                min = sum;
            }
        }
        return (int)min;
    }


    public int minMoves(int[] nums) {

        if(nums == null || nums.length == 1){
            return 0;
        }

        Arrays.sort(nums);

        int n = nums.length;
        int mid = n / 2;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += Math.abs(nums[i] - nums[mid]);
        }
        return sum;
    }

    public void test(){
        System.out.println(minMoves2(new int[]{1,2,2,3,3,3,3,5,5,5}));
        System.out.println(minMoves(new int[]{1,2,2,3,3,3,3,5,5,5}));
    }
}
