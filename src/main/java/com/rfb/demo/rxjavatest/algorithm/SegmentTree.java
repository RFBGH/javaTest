package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/13 0013.
 */
public class SegmentTree{

    private List<Integer> mData = null;
    private int mIndexCount = 1;

    public int getIndexCount() {
        return mIndexCount;
    }

    public void init(int _n){

        int mAllCount = 1;
        mIndexCount = 1;

        while(mIndexCount < _n){
            mIndexCount *= 2;
            mAllCount += mIndexCount;
        }

        mData = new ArrayList<>(mAllCount);
        for(int i = 0; i < mAllCount; i++){
            mData.add(0);
        }
    }

    public void update(int index, int value){

        int realIndex = index + mIndexCount - 1;

        mData.set(realIndex, value);

        int temp = (realIndex - 1) / 2;
        while(true){
            int left = temp * 2 + 1;
            int right = temp *2 + 2;
            mData.set(temp, mData.get(left)+mData.get(right));

            temp --;
            if(temp < 0){
                break;
            }
            temp /= 2;
        }
    }

    public int query(int start, int end, int k, int left, int right){

        if(left <= start && right >= end){
            return mData.get(k);
        }else if(right < start || left > end){
            return 0;
        }else{
            int mid = (start+end) / 2;
            int sumLeft = query(start, mid, k*2+1, left, right);
            int sumRight = query(mid+1, end, k*2+2, left, right);
            return sumLeft + sumRight;
        }
    }

    private void printEmtpy(int n){
        while(n > 0){
            System.out.print(" ");
            n--;
        }
    }

    public void print(){

        System.out.println("indexCount "+mIndexCount);
        int n = mIndexCount - 1;

        int index = 1;
        int sum = 0;
        while(index <= mIndexCount){


            int start = sum;
            for(int i = 0; i < index; i++){
                printEmtpy(n);
                System.out.print(mData.get(start+i));
                printEmtpy(n);
                printEmtpy(1);
            }

            System.out.println();

            sum += index;
            index *= 2;

            n = (n-1)/2;
        }
    }

    public static void test(){

        SegmentTree segmentTree = new SegmentTree();
        int n = 100;
        segmentTree.init(n);

        for(int i = 0; i < n; i++){
            segmentTree.update(i, i+1);
        }

        segmentTree.print();

        int ans = segmentTree.query(0, segmentTree.getIndexCount()-1, 0, 13, 78);
        System.out.println(ans);

//        ans = segmentTree.query(0, n, 0, 1, 5);
//        System.out.println(ans);
//
//        ans = segmentTree.query(0, n, 0, 2, 3);
//        System.out.println(ans);
    }
}
