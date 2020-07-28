package com.rfb.demo.rxjavatest.algorithm.maxFlow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2020/7/28 0028.
 */
public class POJ1273 {

    private int n;
    private List<Edge>[] G;
    private int[] levels;

    private static class Edge{
        public int to;
        public int cap;
        public int rev;

        private Edge(int to, int cap, int rev) {
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
    }

    public void init(int n){
        this.n = n;
        levels = new int[n];
        G = new ArrayList[n];
        for(int i = 0; i < n; i++){
            List<Edge> edges = new ArrayList<>();
            G[i] = edges;
        }
    }

    private void resetLevel(){
        for(int i = 0; i < n; i++){
            levels[i] = -1;
        }
    }

    private void initLevel(int s, int t){

        resetLevel();
        levels[s] = 0;
        Queue<Integer> queues = new LinkedList<>();
        queues.offer(s);

        while(!queues.isEmpty()){

            int cur = queues.poll();
            List<Edge> edges = G[cur];
            int nextLevel = levels[cur]+1;
            for(Edge edge:edges){
                int to = edge.to;
                if(levels[to] != -1){
                    continue;
                }

                int cap = edge.cap;
                if(cap <= 0){
                    continue;
                }

                levels[to] = nextLevel;
                queues.offer(to);

                if(t == to){
                    return;
                }
            }
        }
    }

    private int dfs(int from, int flow, int t){

        if(from == t){
            return flow;
        }

        List<Edge> edges = G[from];
        for(Edge edge:edges){

            int to = edge.to;
            if(levels[to] <= levels[from]){
                continue;
            }

            int cap = edge.cap;
            if(cap <= 0){
                continue;
            }

            int f = dfs(to, Math.min(flow, cap), t);
            if(f == 0){
                continue;
            }

            edge.cap -= f;
            G[edge.to].get(edge.rev).cap += f;
            return f;
        }

        return 0;
    }

    private int calc(){

        int ans = 0;
        while (true){

            initLevel(0, n-1);
            if(levels[n-1] == -1){
                break;
            }

            while (true){
                int f = dfs(0, Integer.MAX_VALUE, n-1);
                if(f == 0){
                    break;
                }
                ans += f;
            }
        }
        return ans;
    }

    public void addEdge(int from, int to, int cap){
        G[from].add(new Edge(to, cap, G[to].size()));
        G[to].add(new Edge(from, 0, G[from].size()-1));
    }

    public void addEdgeFromOne(int from, int to, int cap){
        from--;
        to--;
        addEdge(from, to, cap);
    }

    public static void test(){

        POJ1273 pool = new POJ1273();
        pool.init(4);
        pool.addEdgeFromOne(1, 2, 40);
        pool.addEdgeFromOne(1, 4, 20);
        pool.addEdgeFromOne(2, 4, 20);
        pool.addEdgeFromOne(2, 3, 30);
        pool.addEdgeFromOne(3, 4, 10);
        System.out.println(pool.calc());
    }

}
