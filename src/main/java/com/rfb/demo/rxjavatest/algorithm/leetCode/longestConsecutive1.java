package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class longestConsecutive1 {

    public int longestConsecutive(int[] nums) {

        if(nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return 1;
        }

        Map<Integer, Integer> set = new HashMap();
        for(int i = 0, size = nums.length; i < size; i++){
            set.put(nums[i], i);
        }

        Set<Integer> used = new HashSet<>();

        int max = 0;
        for(int i = 0, size = nums.length; i < size; i++){
            if(used.contains(nums[i])){
                continue;
            }

            used.add(nums[i]);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(nums[i]);
            int count = 0;
            while (!queue.isEmpty()){
                count++;
                int cur = queue.poll();
                int next = cur-1;

                Integer nextIndex = set.get(next);
                if(nextIndex != null && !used.contains(next)){
                    used.add(next);
                    queue.add(next);
                }

                next = cur + 1;
                nextIndex = set.get(next);
                if(nextIndex != null && !used.contains(next)){
                    used.add(next);
                    queue.add(next);
                }
            }

            if(count > max){
                max = count;
            }
        }

        return max;
    }

    public void test(){
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
