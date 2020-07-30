package com.rfb.demo.rxjavatest.algorithm.maxFlow.poj2391;

import java.util.*;

/**
 * Created by Administrator on 2020/7/29 0029.
 */
public class Main4 {

    private static class Field{
        int cow;
        int shed;
    }

    private static class Node{
        int from;
        int flow;
        int pre;

        private Node(int from, int flow, int pre) {
            this.from = from;
            this.flow = flow;
            this.pre = pre;
        }
    }

    private static int dfs(int s, int f, int n, boolean[] used,  int[][] G){

        List<Node> queue = new ArrayList<Node>();
        int front = 0;
        Node node = new Node(s, f, -1);
        queue.add(node);
        used[s] = true;

        boolean find = false;

        while (front < queue.size()){

            Node cur = queue.get(front);
            int from = cur.from;
            int flow = cur.flow;
            for(int i = 0; i < n; i++){

                int to = i;
                int cap = G[from][i];

                if(used[to]){
                    continue;
                }

                if(cap <= 0){
                    continue;
                }

                used[to] = true;
                Node newNode = new Node(to, Math.min(flow, cap), front);
                queue.add(newNode);

                if(newNode.from == n-1){
                    find = true;
                    break;
                }
            }

            if(find){
                break;
            }

            front++;
        }

        if(!find){
            return 0;
        }

        Node cur = queue.get(queue.size()-1);
        int resultFlow = cur.flow;

        while (true){

            if(cur.pre == -1){
                break;
            }

            Node last = queue.get(cur.pre);
            int from = last.from;
            int to = cur.from;

            G[from][to] -= resultFlow;
            G[to][from] += resultFlow;

            cur = last;
        }

        return resultFlow;
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
            long cost = scanner.nextInt();
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
                G[F+1+i][n-1] = fields[i].shed;
                G[1+i][F+1+i] = Integer.MAX_VALUE;

                for(int j = 0; j < F; j++){
                    if(dist[i][j] > cut){
                        continue;
                    }

                    G[1+i][F+1+j] = Integer.MAX_VALUE;
                }
            }

            int allFlow = 0;
            while (true){

                for(int i = 0; i < n; i++){
                    used[i] = false;
                }

                int f = dfs(0, Integer.MAX_VALUE, n, used, G);
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
