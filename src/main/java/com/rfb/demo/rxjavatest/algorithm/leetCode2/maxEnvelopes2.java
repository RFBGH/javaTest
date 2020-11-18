package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class maxEnvelopes2 {

    public static class Envelope{
        int w;
        int h;

        public Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    private int[] deeps;

    private int dfs(int[][] envelopes, int cur){

        if(deeps[cur] != -1){
            return deeps[cur];
        }

        int n = envelopes.length;
        int max = -1;
        for(int i = 0; i < n; i++){
            if(i == cur){
                continue;
            }

            if(envelopes[cur][0] < envelopes[i][0] && envelopes[cur][1] < envelopes[i][1]){
                int temp = dfs(envelopes, i);
                if(temp > max){
                    max = temp;
                }
            }
        }

        max++;
        deeps[cur] = max;
        return max;
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

        deeps = new int[n];
        for(int i = 0; i < n; i++){
            deeps[i] = -1;
        }

        for(int i = 0; i < n; i++){
            envelopes[i][0] = envelopeList.get(i).w;
            envelopes[i][1] = envelopeList.get(i).h;
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            int temp = dfs(envelopes, i);
            if(temp > max){
                max = temp;
            }
        }
        return max+1;
    }

    public void test(){
        System.out.println(maxEnvelopes(new int[][]{{20,48},{12,35},{32,16},{30,17},{35,23},{37,33},{14,10},{23,31},{8,7},{17,6},{6,19},{3,6},{22,34},{10,26},{16,46},{20,11},{20,28},{39,33},{44,47},{29,18},{8,25},{17,24},{43,27},{45,12},{40,29},{37,10},{49,20},{2,4},{33,1},{46,27},{39,24},{34,6},{15,15},{21,40},{7,30},{19,9},{11,39},{29,31},{28,37},{4,37},{8,36},{38,1},{48,46},{4,1},{43,29},{41,32},{19,23},{37,35},{31,9},{8,1},{34,30},{2,20},{49,21},{16,26},{38,12},{27,20},{43,7},{25,8},{17,36},{42,40},{45,14},{16,10},{19,47},{5,37},{21,7},{30,3},{42,40},{18,40},{35,41},{12,33},{4,15},{40,22},{4,29},{27,27},{41,40},{12,33},{37,41},{21,11},{5,24},{4,32}}));
    }

}
