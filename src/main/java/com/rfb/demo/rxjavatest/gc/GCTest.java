package com.rfb.demo.rxjavatest.gc;

import com.rfb.demo.rxjavatest.bean.A;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class GCTest {


    public static void test(){

        A a = new A(111);

        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(a, queue);
        a = null;

        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(phantomReference.get() == null){
            System.out.println("recyle");
        }else{
            System.out.println("no");
        }
        System.out.println(queue.toString());
    }

}
