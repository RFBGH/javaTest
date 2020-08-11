package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/11 0011.
 */
public class TreeGravityCenter {

    private List<Integer>[] G;
    private int n;
    private int[] nodeCounts;
    private int gravityIndex = -1;
    private int gravityValue = Integer.MAX_VALUE;

    public void init(int n){
        this.n = n;
        G = new ArrayList[n];
        nodeCounts = new int[n];
        for(int i = 0; i < n; i++){
            G[i] = new ArrayList<>();
            nodeCounts[i] = 0;
        }
    }

    public void addEdge(int u, int v){
        G[u].add(v);
        G[v].add(u);
    }

    public void dfs(int cur, int parent){

        int max = Integer.MIN_VALUE;

        nodeCounts[cur] = 1;
        List<Integer> edges = G[cur];
        for(Integer next:edges){
            if(next == parent){
                continue;
            }

            dfs(next, cur);
            nodeCounts[cur] += nodeCounts[next];

            if(max < nodeCounts[next]){
                max = nodeCounts[next];
            }
        }

        max = Math.max(max, n-nodeCounts[cur]);

        if(max < gravityValue){
            gravityValue = max;
            gravityIndex = cur;
        }

    }

    public static void test(){



    }




}
