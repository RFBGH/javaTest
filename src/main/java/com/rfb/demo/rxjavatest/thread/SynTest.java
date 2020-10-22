package com.rfb.demo.rxjavatest.thread;

public class SynTest {

    private static class Test{

        public int test = 100;
    }

    public static void test(){

        final Test test = new Test();

        new Thread(){
            @Override
            public void run() {

                synchronized (test){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.test = 20;
                    System.out.println(test.test);
                }

            }
        }.start();

        System.out.println(test.test);
        test.test = 1;
        System.out.println(test.test);
    }

}
