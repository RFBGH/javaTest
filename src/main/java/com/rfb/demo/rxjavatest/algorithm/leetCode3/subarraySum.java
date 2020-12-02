package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.Map;

public class subarraySum {


    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int n= nums.length;
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for(int i = 0; i < n; i++){
            sum += nums[i];

            if(map.containsKey(sum - k)){
                count += map.get(sum-k);
            }
            
            Integer add = map.get(sum);
            if(add == null){
                add = 1;
            }else{
                add++;
            }
            map.put(sum, add);
        }

        return count;
    }

    public void test(){
        System.out.println(subarraySum(new int[]{1,2,3}, 3));
    }
}
