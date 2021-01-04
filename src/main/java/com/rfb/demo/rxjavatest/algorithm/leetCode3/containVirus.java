package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class containVirus {

    private static class Node{
        public int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i &&
                    j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    private static class Scope{
        public List<Node> nodes;
        public Set<Node> edges;
        public int doors;

        public Scope(List<Node> nodes, Set<Node> edges, int doors) {
            this.nodes = nodes;
            this.edges = edges;
            this.doors = doors;
        }
    }

    private int[][] move = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private List<Scope> getAllScope(int[][] grid){

        List<Scope> scopes = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] gone = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(grid[i][j] != 1){
                    continue;
                }

                if(gone[i][j]){
                    continue;
                }

                gone[i][j] = true;
                List<Node> nodes = new ArrayList<>();
                nodes.add(new Node(i, j));

                int front = 0;
                int doors = 0;
                Set<Node> edges = new HashSet<>();

                while (front < nodes.size()){
                    Node cur = nodes.get(front);

                    for(int k = 0; k < 4; k++){
                        int nextI = cur.i + move[k][0];
                        int nextJ = cur.j + move[k][1];
                        if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m){
                            continue;
                        }

                        if(grid[nextI][nextJ] == 0){
                            doors++;
                            edges.add(new Node(nextI, nextJ));
                        }else if(grid[nextI][nextJ] == 2){
                            //...
                        }else{
                            if(!gone[nextI][nextJ]){
                                gone[nextI][nextJ] = true;
                                nodes.add(new Node(nextI, nextJ));
                            }
                        }
                    }

                    front++;
                }

                if(edges.isEmpty()){
                    continue;
                }

                scopes.add(new Scope(nodes, edges, doors));
            }
        }

        return scopes;
    }

    public int containVirus(int[][] grid) {

        int ans = 0;
        while (true){
            List<Scope> scopes = getAllScope(grid);
            if(scopes.isEmpty()){
                break;
            }
            int max = 0;
            Scope target = null;
            for(Scope scope : scopes){
                if(max < scope.edges.size()){
                    max = scope.edges.size();
                    target = scope;
                }
            }

            if(target == null){
                break;
            }

            ans += target.doors;
            for(Node node : target.nodes){
                grid[node.i][node.j] = 2;
            }

            for(Scope scope : scopes){

                if(target == scope){
                    continue;
                }

                for(Node node : scope.edges){
                    grid[node.i][node.j] = 1;
                }
            }
        }

        return ans;
    }

    public void test(){
        System.out.println(containVirus(new int[][]
                {
                        {0,1,0,0,0,0,0,1},
                        {0,1,0,1,0,0,0,1},
                        {0,0,0,0,0,0,0,1}
                }));
    }
}
