package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class splitIntoFibonacci {


    private boolean dfs(List<Integer> list, int index, String S){

        if(index == S.length()){
            return true;
        }

        int sum = list.get(list.size()-1) + list.get(list.size()-2);

        int temp = 0;
        int i;
        for(i = index; i < S.length(); i++){
            temp *= 10;
            temp += S.charAt(i) - '0';
            if(temp == sum){
                break;
            }

            if(temp > sum){
                return false;
            }
        }

        if(i == S.length()){
            return false;
        }

        list.add(sum);
        return dfs(list, i+1, S);
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
        splitIntoFibonacci("123456579");
    }
}
