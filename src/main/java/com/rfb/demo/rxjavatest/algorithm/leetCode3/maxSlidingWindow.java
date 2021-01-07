package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class maxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        int left = 0;
        stack.push(0);
        for(int i = 1; i < nums.length; i++){

            if(i - left == k){

                int first = stack.get(0);
                result.add(nums[first]);

                if(first == left){
                    stack.remove(0);
                }
                left++;
            }

            while (!stack.isEmpty() ){
                int last = stack.pop();
                if(nums[last] > nums[i]){
                    stack.push(last);
                    break;
                }
            }
            stack.push(i);
        }
        result.add(nums[stack.get(0)]);

        int[] ans = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            ans[i] = result.get(i);
        }

        return ans;
    }

    public void test(){
        int[] ans = maxSlidingWindow(new int[]{9, 11}, 2);
        for(int i : ans){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
