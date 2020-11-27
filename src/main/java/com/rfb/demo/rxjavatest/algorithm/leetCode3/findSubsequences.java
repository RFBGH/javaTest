package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class findSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        List<List<Integer>>[] dp = new ArrayList[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            dp[i].add(list);
        }

        for(int i = 1; i < nums.length; i++){

            Set<Integer> set = new HashSet<>();
            for(int j = i-1; j >= 0; j--){
                if(nums[i] >= nums[j]){

                    if(set.contains(nums[j])){
                        continue;
                    }
                    set.add(nums[j]);

                    for(List<Integer> list : dp[j]){
                        List<Integer> newList = new ArrayList<>(list);
                        newList.add(nums[i]);
                        dp[i].add(newList);
                    }
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = nums.length-1; i >= 0; i--){

            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);

            for(List<Integer> list : dp[i]){

                if(list.size() == 1){
                    continue;
                }

                result.add(list);
            }
        }

        return result;
    }

    public void test(){
        findSubsequences(new int[]{1,1,1,2,2,2});
    }
}
