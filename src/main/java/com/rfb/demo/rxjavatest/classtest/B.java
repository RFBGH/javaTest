package com.rfb.demo.rxjavatest.classtest;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class B extends A{


    public B(){
        super();
    }

    @Override
    public void init() {
        System.out.println(getString());
    }

    public String getString(){
        return mXX.s;
    }

    private XX mXX = new XX("xxxx");
}
