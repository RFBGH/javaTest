package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2020/7/17 0017.
 */
public class DP_MaxMulti{

    public static int calc(List<Integer> a){


        int n = a.size();
        int dp[][] = new int[n][2];

        dp[0][0] = a.get(0);
        dp[0][1] = a.get(0);
        int ans = dp[0][0];

        for(int i = 1; i < n; i++){

            int value = a.get(i);

            if(value > 0){
                dp[i][0] = Math.max(dp[i-1][0] * value, value);
                dp[i][1] = Math.min(dp[i-1][1] * value, value);
            }else{

                dp[i][0] = Math.max(dp[i-1][1] * value, value);
                dp[i][1] = Math.min(dp[i-1][0] * value, value);
            }

            if(ans < dp[i][0]){
                ans = dp[i][0];
            }
        }

        return ans;
    }

    public static void test(){

        int n = 10000;
        Random random = new Random(System.currentTimeMillis());
//        Integer[] array = new Integer[]{83, -63, -67, -68, 23, -18, 83, 24, -77, -42};
        List<Integer> a = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int value = random.nextInt(100);

            if(random.nextInt(10) < 4){
                value = -value;
            }
            a.add(value);
        }
    }



}
