package com.rfb.demo.rxjavatest.algorithm.niuke;

import java.util.HashMap;
import java.util.Map;

public class LCS {

    public String LCS (String str1, String str2) {
        // write code here
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n][m];
        int max = 0;
        int start = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    if(i > 0 && j > 0){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else{
                        dp[i][j] = 1;
                    }
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        start = i;
                    }
                }
            }
        }

        if(max == 0){
            return "-1";
        }
        return str1.substring(start+1-max, start+1);
    }

    public void test(){
        System.out.println(LCS("eeeabc","abeeecd"));
    }

}
