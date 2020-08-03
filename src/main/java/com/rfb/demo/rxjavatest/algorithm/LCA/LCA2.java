package com.rfb.demo.rxjavatest.algorithm.LCA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/3 0003.
 */
public class LCA2 {

    private static List<Integer>[] G;
    private static int n;
    private static int[] deeps;
    private static int[] parents;
    private static int[][] dp;

    private static void dfs(int cur, int parent, int deep){

        parents[cur] = parent;
        deeps[cur] = deep;

        List<Integer> sons = G[cur];
        for(int son:sons){
            if(son == parent){
                continue;
            }

            dfs(son, cur, deep+1);
        }
    }

    public static void initDp(){

        dp = new int[30][n];

        for(int i = 0; i < n; i++){
            dp[0][i] = parents[i];
        }

        for(int i = 1; (1 << i) <= n; i++){
            for(int j = 1; j < n; j++){
                if(dp[i-1][j] < 0){
                    dp[i][j] = -1;
                }else{
                    dp[i][j] = dp[i-1][dp[i-1][j]];
                }
            }
        }
    }

    public static int LCA1(int x, int y){

        int deepX = deeps[x];
        int deepY = deeps[y];

        while (deepX > deepY){
            x = parents[x];
            deepX = deeps[x];
        }

        while (deepY > deepX){
            y = parents[y];
            deepY = deeps[y];
        }

        while (x != y){
            x = parents[x];
            y = parents[y];
        }

        return x;
    }

    public static int LCA2(int x, int y){
        if(deeps[x] < deeps[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        int length = deeps[x] - deeps[y];
        int k = 0;
        for(k = 0; (1<<k) <= length; k++);
        k--;

        for(int i = k; i >= 0; i--){
            if(length >= (1 << i)){
                x = dp[i][x];
                length = deeps[x] - deeps[y];
            }
        }

        if(x == y){
            return x;
        }

        for(k = 0; (1<<k) <= n; k++);
        k--;
        for(int i = k ;i >= 0; i--){
            if(dp[i][x] != dp[i][y]){
                x = dp[i][x];
                y = dp[i][y];
            }
        }

        return parents[x];
    }

    public static void init(int count){
        count++;
        n = count;
        deeps = new int[n];
        parents = new int[n];
        G = new ArrayList[n];
        for(int i = 0; i < n; i++){
            deeps[i] = 0;
            parents[i] = -1;
            G[i] = new ArrayList<>();
        }
    }

    public static void addEdge(int u, int v){
        G[u].add(v);
    }

    public static void test(){

        init(12);

        addEdge(1, 2);
        addEdge(1, 7);
        addEdge(2, 3);
        addEdge(2, 4);
        addEdge(4, 5);
        addEdge(4, 6);
        addEdge(7, 8);
        addEdge(8, 9);
        addEdge(9, 10);
        addEdge(9, 11);
        addEdge(10, 12);

        dfs(1, -1, 0);

        System.out.println(LCA1(4, 8));
        System.out.println(LCA1(6, 10));
        System.out.println(LCA1(4, 6));

        initDp();

        System.out.println(LCA2(4, 8));
        System.out.println(LCA2(6, 11));
        System.out.println(LCA2(4, 6));
    }
}
