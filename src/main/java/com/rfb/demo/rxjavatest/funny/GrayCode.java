package com.rfb.demo.rxjavatest.funny;

import com.rfb.demo.rxjavatest.PrintUtil;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
public class GrayCode {

    private int mCode[];
    private int mCount;

    public GrayCode(int count){
        mCount = count;
        mCode = new int[count];
        for(int i = 0; i < mCount; i++){
            mCode[i] = 0;
        }
    }

    private void produce(int start){



        for(int i = 0; i < mCount; i++){
            System.out.print(mCode[i]);
        }
        System.out.println();

        if(start == mCount){
            return;
        }

        for(int i = start; i < mCount; i++){
            mCode[start] = 1;
            produce(i+1);
            mCode[start] = 0;
        }
    }

    public void start(){

        produce(0);
    }
}
