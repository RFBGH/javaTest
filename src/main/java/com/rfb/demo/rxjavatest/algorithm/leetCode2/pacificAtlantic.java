package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pacificAtlantic {

    private static class Node{
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private int[][] move = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        if(matrix.length == 0){
            return new ArrayList<>();
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] color = new int[n][m];

        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            color[i][0] = 1;
            queue.add(new Node(i, 0));
        }

        for(int j = 1; j < m; j++){
            color[0][j] = 1;
            queue.add(new Node(0, j));
        }

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            int curH = matrix[cur.i][cur.j];
            for(int k = 0; k < 4; k++){
                int i = cur.i + move[k][0];
                int j = cur.j + move[k][1];
                if(i < 0 || i >= n || j < 0 || j >= m){
                    continue;
                }

                if(matrix[i][j] < curH){
                    continue;
                }

                if(color[i][j] != 0){
                    continue;
                }

                color[i][j] = 1;
                queue.offer(new Node(i, j));
            }
        }

        List<Node> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            Node node = new Node(i, m-1);
            color[i][m-1] += 2;
            if(color[i][m-1] == 3){
                result.add(node);
            }
            queue.offer(node);
        }

        for(int j = 0; j < m-1; j++){

            Node node = new Node(n-1, j);
            color[n-1][j] += 2;
            if(color[n-1][j] == 3){
                result.add(node);
            }
            queue.offer(node);
        }

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curH = matrix[cur.i][cur.j];
            for(int k = 0; k < 4; k++){
                int i = cur.i + move[k][0];
                int j = cur.j + move[k][1];
                if(i < 0 || i >= n || j < 0 || j >= m){
                    continue;
                }

                if(matrix[i][j] < curH){
                    continue;
                }

                Node node = new Node(i, j);
                if(color[i][j] > 1){
                    continue;
                }

                color[i][j] += 2;
                if(color[i][j] == 3){
                    result.add(node);
                }
                queue.offer(node);
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        for(Node node : result){
            List<Integer> integers = new ArrayList<>();
            integers.add(node.i);
            integers.add(node.j);
            list.add(integers);
        }

        return list;
    }

    public void test(){
        pacificAtlantic(new int[][]{});
    }
}
