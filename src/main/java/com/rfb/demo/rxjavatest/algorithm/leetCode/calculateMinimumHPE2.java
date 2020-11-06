package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class calculateMinimumHPE2 {

    public int calculateMinimumHP(int[][] dungeon) {

        int[][] dp = new int[dungeon.length][dungeon[0].length];
        int[][] dp2 = new int[dungeon.length][dungeon[0].length];

        dp[0][0] = dungeon[0][0];

        if(dungeon[0][0] < 0){
            dp2[0][0] = dungeon[0][0];
        }

        for(int i = 1, n = dungeon.length; i < n; i++){
            dp[i][0] = dp[i-1][0] + dungeon[i][0];
            if(dp2[i-1][0] > dp[i][0]){
                dp2[i][0] = dp[i][0];
            }else{
                dp2[i][0] = dp2[i-1][0];
            }
        }

        for(int j = 1, m = dungeon[0].length; j < m; j++){
            dp[0][j] = dp[0][j-1] + dungeon[0][j];
            if(dp2[0][j-1] > dp[0][j]){
                dp2[0][j] = dp[0][j];
            }else{
                dp2[0][j] = dp2[0][j-1];
            }
        }

        for(int i = 1, n = dungeon.length; i < n; i++){
            for(int j = 1, m = dungeon[0].length; j < m; j++){

                int a = dp2[i][j-1];
                int b = dp2[i-1][j];

                int c = dp[i][j-1] + dungeon[i][j];
                int d = dp[i-1][j] + dungeon[i][j];

                int t1 = a;
                if(a > c){
                    t1 = c;
                }

                int t2 = b;
                if(b > d){
                    t2 = d;
                }

                if(t1 > t2){
                    dp2[i][j] = t1;
                    dp[i][j] = c;
                }else{
                    dp2[i][j] = t2;
                    dp[i][j] = d;
                }
            }
        }

        return -dp2[dungeon.length-1][dungeon[0].length-1]+1;
    }

    public void test(){
        System.out.println( calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));
    }

}
