package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj3498;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2020/7/31 0031.
 */
public class Main2 {

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

    private static int dfs(int[][] G, int n, boolean[] used, int from, int flow, int end){

        if(from == end){
            return flow;
        }

        used[from] = true;
        for(int i = 0; i < n; i++){

            if(used[i]){
                continue;
            }

            int cap = G[from][i];
            if(cap <= 0){
                continue;
            }

            int f = dfs(G, n, used, i, Math.min(cap, flow), end);
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
            boolean[] used = new boolean[n];


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
                while (true){

                    for(int k = 0; k < n; k++){
                        used[k] = false;
                    }

                    int flow = dfs(G, n, used, 0, Integer.MAX_VALUE, 1+I);
                    if(flow == 0){
                        break;
                    }
                    allFlow += flow;
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
