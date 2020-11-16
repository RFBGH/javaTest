package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class isSelfCrossing {

    private static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point point) {
            this.x = point.x;
            this.y = point.y;
        }
    }

    private boolean isLineCross(Point p1, Point p2, Point p3, Point p4){

        if(p3.y == p4.y){
            Point p = p3;
            p3 = p1;
            p1 = p;

            p = p4;
            p4 = p2;
            p2 = p;
        }

        if(p1.x > p2.x){
            int t = p1.x;
            p1.x = p2.x;
            p2.x = t;
        }

        if(p3.y > p4.y){
            int t = p3.y;
            p3.y = p4.y;
            p4.y = t;
        }

        if(p1.y < p3.y || p1.y > p4.y){
            return false;
        }

        if(p3.x < p1.x || p3.x > p2.x){
            return false;
        }

        return true;
    }

    public boolean isSelfCrossing(int[] o) {

        List<Point> points = new ArrayList<>();

        points.add(new Point(0, 0));
        int x = 0;
        int y = 0;

        for(int i = 0, size = o.length; i < size; i++){
            if(i % 4 == 0){
                y += o[i];
            }else if(i % 4 == 1){
                x -= o[i];
            }else if(i % 4 == 2){
                y -= o[i];
            }else {
                x += o[i];
            }
            points.add(new Point(x, y));
        }

        for(int i = 0, size = points.size()-1; i < size; i++){

            for(int j = i + 3; j < points.size()-1; j++){
                if(isLineCross(new Point(points.get(i)), new Point(points.get(i+1)),
                        new Point(points.get(j)),new Point( points.get(j+1)))){
                    return true;
                }
            }
        }

        return false;
    }

    public void test(){
        System.out.println(isSelfCrossing(new int[]{2,1,1,2}));
        System.out.println(isSelfCrossing(new int[]{1,2,3,4}));
        System.out.println(isSelfCrossing(new int[]{1,1,1,1}));
        System.out.println(isSelfCrossing(new int[]{1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1}));
    }
}
