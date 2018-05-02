package com.rfb.demo.rxjavatest.funny;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
public class PerfectSqrt{

    private static void perfectSqrt(int n){

        while(true){

            int next = (int)Math.sqrt(n);
            System.out.print(next+" ");

            n -= next*next;

            if(n == 0){
                break;
            }
        }

        System.out.println();
    }

    public static void test(){

        perfectSqrt(12);
        perfectSqrt(13);
        perfectSqrt(14);
        perfectSqrt(15);
        perfectSqrt(16);
        perfectSqrt(17);
        perfectSqrt(112313);

    }

}
