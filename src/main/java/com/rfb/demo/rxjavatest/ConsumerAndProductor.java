package com.rfb.demo.rxjavatest;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsumerAndProductor {


    private LinkedBlockingQueue<String> list = new LinkedBlockingQueue<>();

    public class Consumer{


        public void consume(){

            while(true){
                String data = null;
                try {
                    data = list.take();
                    System.out.println(Thread.currentThread().getName()+" consume "+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        public void startConsume(){
            new Thread(){

                @Override
                public void run(){
                    consume();
                }

            }.start();
        }

    }

    public class Productor{

        public void produce(){

            for(int i = 0; i < 10; i++){
                try {
                    list.put(i+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" produce "+i);
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        public void startProduce(){

            new Thread(){

                @Override
                public void run(){
                    produce();
                }

            }.start();
        }
    }

    public void test(){

        Productor productor = new Productor();
        Consumer consumer = new Consumer();

        consumer.startConsume();
        productor.startProduce();

    }

    void setParted1(int[] a,int left,int right){
        if(left>=right||left==a.length||right==0){
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]);
            }
            return ;
        }
        while(a[left]<0){
            left++;
        }
        while(a[right]>=0){
            right--;
        }
        if(left>=right||left==a.length||right==0){
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]);
            }
            return;
        }
        swap(a,left,right);
        left++;
        right--;
        setParted1(a,left,right);
    }
    private void swap(int a[],int left,int right){
        int temp=0;
        temp=a[left];
        a[left]=a[right];
        a[right]=temp;
    }

    //{10, -2, 5, 8, -4, 2, -3, 7, 12, -88, -23, 35}变化后是{-2, -4，-3, -88, -23,5, 8 ,10, 2, 7, 12, 35}
    public void test(int[] a){

        int n = a.length;
        int offset = -1;
        for(int i = 0; i < n; i++){

            if(a[i] < 0){
                continue;
            }

            if(offset == -1){
                offset = i+1;
            }

            for(; offset < n; offset++){
                if(a[offset] < 0){
                    break;
                }
            }

            if(offset == n){
                break;
            }

            int t = a[i];
            a[i] = a[offset];
            a[offset] = t;
        }
    }
}
