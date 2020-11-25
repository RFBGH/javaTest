package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class eraseOverlapIntervals {

    private static class Segment{
        int from;
        int to;

        public Segment(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    public int eraseOverlapIntervals(int[][] intervals) {

        int n = intervals.length;
        List<Segment> segments = new ArrayList<>();
        for(int i = 0; i < n; i++){
            segments.add(new Segment(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
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
        for(int i = 1; i < segments.size();){
            Segment segment0 = segments.get(i-1);
            Segment segment1 = segments.get(i);
            if(segment1.from >= segment0.to){
                i++;
                continue;
            }

            if(segment0.to > segment1.to){
                segments.remove(i-1);
            }else{
                segments.remove(i);
            }
            sum++;
        }

        return sum;
    }
}
