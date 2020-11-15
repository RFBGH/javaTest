package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class removeKdigits2 {

    private List<Integer> getSmallestNum(int[] num, int K){

        int start = 0;
        int n = num.length;
        List<Integer> result = new ArrayList<>();
        for(int k = 0; k < K; k++){

            int min = Integer.MAX_VALUE;
            int firstMinIndex = 0;
            for(int i = start, size = n - K + 1 + k; i < size; i++){
                if(num[i] < min){
                    min = num[i];
                    firstMinIndex = i;
                }
            }

            result.add(min);
            start = firstMinIndex+1;
        }

        return result;
    }

    public String removeKdigits(String s, int k) {

        int n = s.length();
        k = n - k;
        if(k == 0){
            return "0";
        }

        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = s.charAt(i) - '0';
        }

        List<Integer> result = getSmallestNum(nums, k);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++){
            int v = result.get(i);
            if(sb.length() == 0 && v == 0){
                continue;
            }
            sb.append(v);
        }

        if(sb.length() == 0){
            sb.append("0");
        }
        return sb.toString();
    }

    public void test(){
        System.out.println(removeKdigits("1432219", 3));
    }

}
