package com.rfb.demo.rxjavatest.algorithm.RMQ;

import java.util.Random;

/**
 * Created by Administrator on 2020/8/3 0003.
 */
public class RMQ {

    private static int [][] dp;

    public static void init(int[] a, int n){
        dp = new int[30][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = a[i];
        }

        for(int i = 1; (1<<i) <= n; i++){
            for(int j = 0; j + (1<<i) <= n; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j+(1<<(i-1))]);
            }
        }
    }

    public static int maxFromTo(int from, int to){
        double d = Math.log(to-from+1)/Math.log(2);
        int k = (int)d;
        return Math.max(dp[k][from], dp[k][to-(1<<k)+1]);
    }

    public static void test() {

        int n = 20000;
        int[] a = new int[n];//{22, 64, 51, 58, 23, 10, 77, 36, 71, 94, 17, 38, 97, 46, 47, 32, 58, 63, 89, 31};//
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(100000);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
        init(a, n);

        {
            int x = 0;
            int y = 20000-1;

            int max = 0;
            for (int i = x; i <= y; i++) {
                if (a[i] > max) {
                    max = a[i];
                }
            }

            System.out.println("from " + x + " to " + y + " max normal:" + max);
            System.out.println("from " + x + " to " + y + " max:" + maxFromTo(x, y));
        }




        for(int k = 0 ; k < 1000; k++){
            int x = random.nextInt(500);
            int y = random.nextInt(n-500)+500;

            int max =0;
            for(int i = x; i <= y; i++){
                if(a[i] > max){
                    max = a[i];
                }
            }

            int lca = maxFromTo(x, y);

            if(max != lca){
                System.out.println("from "+x+" to "+y+" max normal:"+max);
                System.out.println("from "+x+" to "+y+" max:"+lca);
            }
        }

        System.out.println("done");
    }

}
