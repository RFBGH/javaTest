package com.rfb.demo.rxjavatest.asm_inner;

public class Test {

    public void test(){
        System.out.println("test");
    }

    public void test1(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1");
    }

    public void test2(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2");
    }

    public void test3(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test3");
    }
}
