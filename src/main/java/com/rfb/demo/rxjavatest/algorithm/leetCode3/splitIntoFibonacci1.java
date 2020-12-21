package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class splitIntoFibonacci1 {


    private boolean dfs(List<Integer> list, int index, String S){

        if(index == S.length()){
            return true;
        }

        int sum = list.get(list.size()-1) + list.get(list.size()-2);
        int temp = sum;
        StringBuilder sb = new StringBuilder();

        while(true){
            sb.append((char)('0'+sum%10));
            sum /= 10;

            if(sum == 0){
                break;
            }
        }


        for(int i = sb.length()-1; i >= 0; i--){

            if(index >= S.length() || sb.charAt(i) != S.charAt(index++)){
                return false;
            }
        }

        list.add(temp);
        return dfs(list, index, S);
    }


    public List<Integer> splitIntoFibonacci(String S) {

        List<Integer> result = new ArrayList<>();
        int start0 = 0;
        for(int i = 0; i < S.length()-2; i++){

            start0 *= 10;
            start0 += S.charAt(i)-'0';

            int start1 = 0;
            for(int j = i+1; j < S.length()-1; j++){

                start1 *= 10;
                start1 += S.charAt(j) - '0';

                result.clear();
                result.add(start0);
                result.add(start1);
                if(dfs(result, j+1, S)){
                    return result;
                }

                if(S.charAt(i+1) == '0'){
                    break;
                }
            }

            if(S.charAt(0) == '0'){
                break;
            }
        }

        result.clear();
        return result;
    }

    public void test(){
        splitIntoFibonacci("0000");
    }
}
