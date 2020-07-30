package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj1273;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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

    private static class Node{
        int from;
        int startEdgeIndex;
        int flow;

        private Node(int from, int startEdgeIndex, int flow) {
            this.from = from;
            this.startEdgeIndex = startEdgeIndex;
            this.flow = flow;
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

        Stack<Node> stack = new Stack<Node>();
        Node node = new Node(from, 0, flow);
        stack.push(node);
        used[from] = true;

        while (!stack.isEmpty()){

            boolean hasNew = false;

            Node cur = stack.peek();
            List<Edge> edges = G[cur.from];
            for(int i = cur.startEdgeIndex; i < edges.size(); i++){
                Edge edge = edges.get(i);
                int to = edge.to;
                int cap = edge.cap;

                if(cap == 0){
                    continue;
                }

                if(used[to]){
                    continue;
                }

                used[to] = true;
                cur.startEdgeIndex = i+1;
                Node newNode = new Node(to, 0, Math.min(cur.flow, cap));
                stack.push(newNode);
                hasNew = true;
                break;
            }

            if(hasNew){
                if(stack.peek().from == N-1){
                    break;
                }
                continue;
            }

            stack.pop();
        }

        if(stack.isEmpty()){
            return 0;
        }

        int f = stack.peek().flow;

        stack.pop();
        while (!stack.isEmpty()){

            Node cur = stack.pop();
            Edge edges = G[cur.from].get(cur.startEdgeIndex-1);
            edges.cap -= f;
            G[edges.to].get(edges.rev).cap += f;
        }

        return f;
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
