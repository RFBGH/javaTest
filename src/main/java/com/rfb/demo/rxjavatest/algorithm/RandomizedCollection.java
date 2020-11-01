package com.rfb.demo.rxjavatest.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {

    private Map<Integer, Integer> map = new HashMap<>();
    private int allCount = 0;
    private Random random = new Random(System.currentTimeMillis());

    /** Initialize your data structure here. */
    public RandomizedCollection() {

    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {

        Integer count = map.get(val);
        if(count == null){
            count = 1;
        }else{
            count++;
        }
        map.put(val, count);
        allCount++;
        return count == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Integer count = map.get(val);
        if(count == null){
            return false;
        }

        count--;
        if(count == 0){
            map.remove(val);
        }else{
            map.put(val, count);
        }

        allCount--;
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {

        int rd = random.nextInt(allCount)+1;
        Set<Integer> keys = map.keySet();
        for(Integer key : keys){
            Integer value = map.get(key);
            if(rd <= value){
                return key;
            }
            rd -= value;
        }

        return 0;
    }
}
