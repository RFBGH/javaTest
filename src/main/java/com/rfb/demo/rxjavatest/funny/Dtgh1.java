package com.rfb.demo.rxjavatest.funny;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/21 0021.
 */
public class Dtgh1 {

    public void test(){

        int n = 30;
        int array[] = new int[n];

        Random rd = new Random(System.currentTimeMillis());
        for(int i = 0; i < n; i++){
            array[i] = rd.nextInt(1000);

            System.out.print(array[i]+" ");
        }
        System.out.println();

        int ans[] = new int[n];
        int pre[] = new int[n];
        ans[0] = 1;
        pre[0] = -1;
        for(int i = 1; i < n; i++){

            int max = 0;
            int last = -1;
            for(int j = i-1; j >= max; j--){
                if(array[i] > array[j] && max < ans[j]){
                    max = ans[j];
                    last = j;
                }
            }

            ans[i] = max+1;
            pre[i] = last;
        }

        int max = 0;
        int index = 0;
        for(int i = 0; i < n; i++){
            if(max < ans[i]){
                max = ans[i];
                index = i;
            }
        }

        System.out.println("max: "+max);

        while (index != -1){
            System.out.print(array[index]+" ");
            index = pre[index];
        }
        System.out.println();
    }
}
