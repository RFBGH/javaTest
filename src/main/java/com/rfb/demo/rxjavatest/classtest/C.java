package com.rfb.demo.rxjavatest.classtest;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class C extends B{

    public C() {
        super();
    }

    @Override
    public void init(){
        System.out.println(getString());
    }

    public void printXX(){
        System.out.println("Xxxxx "+getString());
    }
}
