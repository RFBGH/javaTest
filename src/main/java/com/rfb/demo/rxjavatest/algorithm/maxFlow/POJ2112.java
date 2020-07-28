package com.rfb.demo.rxjavatest.algorithm.maxFlow;

import java.util.Scanner;

/**
 * Created by Administrator on 2020/7/28 0028.
 */
public class POJ2112 {

    private static class Edge{
        int cap;
        int cost;

        private Edge(int cap, int cost) {
            this.cap = cap;
            this.cost = cost;
        }
    }

    public static void calc(int K, int M, int C, int dist[][]){


        int pointCount = K + C + 2;

        Edge[][] G = new Edge[pointCount][pointCount];
        for(int i = 0; i < K; i++){
            Edge edge = new Edge(M, 0);
            G[C+1+i][pointCount-1] = edge;
        }

        for(int i = 0; i < C; i++){
            Edge edge = new Edge(1, 0);
            G[0][i+1] = edge;
        }

        int n = K+C;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){

                if(dist[i][j] == 0){
                    continue;
                }

                int from;

                if(i < K){
                    from = C+1+i;
                }else{
                    from = i-K+1;
                }

                int to;
                if(j < K){
                    to = C+1+j;
                }else{
                    to = j-K+1;
                }

                Edge edge = new Edge(Integer.MAX_VALUE, dist[i][j]);
                G[from][to] = edge;
                edge = new Edge(Integer.MAX_VALUE, dist[i][j]);
                G[to][from] = edge;
            }
        }

        int ans = 0;
        n = K+C+2;
        int[] distance = new int[n];
        int[] pre = new int[n];
        while (true){

            for(int i = 0; i < n; i++){
                distance[i] = Integer.MAX_VALUE;
                pre[i] = -1;
            }

            distance[0] = 0;
            boolean update = true;
            while (update){
                update = false;
                for(int i = 0; i < n; i++){

                    if(distance[i] == Integer.MAX_VALUE){
                        continue;
                    }

                    for(int j = 0; j < n; j++){

                        if(G[i][j] == null){
                            continue;
                        }

                        if(G[i][j].cap <= 0){
                            continue;
                        }

                        if(distance[j] <= distance[i]+G[i][j].cost){
                            continue;
                        }

                        distance[j] = distance[i]+G[i][j].cost;
                        pre[j] = i;
                        update = true;
                    }
                }

            }

            if(distance[n-1] == Integer.MAX_VALUE){
                break;
            }

            int cur = pre[n-1];
            while (true){
                int last = pre[cur];
                if(last == -1){
                    break;
                }

                if(G[last][cur].cap != Integer.MAX_VALUE){
                    G[last][cur].cap --;
                }

                cur = last;
            }

            if(ans < distance[n-1]){
                ans = distance[n-1];
            }
        }

        System.out.println(ans);
    }

//    public static void main(String args[]) throws Exception{
//
//        Scanner scanner = new Scanner(System.in);
//        int K = scanner.nextInt();
//        int C = scanner.nextInt();
//        int M = scanner.nextInt();
//
//        int n = K+C;
//        int[][]dist = new int[n][n];
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                dist[i][j] = scanner.nextInt();
//            }
//        }
//
//        calc(K, M, C, dist);
//
//    }


}
