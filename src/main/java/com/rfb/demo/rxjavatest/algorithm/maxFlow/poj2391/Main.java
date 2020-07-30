package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2391;

import java.util.*;

/**
 * Created by Administrator on 2020/7/29 0029.
 */
public class Main {

    private static class Field{
        int cow;
        int shed;
    }

    private static int dfs(int from, int flow, int n, boolean[] used, int[][] G, int step){

        if(step == 0){
            if(from == n-1){
                return flow;
            }
            return 0;
        }


        used[from] = true;
        for(int i = 0; i < n; i++){
            if(used[i]){
                continue;
            }

            if(G[from][i] == 0){
                continue;
            }

            int f = dfs(i, Math.min(G[from][i], flow), n, used, G, step-1);
            if(f != 0){
                if(G[from][i] != Integer.MAX_VALUE){
                    G[from][i] -= f;
                    G[i][from] += f;
                }
                return f;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        int F = scanner.nextInt();
        int P = scanner.nextInt();

        int allCow = 0;

        Field[] fields = new Field[F];
        for(int i = 0; i < F; i++){
            fields[i] = new Field();
        }

        for(int i = 0; i < F; i++){
            int c = scanner.nextInt();
            int shed = scanner.nextInt();
            fields[i].cow = c;
            fields[i].shed = shed;

            allCow += c;
        }

        long[][] dist = new long[F][F];
        for(int i = 0; i < F; i++){
            for(int j = 0; j < F; j++){
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        for(int i = 0; i < P; i++){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            from--;
            to--;
            long last = dist[from][to];
            if(last > cost){
                dist[from][to] = cost;
                dist[to][from] = cost;
            }
        }

        for(int k = 0; k < F; k++){
            for(int i = 0; i < F; i++){
                for(int j = 0; j < F; j++){

                    if(i == j){
                        continue;
                    }

                    if(dist[i][k] == Long.MAX_VALUE){
                        continue;
                    }

                    if(dist[k][j] == Long.MAX_VALUE){
                        continue;
                    }

                    if(dist[i][j] > dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                    }
                }
            }
        }

        Set<Long> lengthSet = new HashSet<Long>();
        for(int i = 0; i < F; i++){
            for(int j = 0; j < F; j++){
                if(dist[i][j] == Long.MAX_VALUE){
                    continue;
                }
                lengthSet.add(dist[i][j]);
            }
        }

        List<Long> length = new ArrayList<Long>(lengthSet.size());
        for(Long l:lengthSet){
            length.add(l);
        }

        Collections.sort(length, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if(o1 < o2){
                    return -1;
                }

                if(o1 > o2){
                    return 1;
                }

                return 0;
            }
        });

        long ans = Long.MAX_VALUE;
        int n = F + 2;
        int [][] G = new int[n][n];
        boolean[] used = new boolean[n];

        int left = 0;
        int right = length.size()-1;
        while (left <= right){

            int mid = (left + right) / 2;
            long cut = length.get(mid);

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    G[i][j] = 0;
                }
            }

            for(int i = 0; i < F; i++){
                G[0][i+1] = fields[i].cow;
                G[1+i][n-1] = fields[i].shed;
            }

            for(int i = 0; i < F; i++){
                for(int j = 0; j < F; j++){
                    if(dist[i][j] > cut){
                        continue;
                    }

                    G[i+1][j+1] = Integer.MAX_VALUE;
                    G[j+1][i+1] = Integer.MAX_VALUE;
                }
            }

            int allFlow = 0;
            while (true){

                for(int i = 0; i < n; i++){
                    used[i] = false;
                }

                int f = dfs(0, Integer.MAX_VALUE, n, used, G, 3);
                if(f == 0){
                    break;
                }
                allFlow += f;
            }

            if(allCow == allFlow){
                right = mid-1;
                if(ans > cut){
                    ans = cut;
                }
            }else{
                left = mid+1;
            }
        }

        if(ans == Long.MAX_VALUE){
            ans = -1;
        }
        System.out.println(ans);
    }

}
