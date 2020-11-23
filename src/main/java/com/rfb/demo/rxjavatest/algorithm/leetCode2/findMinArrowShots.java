package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class findMinArrowShots {

    private static class Ball{
        int from;
        int to;

        public Ball(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        List<Ball> balls = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            balls.add(new Ball(points[i][0], points[i][1]));
        }

        Collections.sort(balls, new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                if(o1.from < o2.from){
                    return -1;
                }

                if(o1.from > o2.from){
                    return 1;
                }

                if(o1.to < o2.to){
                    return -1;
                }

                if(o1.to > o2.to){
                    return 1;
                }
                return 0;
            }
        });

        int sum = 0;
        for(int i = 0; i < n;){
            Ball ball0 = balls.get(i);
            int to = ball0.to;

            i++;
            while (i < n){
                Ball ball1 = balls.get(i);
                if(ball1.from > to){
                    break;
                }

                to = Math.min(to, ball1.to);
                i++;
            }

            sum++;
        }

        return sum;
    }

    public void test(){
        System.out.println(findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
        System.out.println(findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}}));
        System.out.println(findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
    }

}
