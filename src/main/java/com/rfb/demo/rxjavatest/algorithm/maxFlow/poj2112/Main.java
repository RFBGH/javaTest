package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2112;
import java.util.*;

public class Main {

    private static boolean connect[][] = new boolean[450][200];
    private static int[] match = new int[200];
    private static boolean[] used = new boolean[200];
    private static int[][] G = new int[30][200];
    private static int[] distance = new int[250];
    private static boolean[] mark = new boolean[250];

    public static boolean dfs(int k, int C){

        for(int c = 0; c < C; c++){

            if(!connect[k][c]){
                continue;
            }

            if(used[c]){
                continue;
            }

            used[c] = true;

            if(match[c] == -1 || dfs(match[c], C)){
                match[c] = k;
                return true;
            }
        }

        return false;
    }

    public static void calc(int K, int M, int C, int[][] dist){

        int n = K+C;

        for(int i = 0; i < K; i++){
            for(int j = 0; j < C; j++){
                G[i][j] = Integer.MAX_VALUE;
            }
        }

        Set<Integer> allLength = new HashSet<Integer>();

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

                    if(distance[i] < min){
                        min = distance[i];
                        index = i;
                    }
                }

                if(index == -1){
                    break;
                }

                mark[index] = true;

                for(int i = 0; i < n; i++){

                    if(mark[i]){
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

            for(int c = 0; c < C; c++){
                int dis = distance[K+c];
                G[k][c] = dis;
                allLength.add(dis);
            }
        }

        List<Integer> length = new ArrayList<Integer>(allLength.size());
        for(Integer l:allLength){
            length.add(l);
        }

        Collections.sort(length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2){
                    return -1;
                }

                if(o1.equals(o2)){
                    return 0;
                }

                return 1;
            }
        });

        int ans = Integer.MAX_VALUE;

        int left = 0;
        int right = length.size()-1;

        while (left < right){

            int mid = (left + right) / 2;

            int cut = length.get(mid);

            for(int k = 0, size = K*M; k < size ;k++){
                for(int c = 0; c < C; c++){
                    connect[k][c] = false;
                }
            }

            for(int c = 0; c < C; c++){
                match[c] = -1;
            }

            for(int k = 0; k < K; k++){
                for(int c = 0; c < C; c++){

                    if(G[k][c] > cut){
                        continue;
                    }

                    for(int m = 0; m < M; m++){
                        connect[k*M+m][c] = true;
                    }
                }
            }

            int matchCount = 0;
            for(int k = 0, size = K*M; k < size; k++){

                for(int c = 0; c < C; c++){
                    used[c] = false;
                }

                if(dfs(k, C)){
                    matchCount++;
                }

                if(matchCount == C){
                    break;
                }
            }

            if(matchCount == C){
                right = mid;
                if(ans > cut){
                    ans = cut;
                }
            }else{
                left = mid+1;
            }

        }

        System.out.println(ans);
    }

    public static void main(String args[]) throws Exception{

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