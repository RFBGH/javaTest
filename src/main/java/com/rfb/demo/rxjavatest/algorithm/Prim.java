package com.rfb.demo.rxjavatest.algorithm;

/**
 * Created by Administrator on 2020/7/24 0024.
 */
public class Prim {

    private int[][] G = null;
    private int n;

    public void init(int n){
        this.n = n;
        G = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                G[i][j] = 0;
            }
        }
    }

    public void addEdge(int f, int t, int cost){
        G[f][t] = cost;
        G[t][f] = cost;
    }

    public void calc(){

        boolean[] mark = new boolean[n];
        int[] dist = new int[n];
        int[] pre = new int[n];
        for(int i = 0; i < n; i++){
            mark[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pre[i] = -1;
        }

        dist[0] = 0;
        for(int I = 0; I < n; I++){

            int min = Integer.MAX_VALUE;
            int k = 0;

            for(int i = 0; i < n; i++){
                if(mark[i]){
                    continue;
                }

                if(min < dist[i]){
                    continue;
                }

                min = dist[i];
                k = i;
            }

            if(min == Integer.MAX_VALUE){
                break;
            }

            mark[k] = true;
            {
                System.out.println("mark "+k);
            }

            for(int i = 0; i < n; i++){
                if(mark[i]){
                    continue;
                }

                if(G[k][i] <= 0){
                    continue;
                }

                if(dist[k]+G[k][i] < dist[i]){
                    dist[i] = dist[k]+G[k][i];
                    pre[i] = k;
                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();

        for(int i = 0; i < n; i++){
            System.out.println(i+" "+pre[i]);
        }

    }

    public static void test(){

        Prim prim = new Prim();
        prim.init(6);
        prim.addEdge(0, 1, 6);
        prim.addEdge(0, 2, 1);
        prim.addEdge(0, 3, 5);

        prim.addEdge(1, 2, 5);
        prim.addEdge(1, 4, 3);

        prim.addEdge(2, 3, 5);
        prim.addEdge(2, 4, 6);
        prim.addEdge(2, 5, 4);

        prim.addEdge(4, 5, 6);
        prim.addEdge(2, 5, 2);
        prim.calc();

    }

}
