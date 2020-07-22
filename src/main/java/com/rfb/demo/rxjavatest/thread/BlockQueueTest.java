package com.rfb.demo.rxjavatest.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2020/7/21 0021.
 */
public class BlockQueueTest{

    public static void delay(int delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test(){

        final BlockingQueue<String> queue = new LinkedBlockingDeque<>();


        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){

                    try {
                        queue.put("thread1 "+i);
                        System.out.println("put thread1 "+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                    delay(1);

                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){

                    try {
                        queue.put("thread2 "+i);
                        System.out.println("put thread2 "+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    delay(1);

                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {

                while (true){

                    try {
                        String value = queue.take();
                        System.out.println(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    delay(100);

                }

            }
        }.start();

    }

}
