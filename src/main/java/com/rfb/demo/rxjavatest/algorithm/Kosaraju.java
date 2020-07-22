package com.rfb.demo.rxjavatest.algorithm;

import java.util.Stack;

/**
 * Created by Administrator on 2020/7/22 0022.
 */
public class Kosaraju {

    private int n;
    private boolean[][] G;
    private boolean[][] rG;
    private boolean[] used;
    private int[] mark;
    private Stack<Integer> postOrder = new Stack<>();

    public void init(int n){

        this.n = n;
        G = new boolean[n][n];
        rG = new boolean[n][n];
        used = new boolean[n];
        mark = new int[n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                G[i][j] = false;
                rG[i][j] = false;
            }
            used[i] = false;
            mark[i] = -1;
        }
    }

    public void addEdge(int f, int t){
        G[f][t] = true;
        rG[t][f] = true;
    }

    public void addEdgePointFromOne(int f, int t){
        f--;
        t--;
        G[f][t] = true;
        rG[t][f] = true;
    }

    public void rDfs(int f){

        used[f] = true;
        for(int i = 0; i < n; i++){
            if(!rG[f][i]){
                continue;
            }

            if(used[i]){
                continue;
            }

            rDfs(i);
        }

        postOrder.push(f);
    }

    public void dfs(int f, int type){

        mark[f] = type;
        for(int i = 0; i < n; i++){
            if(!G[f][i]){
                continue;
            }

            if(mark[i] != -1){
                continue;
            }

            dfs(i, type);
        }
    }

    public void calc(){
        for(int i = 0; i < n; i++){
            if(used[i]){
               continue;
            }
            rDfs(i);
        }

        int type = 0;
        for(int i = 0; i < n; i++){
            int f = postOrder.pop();
            if(mark[f] != -1){
                continue;
            }
            dfs(f, type++);
        }
    }

    public void print(){
        for(int i = 0; i < n; i++){
            System.out.print(mark[i]+" ");
        }
        System.out.println();
    }

    public static void test(){

        Kosaraju kosaraju = new Kosaraju();
//        kosaraju.init(6);
//        kosaraju.addEdge(0, 2);
//        kosaraju.addEdge(1, 3);
//        kosaraju.addEdge(2, 3);
//        kosaraju.addEdge(3, 2);
//        kosaraju.addEdge(2, 4);
//        kosaraju.addEdge(3, 5);
//        kosaraju.calc();
//        kosaraju.print();

        kosaraju.init(12);
        kosaraju.addEdgePointFromOne(12, 11);
        kosaraju.addEdgePointFromOne(11, 8);
        kosaraju.addEdgePointFromOne(11, 10);
        kosaraju.addEdgePointFromOne(8, 10);
        kosaraju.addEdgePointFromOne(10, 9);
        kosaraju.addEdgePointFromOne(9, 8);
        kosaraju.addEdgePointFromOne(9, 7);
        kosaraju.addEdgePointFromOne(7, 6);
        kosaraju.addEdgePointFromOne(6, 5);
        kosaraju.addEdgePointFromOne(5, 7);
        kosaraju.addEdgePointFromOne(6, 4);
        kosaraju.addEdgePointFromOne(4, 3);
        kosaraju.addEdgePointFromOne(6, 3);
        kosaraju.addEdgePointFromOne(6, 4);
        kosaraju.addEdgePointFromOne(4, 3);
        kosaraju.addEdgePointFromOne(2, 3);
        kosaraju.addEdgePointFromOne(3, 2);
        kosaraju.addEdgePointFromOne(4, 1);

        kosaraju.calc();
        kosaraju.print();
    }

}
