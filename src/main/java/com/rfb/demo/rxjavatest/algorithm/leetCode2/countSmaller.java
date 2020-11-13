package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class countSmaller {

    private int N;
    private int tree[];

    private int lowBit(int k){
        return k & -k;
    }

    private void update(int k, int num){
        while (k <= N){
            tree[k] += num;
            k += lowBit(k);
        }
    }

    private int request(int k){
        int sum = 0;
        while (k > 0){
            sum += tree[k];
            k -= lowBit(k);
        }
        return sum;
    }

    public List<Integer> countSmaller(int[] nums) {

        if(nums.length == 0){
            return new ArrayList<>();
        }

        N = 20005;
        tree = new int[N];

        int n = nums.length;
        for(int i = 0; i < n; i++){
            nums[i] += 10001;
        }

        int[] count = new int[n];
        for(int i = n-1; i >= 0; i--){
            count[i] = request(nums[i]-1);
            update(nums[i], 1);
        }

        List<Integer> result = new ArrayList<>();
        for(int c : count){
            result.add(c);
        }
        return result;
    }

    public void test(){
        countSmaller(new int[]{5,2,6,1});
    }
}
