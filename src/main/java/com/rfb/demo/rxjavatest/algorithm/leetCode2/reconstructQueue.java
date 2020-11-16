package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class reconstructQueue {

    public int[][] reconstructQueue(int[][] people) {

        if(people == null || people.length == 1){
            return people;
        }

        List<Integer> indexs = new ArrayList<>();

        int n = people.length;
        int[][] temp = new int[n][2];
        for(int i = 0; i < n; i++){
            temp[i][0] = people[i][0];
            temp[i][1] = people[i][1];
        }

        for(int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int minLastHeight = Integer.MAX_VALUE;
            int minIndex = Integer.MAX_VALUE;

            for(int j = 0; j < n; j++){

                if(temp[j][1] < 0){
                    continue;
                }

                if(minLastHeight < temp[j][1]){
                    continue;
                }

                if(minLastHeight > temp[j][1]){
                    minIndex = j;
                    minLastHeight = temp[j][1];
                    min = temp[j][0];
                }else if( minLastHeight == temp[j][1] && min > temp[j][0]){
                    minIndex = j;
                    minLastHeight = temp[j][1];
                    min = temp[j][0];
                }
            }

            indexs.add(minIndex);
            for(int j = 0; j < n; j++){
                if(min >= temp[j][0]){
                    temp[j][1]--;
                }
            }
        }

        for(int i = 0; i < n; i++){
            int index = indexs.get(i);
            temp[i][0] = people[index][0];
            temp[i][1] = people[index][1];
        }

        return temp;
    }

    public void test(){
        reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});

    }

}
