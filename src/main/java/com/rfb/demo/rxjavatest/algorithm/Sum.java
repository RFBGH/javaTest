package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/6/23 0023.
 */
public class Sum {

    private static List<Integer> ans = new ArrayList<>();

    public static void countSum(int n, int less){

        if(n < 0){
            return;
        }

        if(n == 0){
            for(int i = 0; i < ans.size(); i++){
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
            return;
        }

        for(int i = less; i >= 1; i--){

            ans.add(i);
            countSum(n - i, i);
            ans.remove((Integer)i);
        }
    }

}
