package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj1273;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2020/7/30 0030.
 */
public class Main2 {

    private static class Edge{
        int to;
        int cap;
        int rev;

        private Edge(int to, int cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    private static List<Edge>[] G = null;
    private static boolean[] used;
    private static int N;

    public static void addEdge(int from, int to, int cap){

        Edge edge = new Edge(to, cap, G[to].size());
        G[from].add(edge);

        edge = new Edge(from, 0, G[from].size()-1);
        G[to].add(edge);
    }

    public static int dfs(int from, int flow){

        if(from == N-1){
            return flow;
        }

        used[from] = true;

        List<Edge> edges = G[from];
        for(Edge edge : edges){
            int to = edge.to;
            int cap = edge.cap;

            if(used[to]){
                continue;
            }

            if(cap <= 0){
                continue;
            }

            int f = dfs(to, Math.min(flow, cap));
            if(f != 0){
                edge.cap -= f;
                G[to].get(edge.rev).cap += f;
                return f;
            }
        }

        return 0;
    }
    
    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int M = scanner.nextInt();
            N = scanner.nextInt();

            G = new ArrayList[N];
            used = new boolean[N];
            for(int i = 0; i < N; i++){
                G[i] = new ArrayList<Edge>();
                used[i] = false;
            }

            for(int i = 0; i < M; i++){
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int cap = scanner.nextInt();
                from--;
                to--;
                addEdge(from, to, cap);
            }

            long allFlow = 0;
            while (true){
                for(int i = 0; i < N; i++){
                    used[i] = false;
                }
                int f = dfs(0, Integer.MAX_VALUE);
                if(f == 0){
                    break;
                }
                allFlow += f;
            }

            System.out.println(allFlow);
        }

    }

}
