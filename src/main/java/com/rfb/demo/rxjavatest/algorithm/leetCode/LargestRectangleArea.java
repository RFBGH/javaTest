package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {

        int max = 0;
        for(int i = 0, size = heights.length; i < size; i++){
            int left = i-1;
            while (left >= 0 && heights[left] >= heights[i]){
                left--;
            }

            int right = i;
            while (right < size && heights[right] >= heights[i]){
                right++;
            }

            int rect = (i - left + right - i - 1) * heights[i];
            if(rect > max){
                max = rect;
            }
        }

        return max;
    }

    public void test(){
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }


}
