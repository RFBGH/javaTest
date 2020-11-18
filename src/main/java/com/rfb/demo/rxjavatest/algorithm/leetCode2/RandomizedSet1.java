package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomizedSet1 {
    private Random random = new Random(System.currentTimeMillis());
    private Set<Integer> set = new HashSet<>();
    /** Initialize your data structure here. */
    public RandomizedSet1() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(set.contains(val)){
            return false;
        }

        set.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        return set.remove(val);
    }

    /** Get a random element from the set. */
    public int getRandom() {

        int index = random.nextInt(set.size());
        for(Integer i : set){
            if(index == 0){
                return i;
            }
            index--;
        }
        return -1;
    }
}
