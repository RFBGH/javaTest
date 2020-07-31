package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj3498;

import java.util.*;

/**
 * Created by Administrator on 2020/7/31 0031.
 */
public class Main3 {

    private static class IceLand{
        int x;
        int y;
        int penguinCount;
        int canJumpCount;

        private IceLand(int x, int y, int penguinCount, int canJumpCount) {
            this.x = x;
            this.y = y;
            this.penguinCount = penguinCount;
            this.canJumpCount = canJumpCount;
        }
    }

    private static boolean bfs(int[][] G, int n, int[] level, int s, int t){

        for(int i = 0; i < n; i++){
            level[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        level[s] = 0;

        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int i = 0; i < n; i++){
                if(level[i] != -1){
                    continue;
                }

                if(G[cur][i] <= 0){
                    continue;
                }

                level[i] = level[cur]+1;
                queue.offer(i);

                if(i == t){
                    return true;
                }
            }
        }

        return false;
    }

    private static int dfs(int[][] G, int n, int[] level, int from, int flow, int end){

        if(from == end){
            return flow;
        }

        for(int i = 0; i < n; i++){

            if(level[i] <= level[from]){
                continue;
            }

            int cap = G[from][i];
            if(cap <= 0){
                continue;
            }

            int f = dfs(G, n, level, i, Math.min(cap, flow), end);
            if(f != 0){

                G[from][i] -= f;
                G[i][from] += f;
                return f;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);

        int testCount = scanner.nextInt();

        while (testCount > 0){

            testCount--;
            int N = scanner.nextInt();
            float D = scanner.nextFloat();
            int allPenguin = 0;

            IceLand[] iceLands = new IceLand[N];

            for(int i = 0; i < N; i++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int penguinCount = scanner.nextInt();
                int canJumpCount = scanner.nextInt();

                allPenguin += penguinCount;

                iceLands[i] = new IceLand(x, y, penguinCount, canJumpCount);
            }

            int n = N*2+1;
            int[][] G = new int[n][n];
            int[] level = new int[n];


            List<Integer> result = new ArrayList<Integer>();
            for(int I = 0; I < N; I++){

                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        G[i][j] = 0;
                    }
                }

                for(int i = 0; i < N; i++){
                    G[0][i+1] = iceLands[i].penguinCount;
                    G[i+1][i+1+N] = iceLands[i].canJumpCount;
                }

                for(int i = 0; i < N; i++){
                    for(int j = i+1; j < N; j++){
                        float distance = (iceLands[i].x - iceLands[j].x)*(iceLands[i].x - iceLands[j].x)
                                + (iceLands[i].y - iceLands[j].y)*(iceLands[i].y - iceLands[j].y);
                        if(distance > D*D){
                            continue;
                        }

                        G[N+i+1][1+j] = Integer.MAX_VALUE;
                        G[N+j+1][1+i] = Integer.MAX_VALUE;
                    }
                }

                int allFlow = 0;

                while (bfs(G, n, level, 0, 1+I)){
                    while (true){
                        int flow = dfs(G, n, level, 0, Integer.MAX_VALUE, 1+I);
                        if(flow == 0){
                            break;
                        }
                        allFlow += flow;
                    }
                }

                if(allFlow == allPenguin){
                    result.add(I);
                }
            }

            if(result.isEmpty()){
                System.out.println("-1");
            }else{
                for(int i = 0, size = result.size(); i < size; i++){
                    System.out.print(result.get(i));
                    if(i != size-1){
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
