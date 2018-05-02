package com.rfb.demo.rxjavatest.bean;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
public class MyAdd {
    public MyAdd(int pData){
        if(pData <= 0){
            throw new IllegalArgumentException("can not less than 0");
        }
    }
}
