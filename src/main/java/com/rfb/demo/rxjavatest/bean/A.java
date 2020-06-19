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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a1 = (A) o;

        if (a != a1.a) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (a ^ (a >>> 32));
    }

    public Runnable testRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("11111");
        }
    };
}
