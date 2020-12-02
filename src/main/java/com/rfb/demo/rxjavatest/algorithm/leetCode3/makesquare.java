package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.HashMap;
import java.util.Map;

public class makesquare {

    private Map<Integer, Boolean> map = new HashMap<>();

    private boolean dfs(int[] nums, boolean[] gone, int sum, int target, int count){

        int key = 0;
        for(int i = 0; i < gone.length; i++){
            if(gone[i]){
                key++;
            }
            key <<= 1;
        }

        if(map.containsKey(key)){
            return map.get(key);
        }

        if(sum > target){
            return false;
        }

        if(sum == target){
            count++;
            sum = 0;
            if(count == 4){
                map.put(key, true);
                return true;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(gone[i]){
                continue;
            }

            gone[i] = true;
            boolean result = dfs(nums, gone, sum + nums[i], target, count);
            gone[i] = false;

            if(result){
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }

    public boolean makesquare(int[] nums) {

        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        if(sum % 4 != 0){
            return false;
        }

        if(sum / 4 == 0){
            return false;
        }

        boolean[] gone = new boolean[nums.length];
        return dfs(nums, gone, 0, sum/4, 0);
    }

    public void test(){
        System.out.println(makesquare(new int[]{3,3,3,3,4}));
    }
}
