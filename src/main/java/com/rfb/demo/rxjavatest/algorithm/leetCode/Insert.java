package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Insert {

    private int find(int[][] intervals, int x){

        if(x < intervals[0][0]){
            return -1;
        }

        int size = intervals.length;
        if(x > intervals[size-1][1]){
            return size;
        }

        int left = 0;
        int right = size-1;

        while (true){

            if(left == right){
                return left;
            }

            int mid = (left + right) / 2;
            if( x == intervals[mid][0]){
                return mid;
            }

            if(x > intervals[mid][0]){
                if(mid <= size-2 && x < intervals[mid+1][0]){
                    return mid;
                }
                left = mid+1;
            }else{
                right = mid;
            }
        }
    }

    private void copyArrays(int[][] src, int from, int to, int[][] dst, int offset){

        for(int i = from; i < to; i++){
            dst[offset+i-from][0] = src[i][0];
            dst[offset+i-from][1] = src[i][1];
        }

    }

    private static class Range{
        int from;
        int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public Range() {
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        if(intervals.length == 0){
            int[][] result = new int[1][2];
            result[0][0] = newInterval[0];
            result[0][1] = newInterval[1];
            return result;
        }

        int from = newInterval[0];
        int to = newInterval[1];

        int size = intervals.length;

        int find = find(intervals, from);
        if(find == size){
            int[][] result = new int[size+1][2];
            copyArrays(intervals, 0, size, result, 0);
            result[size][0] = newInterval[0];
            result[size][1] = newInterval[1];
            return result;
        }

        int find2 = find(intervals, to);
        if(find2 == -1){
            int[][] result = new int[size+1][2];
            result[0][0] = newInterval[0];
            result[0][1] = newInterval[1];
            copyArrays(intervals, 0, size, result, 1);
            return result;
        }

        List<Range> ranges = new ArrayList<>();
        for(int i = 0; i < find; i++){
            ranges.add(new Range(intervals[i][0], intervals[i][1]));
        }

        Range range = new Range();
        if(find == -1){
            range.from = from;
        }else {
            if(from <= intervals[find][1]){
                range.from = intervals[find][0];
            }else{
                ranges.add(new Range(intervals[find][0], intervals[find][1]));
                range.from = newInterval[0];
            }
        }

        if(find2 == size){
            range.to = to;
        }else{
            if(to <= intervals[find2][1]){
                range.to = intervals[find2][1];
            }else{
                range.to = to;
            }
        }

        ranges.add(range);
        for(int i = find2+1; i < size; i++){
            ranges.add(new Range(intervals[i][0], intervals[i][1]));
        }

        int[][] result = new int[ranges.size()][2];
        for(int i = 0, sze = ranges.size(); i < sze; i++){
            result[i][0] = ranges.get(i).from;
            result[i][1] = ranges.get(i).to;
        }
        return result;
    }

    public void test(){

//        insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8});

        insert(new int[][]{{1,5}}, new int[]{0,0});

    }
}
