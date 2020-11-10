package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class kClosest {

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distZero(){
            return x*x + y*y;
        }
    }

    public int[][] kClosest(int[][] points, int K) {

        List<Point> pointList = new ArrayList<>();
        for(int i = 0, size = points.length; i < size; i++){
            pointList.add(new Point(points[i][0], points[i][1]));
        }

        Collections.sort(pointList, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int dist1 = o1.distZero();
                int dist2 = o2.distZero();

                if(dist1 < dist2){
                    return -1;
                }

                if(dist1 > dist2){
                    return 1;
                }
                return 0;
            }
        });

        int[][] result = new int[K][2];
        for(int i = 0; i < K; i++){
            result[i][0] = pointList.get(i).x;
            result[i][1] = pointList.get(i).y;
        }

        return result;
    }

}
