package com.rfb.demo.rxjavatest.funny;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class HowManyZero {

    public void test(int n){

        int sum = 1;
        int result = 0;

        for(int i = 2; i <= n; i++){

            sum *= i;

            System.out.println("sum: "+sum);

            while(sum % 10 == 0){
                sum /= 10;
                result++;
            }

            System.out.println("after kill 10 sum: "+sum+" result: "+result);
        }

        System.out.println("result: "+result);
    }
}
