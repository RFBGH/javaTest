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

    public static void addEdge(List<Edge>[]G, int from, int to, int cap){
        Edge edge = new Edge(to, cap, G[to].size());
        G[from].add(edge);

        edge = new Edge(from, 0, G[from].size()-1);
        G[to].add(edge);
    }

    private static int dfs(int from, int flow, int n, int[] level,  List<Edge>[] G){

        if(from == n-1){
            return flow;
        }

        List<Edge> edges = G[from];
        for(Edge edge : edges){

            int to = edge.to;
            int cap = edge.cap;
            if(level[to] < level[from]){
                continue;
            }

            if(cap == 0){
                continue;
            }

            int f = dfs(to, Math.min(cap, flow), n, level, G);
            if(f != 0){
                edge.cap -= f;
                G[edge.to].get(edge.rev).cap += f;
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
        int n = F*2 + 2;
        List<Edge>[] G = new ArrayList[n];
        for(int i = 0; i < n; i++){
            G[i] = new ArrayList<Edge>();
        }
        int[] level = new int[n];

        int left = 0;
        int right = length.size()-1;
        while (left <= right){

            int mid = (left + right) / 2;
            long cut = length.get(mid);

            for(int i = 0; i < n; i++){
                G[i].clear();
            }

            for(int i = 0; i < F; i++){
                addEdge(G, 0, i+1, fields[i].cow);
                addEdge(G, F+1+i, n-1, fields[i].shed);
                addEdge(G, 1 + i, F + 1 + i, Integer.MAX_VALUE);
            }

            for(int i = 0; i < F; i++){
                for(int j = 0; j < F; j++){
                    if(dist[i][j] > cut){
                        continue;
                    }

                    addEdge(G, 1+i, F+j+1, Integer.MAX_VALUE);
                }
            }

            int allFlow = 0;

            while (true){
                for(int i = 0; i < n; i++){
                    level[i] = -1;
                }

                boolean find = false;
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.add(0);
                level[0] = 0;
                while (!queue.isEmpty()){

                    int cur = queue.poll();
                    List<Edge> edges = G[cur];
                    for(Edge edge : edges){

                        int to = edge.to;
                        if(edge.cap == 0){
                            continue;
                        }

                        if(level[to] != -1){
                            continue;
                        }

                        level[to] = level[cur]+1;
                        queue.offer(to);

                        if(to == n-1){
                            find = true;
                            break;
                        }
                    }
                    if(find){
                        break;
                    }
                }

                if(!find){
                    break;
                }

                while (true){

                    int f = dfs(0, Integer.MAX_VALUE, n, level, G);
                    if(f == 0){
                        break;
                    }
                    allFlow += f;
                }
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
