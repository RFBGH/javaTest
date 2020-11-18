package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class maxEnvelopes {

    public static class Envelope{
        int w;
        int h;

        public Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes.length == 0){
            return 0;
        }

        if(envelopes.length == 1){
            return 1;
        }

        int n = envelopes.length;

        List<Envelope> envelopeList = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            envelopeList.add(new Envelope(envelopes[i][0], envelopes[i][1]));
        }

        Collections.sort(envelopeList, new Comparator<Envelope>() {
            @Override
            public int compare(Envelope o1, Envelope o2) {
                if(o1.w < o2.w){
                    return -1;
                }

                if(o1.w > o2.w){
                    return 1;
                }

                if(o1.h < o2.h){
                    return -1;
                }

                if(o1.h > o2.h){
                    return 1;
                }

                return 0;
            }
        });

        for(int i = 0; i < n; i++){
            envelopes[i][0] = envelopeList.get(i).w;
            envelopes[i][1] = envelopeList.get(i).h;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int result = 0;
        for(int i = 1; i < n; i++){
            int max = 0;
            for(int j = 0; j < i-max; j++){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    if(max < dp[j]){
                        max = dp[j];
                    }
                }
            }

            dp[i] = max+1;
            if(result < dp[i]){
                result = dp[i];
            }
        }
        return result;
    }

    public void test(){
        System.out.println(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }

}
