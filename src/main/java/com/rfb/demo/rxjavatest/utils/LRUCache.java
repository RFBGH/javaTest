package com.rfb.demo.rxjavatest.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LRUCache {

    private int mCacheSize = 0;

    private Map<Integer, Integer> mValue = new HashMap<>();
    private Map<Integer, Integer> mUseCount = new HashMap<>();

    public LRUCache(int mCacheSize) {
        this.mCacheSize = mCacheSize;
    }

    public void put(int key, int value){
        mValue.put(key, value);
        mUseCount.put(key, 0);

        if(mCacheSize == 0){
            return;
        }

        if(mValue.size() < mCacheSize){
            return;
        }

        Set<Integer>keys = mUseCount.keySet();
        int min = Integer.MAX_VALUE;
        int targetKey = -1;
        for(Integer temp : keys){
            int count = mUseCount.get(temp);
            if(count < min){
                min = count;
                targetKey = temp;
            }
        }

        mValue.remove(targetKey);
        mUseCount.remove(targetKey);
    }

    public int get(int key){
        if(mValue.containsKey(key)){
            int count = mUseCount.get(key);
            mUseCount.put(key, count);
            return mValue.get(key);
        }
        return -1;
    }
}
