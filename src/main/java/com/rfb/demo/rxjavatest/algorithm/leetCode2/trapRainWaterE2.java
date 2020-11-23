package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class trapRainWaterE2 {

    private static class Node{
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static class SumInfo{
        int sum;
        int top;
        int bottom;

        public SumInfo(int sum, int top, int bottom) {
            this.sum = sum;
            this.top = top;
            this.bottom = bottom;
        }
    }

    private int[][] move = new int[][]{{-1, 0},{0,-1},{1,0},{0,1}};
    private SumInfo trapRainWaterWithHeight(int[][] heightMap, int h){

        int sum = 0;
        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] gone = new boolean[n][m];
        boolean allLess = true;
        int biggerHMin = Integer.MAX_VALUE;
        int smallerHMax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(heightMap[i][j] >= h){

                    if(biggerHMin > heightMap[i][j]){
                        biggerHMin = heightMap[i][j];
                    }

                    continue;
                }

                if(heightMap[i][j] > smallerHMax){
                    smallerHMax = heightMap[i][j];
                }

                allLess = false;
                if(gone[i][j]){
                    continue;
                }

                gone[i][j] = true;
                List<Node> queue = new ArrayList<>();
                queue.add(new Node(i, j));
                int front = 0;
                while (front < queue.size()){
                    Node cur = queue.get(front);

                    for(int k = 0; k < 4; k++){
                        int nextI = cur.i + move[k][0];
                        int nextJ = cur.j + move[k][1];
                        if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m){
                            continue;
                        }

                        if(heightMap[nextI][nextJ] >= h){
                            continue;
                        }

                        if(gone[nextI][nextJ]){
                            continue;
                        }

                        gone[nextI][nextJ] = true;
                        queue.add(new Node(nextI, nextJ));
                    }

                    front++;
                }

                int minEdgeHeight = Integer.MAX_VALUE;
                int minHeight = Integer.MAX_VALUE;

                for(Node node : queue){
                    if(node.i == 0 || node.i == n-1 || node.j == 0 || node.j == m-1){
                        if(minEdgeHeight > heightMap[node.i][node.j]){
                            minEdgeHeight = heightMap[node.i][node.j];
                        }
                    }

                    if(minHeight > heightMap[node.i][node.j]){
                        minHeight = heightMap[node.i][node.j];
                    }
                }

                if(minHeight < minEdgeHeight){

                    int minH = Math.min(minEdgeHeight, h);
                    for(Node node: queue){
                        if(heightMap[node.i][node.j] < minH){
                            sum += minH-heightMap[node.i][node.j];
                            heightMap[node.i][node.j] = minH;
                        }
                    }
                }

            }
        }

        if(allLess){
            sum = -1;
        }

        return new SumInfo(sum, biggerHMin, smallerHMax);
    }

    private int dfs(int[][] heightMap, int min, int max){

        if(min > max){
            return 0;
        }

        if(min == max){
            SumInfo sum = trapRainWaterWithHeight(heightMap, min);
            if(sum.sum < 0){
                sum.sum = 0;
            }
            return sum.sum;
        }

        if(min + 1 == max){
            SumInfo sum1 = trapRainWaterWithHeight(heightMap, min);
            if(sum1.sum < 0){
                sum1.sum = 0;
            }
            SumInfo sum2 = trapRainWaterWithHeight(heightMap, max);
            if(sum2.sum < 0){
                sum2.sum = 0;
            }
            return sum1.sum + sum2.sum;
        }

        int mid = (min + max) / 2;
        SumInfo sum = trapRainWaterWithHeight(heightMap, mid);

        if(sum.sum < 0){
            return dfs(heightMap, Math.max(sum.top + 1, mid + 1), max);
        }else if(sum.sum == 0){
            return dfs(heightMap,  Math.max(sum.top + 1, mid + 1), max)
                    + dfs(heightMap, min, Math.min(mid, sum.bottom));
        }else{
            return sum.sum + dfs(heightMap,  Math.max(sum.top + 1, mid + 1), max)
                    + dfs(heightMap, min, Math.min(mid, sum.bottom));
        }
    }

    public int trapRainWater(int[][] heightMap) {

        int n = heightMap.length;
        int m = heightMap[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(min > heightMap[i][j]){
                    min = heightMap[i][j];
                }

                if(max < heightMap[i][j]){
                    max = heightMap[i][j];
                }
            }
        }

        return dfs(heightMap, min, max);
    }

    public void test(){
        System.out.println(trapRainWater(new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}}));
    }
}
