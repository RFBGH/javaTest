package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class removeKdigits {

    private List<Integer> getSmallestNum(int[] num, int k){

        int start = 0;
        int n = num.length;
        int rest = k;
        List<Integer> result = new ArrayList<>();
        while (true){
            int min = Integer.MAX_VALUE;
            int firstMinIndex = 0;
            for(int i = start; i < n - rest + 1; i++){
               if(num[i] < min){
                   min = num[i];
                   firstMinIndex = i;
               }
            }

            result.add(min);
            start = firstMinIndex+1;
            rest = k - result.size();
            if(rest == 0){
                break;
            }
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
        System.out.println(removeKdigits("10200", 1));
    }

}
