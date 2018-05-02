package com.rfb.demo.rxjavatest.classtest2;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class Son extends Father{

    private String mSaid;

    public Son(){
        super();
    }

    @Override
    protected void init() {
        super.init();

        mSaid = "hello world";
    }

    public void print(){
        System.out.println(mSaid);
    }
}
