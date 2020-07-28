package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2112;

import java.util.Scanner;

public class Main {

    public static void calc(int K, int M, int C, int[][] dist){

        int n = K+C;
        int[] distance = new int[n];
        boolean[] mark = new boolean[n];

        int[][] G = new int[K][C];
        for(int i = 0; i < K; i++){
            for(int j = 0; j < C; j++){
                G[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int k = 0; k < K; k++){

            for(int i = 0; i < n; i++){
                distance[i] = Integer.MAX_VALUE;
                mark[i] = false;
            }

            distance[k] = 0;

            for(int I = 0; I < n; I++){

                int min = Integer.MAX_VALUE;
                int index = -1;
                for(int i = 0; i < n; i++){
                    if(mark[i]){
                        continue;
                    }

                    if(distance[i] > min){
                        min = distance[i];
                        index = i;
                    }
                }

                if(index == -1){
                    break;
                }

                mark[index] = true;

                for(int i = 0; i < n; i++){

                    if(mark[index]){
                        continue;
                    }

                    if(dist[index][i] == 0){
                        continue;
                    }

                    int dis = distance[index] + dist[index][i];
                    if(dis < distance[i]){
                        distance[i] = dis;
                    }
                }

            }

            for(int i = 0; i < C; i++){
                G[k][i] = distance[K+i];
            }
        }

        int[] used = new int[K];
        boolean[] pick = new boolean[C];

        for(int i = 0; i < K; i++){
            used[i] = 0;
        }

        for(int i = 0; i < C; i++){
            pick[i] = false;
        }

        int ans = 0;

        for(int c = 0; c < C; c++){

            int min = Integer.MAX_VALUE;
            int useK = -1;
            int useC = -1;
            for(int k = 0; k < K; k++){

                if(used[k] == M){
                    continue;
                }

                for(int i = 0; i < C; i++){

                    if(pick[i]){
                        continue;
                    }

                    if(G[k][i] < min){
                        min = G[k][i];
                        useK = k;
                        useC = i;
                    }

                }
            }

            if(useC == -1){
                break;
            }

            used[useK]++;
            pick[useC] = true;

            if(min > ans){
                ans = min;
            }
        }

        System.out.println(ans);

    }

    public static void test(String args[]) throws Exception{

        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        int C = scanner.nextInt();
        int M = scanner.nextInt();

        int n = K+C;
        int[][]dist = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dist[i][j] = scanner.nextInt();
            }
        }

        calc(K, M, C, dist);

    }

}
