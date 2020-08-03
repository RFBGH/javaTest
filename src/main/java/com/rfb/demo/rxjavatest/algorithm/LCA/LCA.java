package com.rfb.demo.rxjavatest.algorithm.LCA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/3 0003.
 */
public class LCA {

    private static List<Integer>[] G;
    private static int n;
    private static int[] deeps;
    private static int[] parents;

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

    public static int LCA1(int x, int y){

        for(int i = 0; i < n; i++){
            deeps[i] = 0;
            parents[i] = -1;
        }

        dfs(0, -1, 0);

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

    public static void init(int count){
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

        u--;
        v--;
        G[u].add(v);
    }

    public static void test(){

        init(12);
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(2, 5);
        addEdge(2, 6);
        addEdge(3, 7);
        addEdge(3, 8);
        addEdge(8, 9);
        addEdge(8, 10);
        addEdge(9, 11);
        addEdge(10, 12);

        System.out.println(LCA1(4, 8));
        System.out.println(LCA1(6, 11));
        System.out.println(LCA1(4, 6));
    }
}
