package com.rfb.demo.rxjavatest.funny;

import java.util.Random;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public class MaxRect {

    public void testXX(){

        Random rd = new Random(System.currentTimeMillis());
        int array[] = new int[10000];

        for(int i = 0; i < 10000; i++){
            array[i] = rd.nextInt(1000000);
        }

        test(array);
    }

    public void test(int heighs[]){

        int result = 0;

        for(int i = 0, size = heighs.length; i < size; i++){

            int max = heighs[i];
            int minHeight = heighs[i];
            for(int j = i-1; j >= 0; j--){

                if(heighs[j] < minHeight){
                    minHeight = heighs[j];
                }

                if(max < minHeight * (i-j+1)){
                    max = minHeight * (i-j+1);
                }

            }

            if(max > result){
                result = max;
            }
        }

        System.out.println("result: "+result);
    }

}
