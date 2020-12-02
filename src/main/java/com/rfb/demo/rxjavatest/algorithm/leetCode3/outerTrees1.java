package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class outerTrees1 {

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int distant(Point p1, Point p2){
        return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y);
    }

    private int distance(Point p1, Point p2, Point p){
        return (p2.y - p1.y)*p.x - (p2.x - p1.x)*p.y + p2.x*p1.y - p1.x*p2.y;
    }

    private List<Point> result = new ArrayList<>();

    private void dfsUp(Point p1, Point p2, List<Point> points){

        int min = Integer.MAX_VALUE;
        List<Point> targets = new ArrayList<>();

        for(Point point : points){
            if(result.contains(point)){
                continue;
            }

            int distance = distance(p1, p2, point);
            if(distance < min){
                min = distance;
                targets.clear();
                targets.add(point);
            }else if(distance == min){
                targets.add(point);
            }
        }

        if(min > 0){
            return;
        }

        if(min == 0){
            result.addAll(targets);
            return;
        }

        result.add(targets.get(0));
        dfsUp(p1, targets.get(0), points);
        dfsUp(targets.get(0), p2, points);
    }

    private void dfsDown(Point p1, Point p2, List<Point> points){

        int max = Integer.MIN_VALUE;
        List<Point> targets = new ArrayList<>();

        for(Point point : points){

            if(result.contains(point)){
                continue;
            }

            int distance = distance(p1, p2, point);
            if(distance > max){
                max = distance;
                targets.clear();
                targets.add(point);
            }else if(distance == max){
                targets.add(point);
            }
        }

        if(max < 0){
            return;
        }

        if(max == 0){
            result.addAll(targets);
            return;
        }

        result.add(targets.get(0));
        dfsDown(p1, targets.get(0), points);
        dfsDown(targets.get(0), p2, points);
    }

    public int[][] outerTrees(int[][] points) {

        if(points.length < 3){
            return points;
        }

        List<Point> pointList = new ArrayList<>();
        for(int i = 0; i < points.length; i++){
            pointList.add(new Point(points[i][0], points[i][1]));
        }

        Collections.sort(pointList, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x < o2.x){
                    return -1;
                }

                if(o1.x > o2.x){
                    return 1;
                }

                if(o1.y < o2.y){
                    return -1;
                }

                if(o1.y > o2.y){
                    return 1;
                }

                return 0;
            }
        });

        int maxDistance = 0;
        Point targetP1 = null;
        Point targetP2 = null;

        for(int i = 0; i < pointList.size(); i++){
            Point p1 = pointList.get(i);
            for(int j = i+1; j < pointList.size(); j++){
                Point p2 = pointList.get(j);

                int distance = Math.abs(distant(p1, p2));
                if(distance > maxDistance){
                    maxDistance = distance;
                    targetP1 = p1;
                    targetP2 = p2;
                }
            }
        }

        result.add(targetP1);
        result.add(targetP2);

        dfsUp(targetP1, targetP2, pointList);
        dfsDown(targetP1, targetP2, pointList);

        int[][] ans = new int[result.size()][2];
        for(int i = 0; i < result.size(); i++){
            Point point = result.get(i);
            ans[i][0] = point.x;
            ans[i][1] = point.y;
        }

        return ans;
    }

    public void test(){
        outerTrees(new int[][]{{11,50},{43,16},{45,55},{74,20},{67,33},{66,4},{67,56},{37,32},{48,96},{81,33},{22,17},{64,92},{71,17},{3,25},{15,91},{1,46},{70,76},{3,15},{23,30},{19,17},{39,82},{32,45},{94,61},{49,98},{43,64},{85,28},{23,39},{55,20},{41,12},{36,58},{59,9},{50,5},{97,19},{57,34},{10,33},{24,80},{35,22},{41,62},{21,53},{68,58},{32,24},{36,69},{82,86},{75,31},{49,9},{9,25},{51,4},{18,84},{5,49},{25,69},{20,59},{17,40},{15,28},{29,91},{10,97},{28,37},{41,9},{82,37},{13,21},{10,4},{43,7},{66,44},{39,67},{26,98},{75,93},{96,56},{60,83},{9,92},{50,83},{33,2},{24,94},{41,48},{95,2},{13,84},{10,41},{65,52},{69,91},{38,24},{6,82},{59,67},{50,75},{36,61},{38,49},{13,50},{8,55},{15,31},{69,37},{97,61},{28,48},{7,56},{18,61},{0,22},{15,97},{0,50},{30,71},{69,89},{84,83},{96,49},{89,4}});
    }
}
