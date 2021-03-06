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

    private static boolean bfs(int n, int[] level,  int[][] G){

        for(int i = 0; i < n; i++){
            level[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        level[0] = 0;
        while (!queue.isEmpty()){

            int cur = queue.poll();
            for(int i = 0; i < n; i++){
                int to = i;

                if(level[to] != -1){
                    continue;
                }

                if(G[cur][i] == 0){
                    continue;
                }

                level[to] = level[cur]+1;
                queue.offer(to);

                if(to == n-1){
                    return true;
                }
            }


        }
        return false;
    }

    private static int dfs(int from, int flow, int n, int[] level,  int[][] G){

        if(from == n-1){
            return flow;
        }

        int cost = 0;
        for(int i = 0; i < n; i++){

            if(level[i] != level[from]+1){
                continue;
            }

            int cap = G[from][i];
            if(G[from][i] == 0){
                continue;
            }

            int f = dfs(i, Math.min(cap, flow-cost), n, level, G);
            if(f != 0){
                G[from][i] -= f;
                G[i][from] += f;

                cost += f;
                if(cost == flow){
                    break;
                }else{
                    level[i] = -1;
                }
            }
        }

        return cost;
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
        int n = F*2 + 2;
        int[][] G = new int[n][n];

        int[] level = new int[n];

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
                G[F+1+i][n-1] = fields[i].shed;
                G[1+i][F+1+i] = Integer.MAX_VALUE;
            }

            for(int i = 0; i < F; i++){
                for(int j = i+1; j < F; j++){
                    if(dist[i][j] > cut){
                        continue;
                    }

                    G[1+i][F+j+1] = Integer.MAX_VALUE;
                    G[1+j][F+i+1] = Integer.MAX_VALUE;
                }
            }

            int allFlow = 0;

            while (bfs(n, level, G)){
                allFlow += dfs(0, Integer.MAX_VALUE, n, level, G);
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
