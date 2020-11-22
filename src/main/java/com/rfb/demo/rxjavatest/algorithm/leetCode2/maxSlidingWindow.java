package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class maxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int K) {

        int n = nums.length;
        Queue<Integer> queue = new LinkedList<>();

        int resultCount = 0;
        int[] result = new int[n-K+1];

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){

            if(queue.size() < K){
                if(nums[i] > max){
                    max = nums[i];
                    queue.clear();
                }
                queue.offer(nums[i]);
            }else{
                if(nums[i] > max){
                    max = nums[i];
                    queue.clear();
                }else{
                    queue.poll();
                    max = Integer.MIN_VALUE;
                    if(!queue.isEmpty()){
                        for(Integer cur : queue){
                            if(cur > max){
                                max = cur;
                            }
                        }
                        while (queue.peek() != max){
                            queue.poll();
                        }
                    }

                    if(nums[i] > max){
                        max = nums[i];
                        queue.clear();
                    }
                }
                queue.offer(nums[i]);
            }

            if(i >= K-1){
                result[resultCount++] = max;
            }
        }
        return result;
    }

    public void test(){
        maxSlidingWindow(new int[]{9,10,9,-7,-4,-8,2,-6}, 5);
    }
}
