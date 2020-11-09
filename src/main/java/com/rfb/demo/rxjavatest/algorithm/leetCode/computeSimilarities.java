package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class computeSimilarities {

    public List<String> computeSimilarities(int[][] docs) {

        String format = "%d,%d: %.4f";
        int n = docs.length;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < n; i++){

            Set<Integer> set = new HashSet<>();
            for(int k = 0, size = docs[i].length; k < size; k++){
                set.add(docs[i][k]);
            }


            for(int j = i+1; j < n; j++){

                int sum = 0;
                for(int k = 0, size = docs[j].length; k < size; k++){
                    if(set.contains(docs[j][k])){
                        sum++;
                    }
                }

                if(sum == 0){
                    continue;
                }
                double d = (double)sum / (set.size()+(docs[j].length-sum));
                list.add(String.format(format, i, j, d));
            }
        }

        return list;
    }

    public void test(){
        System.out.println(computeSimilarities(new int[][]{{14, 15, 100, 9, 3},{32, 1, 9, 3, 5},{15, 29, 2, 6, 8, 7}, {7, 10}}));
    }
}
