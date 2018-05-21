package com.rfb.demo.rxjavatest;

/**
 * Created by Administrator on 2018/5/21 0021.
 */
public class DelayUtil {

    public static void delay(int mills){

        try{
            Thread.sleep(mills);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
