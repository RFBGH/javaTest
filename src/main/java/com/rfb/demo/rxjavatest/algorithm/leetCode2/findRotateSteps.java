package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class findRotateSteps {

    public int findRotateSteps(String ring, String key) {

        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            if(ring.charAt(i) == key.charAt(0)){
                int step = i;
                if(step > n - step){
                    step = n - step;
                }
                step++;

                dp[i][0] = step;
            }
        }

        for(int j = 1; j < m; j++){

            for(int i = 0; i < n; i++){
                if(dp[i][j-1] == 0){
                    continue;
                }

                for(int k = 0; k < n; k++){
                    if(ring.charAt(k) == key.charAt(j)){
                        int step = Math.abs(k - i);
                        if(step > n - step){
                            step = n - step;
                        }
                        step++;
                        step += dp[i][j-1];

                        if(dp[k][j] != 0 ){
                            if(dp[k][j] > step) {
                                dp[k][j] = step;
                            }
                        }else{
                            dp[k][j] = step;
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(dp[i][m-1] != 0 && dp[i][m-1] < min){
                min = dp[i][m-1];
            }
        }

        return min;
    }

    public void test(){
//        System.out.println(findRotateSteps("abcde", "ade"));
        System.out.println(findRotateSteps("godding", "gd"));
    }
}
