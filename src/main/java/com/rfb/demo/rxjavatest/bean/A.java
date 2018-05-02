package com.rfb.demo.rxjavatest.bean;

/**
 * Created by Administrator on 2016/12/27 0027.
 */
public class A implements Comparable<A>{

    public long a;

    public A(long a){
        this.a = a;
    }

    @Override
    public int compareTo(A o) {

        if(a > o.a){
            return -1;
        }else if(a == o.a){
            return 0;
        }
        return 1;
    }
}
