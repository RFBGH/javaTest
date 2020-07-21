package com.rfb.demo.rxjavatest.algorithm;

/**
 * Created by Administrator on 2020/7/21 0021.
 */
public class GCutPoint {

    private boolean [][] G = null;
    private int n;
    private int[] lows;
    private int[] nums;
    private int[] parents;
    private int numCount = 0;
    private int root;

    public void init(int n){
        this.n = n;
        G = new boolean[n][n];

        lows = new int[n];
        nums = new int[n];
        parents = new int[n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                G[i][j] = false;
            }
            lows[i] = Integer.MAX_VALUE;
            parents[i] = -1;
            nums[i] = -1;
        }
    }

    public void addEdge(int u, int v){
        G[u][v] = true;
        G[v][u] = true;
    }

    private void dfs(int u){

        nums[u] = numCount++;
        lows[u] = nums[u];
        int childCount = 0;
        for(int v = 0; v < n; v++){
            if(!G[u][v]){
                continue;
            }

            parents[v] = u;

            if(nums[v] == -1){

                childCount++;
                dfs(v);
                lows[u] = Math.min(lows[u], lows[v]);
                if(u != root && lows[v] >= nums[u]){
                    System.out.println("cut point "+u);
                }

                if(u == root && childCount > 1){
                    System.out.println("cut point "+u);
                }

            }else if(parents[u] != v){
                lows[u] = Math.min(lows[u], nums[v]);
            }
        }
    }

    public void calc(){
        for(int i = 0; i < n; i++){
            if(nums[i] != -1){
                continue;
            }
            root = i;
            dfs(i);
        }
    }

    public void print(){
        for(int i = 0; i < n; i++){
            System.out.print(lows[i]+" ");
        }
        System.out.println();
    }

    public static void test(){

        GCutPoint cutPoint = new GCutPoint();
//        cutPoint.init(7);
//        cutPoint.addEdge(0, 1);
//        cutPoint.addEdge(1, 2);
//        cutPoint.addEdge(2, 3);
//        cutPoint.addEdge(2, 4);
//        cutPoint.addEdge(0, 4);
//        cutPoint.addEdge(4, 5);
//        cutPoint.addEdge(4, 6);
//        cutPoint.addEdge(5, 6);

        cutPoint.init(6);
        cutPoint.addEdge(0, 3);
        cutPoint.addEdge(0, 2);
        cutPoint.addEdge(3, 1);
        cutPoint.addEdge(2, 1);
        cutPoint.addEdge(1, 4);
        cutPoint.addEdge(1, 5);
        cutPoint.addEdge(4, 5);

        cutPoint.calc();
        cutPoint.print();
    }

}
