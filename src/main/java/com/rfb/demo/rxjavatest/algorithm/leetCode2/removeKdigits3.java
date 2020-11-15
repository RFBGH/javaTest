package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class removeKdigits3 {

    private List<Integer> getSmallestNum(int[] num, int k){

        int start = 0;
        int n = num.length;
        int rest = k;
        List<Integer> result = new ArrayList<>();
        while (true){
            int min = Integer.MAX_VALUE;
            List<Integer> indexs = new ArrayList<>();
            for(int i = start; i < n - rest + 1; i++){
               if(num[i] < min){
                   min = num[i];
                   indexs.clear();
                   indexs.add(i);
               }else if(num[i] == min){
                   indexs.add(i);
               }
            }

            result.add(min);
            start = indexs.get(0) + 1;

            int i = 1;
            int j =  n - rest + 1;
            while (i < indexs.size() && j < n){
                if(num[j] >= min){
                    result.add(min);
                }else{
                    while (j < n){
                        result.add(num[j++]);
                    }
                }

                if(result.size() == k){
                    return result;
                }

                i++;
                j++;
            }

            if(j == n){
                while (i < indexs.size()){
                    result.add(min);
                    if(result.size() == k){
                        return result;
                    }
                    i++;
                }
            }

            if(i == indexs.size()){
                start = indexs.get(indexs.size()-1)+1;
            }

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
        System.out.println(removeKdigits("1432219", 3));
    }

}
