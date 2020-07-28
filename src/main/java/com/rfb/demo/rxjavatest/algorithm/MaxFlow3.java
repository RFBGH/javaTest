package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/20 0020.
 */
public class MaxFlow3 {

    private static class Edge {
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
        int pre;
        int flow;
        int i;
        int toIndex;

        private Node(int pre, int flow, int i, int toIndex) {
            this.pre = pre;
            this.flow = flow;
            this.i = i;
            this.toIndex = toIndex;
        }
    }

    private List<Edge>[] G;
    private boolean[] used;
    private int n;

    public void init(int n){

        this.n = n;

        G = new ArrayList[n];
        used = new boolean[n];

        for(int i = 0; i < n; i++){
            List<Edge> nodes = new ArrayList<>();
            G[i] = nodes;
            used[i] = false;
        }
    }

    private void resetUsed(){
        for(int i = 0; i < n; i++){
            used[i] = false;
        }
    }

    public void add(int from, int to, int cap){

        Edge node = new Edge(to, cap, G[to].size());
        G[from].add(node);

        node = new Edge(from, 0, G[from].size()-1);
        G[to].add(node);
    }

    public void addFromOne(int from, int to, int cap){
        from--;
        to--;
        add(from, to, cap);
    }

    public void calc(int s, int e){

        int ans = 0;

        while (true){

            resetUsed();
            used[s] = true;

            List<Node> queue = new LinkedList<>();
            Node node = new Node(-1, Integer.MAX_VALUE, s, -1);
            queue.add(node);
            int curIndex = 0;
            boolean find = false;

            while (curIndex < queue.size()){

                Node cur = queue.get(curIndex);

                int from = cur.i;
                List<Edge> edges = G[from];

                for(int i = 0, size = edges.size(); i < size; i++){

                    Edge edge = edges.get(i);
                    int to = edge.to;
                    if(used[to]){
                        continue;
                    }

                    if(edge.cap <= 0){
                        continue;
                    }

                    used[to] = true;
                    Node newNode = new Node(curIndex, Math.min(edge.cap, cur.flow), to, i);
                    queue.add(newNode);

                    if(to == e){
                        find = true;
                        break;
                    }
                }

                if(find){
                    break;
                }

                curIndex ++;
            }

            if(!find){
                break;
            }


            Node target = queue.get(queue.size()-1);
            int f = target.flow;
            ans += f;

            System.out.println("f "+f);

            int pre = queue.size()-1;
            while (true){

                Node cur = queue.get(pre);
                int ppre = cur.pre;

                System.out.print(cur.i+" ");

                if(ppre == -1){
                    break;
                }

                Node last = queue.get(ppre);

                int from = last.i;
                int toIndex = cur.toIndex;

                Edge edge = G[from].get(toIndex);
                edge.cap -= f;

                G[edge.to].get(edge.rev).cap += f;

                pre = ppre;
            }

            System.out.println();
        }

        System.out.println(ans);

    }



    public static void test(){

        MaxFlow3 maxFlow = new MaxFlow3();
        maxFlow.init(6);
        maxFlow.add(0, 1, 10);
        maxFlow.add(0, 2, 10);
        maxFlow.add(1, 2, 2);
        maxFlow.add(1, 3, 4);
        maxFlow.add(1, 4, 8);
        maxFlow.add(2, 4, 9);
        maxFlow.add(4, 3, 6);
        maxFlow.add(3, 5, 10);
        maxFlow.add(4, 5, 10);
        maxFlow.calc(0, 5);
//        maxFlow.init(4);
//        maxFlow.addFromOne(1, 2, 40);
//        maxFlow.addFromOne(1, 4, 20);
//        maxFlow.addFromOne(2, 4, 20);
//        maxFlow.addFromOne(2, 3, 30);
//        maxFlow.addFromOne(3, 4, 10);
//        maxFlow.calc(0, 3);
    }

}
