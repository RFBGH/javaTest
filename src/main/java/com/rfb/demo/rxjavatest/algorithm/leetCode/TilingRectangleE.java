package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class TilingRectangleE {

    private int map[][];

    private int dfs(int n, int m){

        if(n == 0 || m == 0){
            return 0;
        }

        if(n == m){
            return 1;
        }

        if(n > m){
            int t = m;
            m = n;
            n = t;
        }

        if(map[n][m] != 0){
            return map[n][m];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){

            int t = dfs(i, n-i)+dfs(n, m-i)+1;
            if(t < min){
                min = t;
            }

            int t2 = dfs(i, n-i)+dfs(i, m-i)+dfs(n-i, m-i)+1;
            if(t2 < min){
                min = t2;
            }

            int t3 = dfs(i, m-i)+dfs(n-i, m)+1;
            if(t3 < min){
                min = t3;
            }
        }

        map[n][m] = min;
        return min;
    }

    public int tilingRectangle(int n, int m) {

        if(n > m){
            int t = m;
            m = n;
            n = t;
        }

        map = new int[n+5][m+5];

        for(int i = 1; i <= n; i++){
            map[i][i] = 1;
            map[i][1] = i;
        }

        for(int i = 1; i <= m; i++){
            map[1][i] = i;
        }

        return dfs(n, m);
    }

    public void test(){


        System.out.println(tilingRectangle(11, 13));
        System.out.println(tilingRectangle(5, 8));

    }
}
