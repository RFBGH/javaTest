package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Sum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0, size = nums.length; i < size; i++){
            map.put(nums[i], i);
        }

        for(int i = 0, size = nums.length; i < size; i++){
            int rest = target - nums[i];
            if(!map.containsKey(rest)){
                continue;
            }

            int y = map.get(rest);
            if(y == i){
                continue;
            }

            return new int[]{i, y};
        }

        return null;
    }

}
