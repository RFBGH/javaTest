package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class RandomizedSet {
    private Random random = new Random(System.currentTimeMillis());
    private List<Integer> list = new ArrayList<>();
    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(list.contains(val)){
            return false;
        }

        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(list.contains(val)){
            list.remove((Integer) val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {

        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
