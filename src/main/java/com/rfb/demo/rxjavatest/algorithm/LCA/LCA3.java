package com.rfb.demo.rxjavatest.algorithm.LCA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/3 0003.
 */
public class LCA3 {

    private static List<Integer>[] G;
    private static int n;
    private static int[] deeps;
    private static int[] parents;
    private static int[][] rmq;
    private static List<Integer> visits;
    private static int[] firsts;


    private static void dfs(int cur, int parent, int deep){

        parents[cur] = parent;
        deeps[cur] = deep;

        firsts[cur] = visits.size();
        visits.add(cur);

        List<Integer> sons = G[cur];
        for(int son:sons){
            if(son == parent){
                continue;
            }

            dfs(son, cur, deep+1);

            visits.add(cur);
        }
    }

    public static void initRMQ(){

        rmq = new int[30][visits.size()];

        int size = visits.size();
        for(int i = 0; i < size; i++){
            rmq[0][i] = visits.get(i);
        }

        for(int i = 1; (1 << i) <= size; i++){
            for(int j = 0; (j + (1 << i)) <= size; j++){
                rmq[i][j] = min(rmq[i-1][j], rmq[i-1][j+(1<<(i-1))]);
            }
        }
    }

    private static int min(int x, int y){
        if(deeps[x] < deeps[y]){
            return x;
        }
        return y;
    }

    public static void init(int count){
        count++;
        n = count;
        visits = new ArrayList<>();
        deeps = new int[n];
        parents = new int[n];
        firsts = new int[n];
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

    public static int LCA(int x, int y){

        int firstX = firsts[x];
        int firstY = firsts[y];

        if(firstX > firstY){
            int temp = firstX;
            firstX = firstY;
            firstY = temp;
        }

        int length = firstY - firstX + 1;

        int k = 0;
        while ((1<<(k+1)) < length){
            k++;
        }

        return min(rmq[k][firstX], rmq[k][firstY-(1<<k)+1]);
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
        initRMQ();

        System.out.println(LCA(4, 8));
        System.out.println(LCA(6, 11));
        System.out.println(LCA(4, 6));
        System.out.println(LCA(3, 12));
    }
}
