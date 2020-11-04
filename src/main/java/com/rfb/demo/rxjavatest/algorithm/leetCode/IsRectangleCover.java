package com.rfb.demo.rxjavatest.algorithm.leetCode;


import java.util.ArrayList;
import java.util.List;

public class IsRectangleCover {

    public boolean isRectangleCover(int[][] rectangles) {

        int size = rectangles.length;

        int leftest = Integer.MAX_VALUE;
        int bottomest = Integer.MAX_VALUE;
        int topest = Integer.MIN_VALUE;
        int rightest = Integer.MIN_VALUE;

        int sum = 0;

        for(int i = 0; i < size; i++){

            if(leftest > rectangles[i][0]){
                leftest = rectangles[i][0];
            }
            if(bottomest > rectangles[i][1]){
                bottomest = rectangles[i][1];
            }
            if(rightest < rectangles[i][2]){
                rightest = rectangles[i][2];
            }
            if(topest < rectangles[i][3]){
                topest = rectangles[i][3];
            }

            sum += (rectangles[i][2]-rectangles[i][0])*(rectangles[i][3]-rectangles[i][1]);

            for(int j = i+1; j < size; j++){

                int maxLeft = Math.max(rectangles[i][0], rectangles[j][0]);
                int minRight = Math.min(rectangles[i][2], rectangles[j][2]);
                int maxBottom = Math.max(rectangles[i][1], rectangles[j][1]);
                int minTop = Math.min(rectangles[i][3], rectangles[j][3]);

                if(maxLeft < minRight && maxBottom < minTop){
                    return false;
                }
            }
        }

        int check = (rightest - leftest)*(topest - bottomest);
        return check == sum;
    }

    public void test(){

        System.out.println(isRectangleCover(new int[][] {
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        }));

        System.out.println(isRectangleCover(new int[][] {
                {1,1,2,3},
                {1,3,2,4},
                {3,1,4,2},
                {3,2,4,4}
        }));
    }

}
