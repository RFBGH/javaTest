package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class minPatches {

    public int minPatches(int[] nums, int n) {
        int i = 0, count = 0;
        long add = 1;
        while(add <= n){
            if(i < nums.length && nums[i] <= add) add += nums[i++];
            else {
                add += add;
                count ++;
            }
        }
        return count;
    }

    public void test(){
        System.out.println(minPatches(new int[]{1,3}, 6));
        System.out.println(minPatches(new int[]{1,5,10}, 20));
        System.out.println(minPatches(new int[]{1,2,2}, 5));
        System.out.println(minPatches(new int[]{}, 7));
    }
}
