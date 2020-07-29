package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2112;
import java.util.*;

public class Main2 {

    public static final int MAX = 200*250;

    private static boolean[] used = new boolean[250];
    private static int[][] G = new int[250][250];

    public static int dfs(int s, int n, int f){

        if(s == n-1){
            return f;
        }

        used[s] = true;
        for(int i = 0; i < n; i++){

            if(used[i]){
                continue;
            }

            if(G[s][i] == 0){
                continue;
            }

            int newF = dfs(i, n, Math.min(f, G[s][i]));
            if(newF > 0){
                G[s][i] -= newF;
                G[i][s] += newF;
                return newF;
            }

        }
        return 0;
    }

    public static void calc(int K, int M, int C, int[][] dist){

        int n = K + C;

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){

                    if(i == j){
                        continue;
                    }

                    if(dist[i][j] > dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                    }
                }
            }
        }

        Set<Integer> allLength = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(dist[i][j] == MAX){
                    continue;
                }

                allLength.add(dist[i][j]);
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

        int ans = MAX;

        n += 2;
        int left = 0;
        int right = MAX;
        while (left != right){
            int mid = (left + right) / 2;
            int cut = mid;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    G[i][j] = 0;
                }
            }

            for(int k = 0; k < K; k++){
                G[k+1][n-1] = M;
            }

            for(int c = 0; c < C; c++){
                G[0][1+K+c] = 1;
            }

            for(int c = 0; c < C; c++) {

                for(int k = 0; k < K; k++){

                    if(dist[c+K][k] > cut){
                        continue;
                    }

                    G[K+1+c][k+1] = 1;
                }
            }

            int allFlow = 0;
            while (true){

                for(int i = 0; i < n; i++){
                    used[i] = false;
                }
                int f = dfs(0, n, Integer.MAX_VALUE);
                if(f == 0){
                    break;
                }
                allFlow += f;
            }

            if(allFlow == C){
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
                if(dist[i][j] == 0){
                    dist[i][j] = MAX;
                }
            }
        }

        calc(K, M, C, dist);
    }

}