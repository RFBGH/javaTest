package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class reachingPoints {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        int x = tx;
        int y = ty;
        while (true){

            if(x > y){
                x = x - y;
            }else{
                y = y - x;
            }

            if(x == sx && y == sy){
                return true;
            }

            if(x < sx || y < sy){
                return false;
            }
        }
    }

    public void test(){
        System.out.println(reachingPoints(1, 8, 4, 15));
    }
}
