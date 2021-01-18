package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.HashSet

class maxCandies {

    fun maxCandies(status: IntArray, candies: IntArray, keys: Array<IntArray>, containedBoxes: Array<IntArray>, initialBoxes: IntArray): Int {

        val queue = LinkedList<Int>()
        val unOpenBoxes = HashSet<Int>()

        initialBoxes
                .forEach {
                    keys[it].forEach {
                        k->
                        status[k] = 1
                    }
                }

        initialBoxes
                .forEach {
                    if(status[it] == 1){
                        queue.offer(it)
                    }else{
                        unOpenBoxes.add(it)
                    }
                }

        var sum = 0
        while (!queue.isEmpty()){

            val cur = queue.poll()
            sum += candies[cur]

            containedBoxes[cur].forEach {
                keys[it].forEach {
                    k->
                    status[k] = 1
                    if(unOpenBoxes.contains(k)){
                        queue.offer(k)
                        unOpenBoxes.remove(k)
                    }
                }
            }

            containedBoxes[cur].forEach {
                if(status[it] == 1){
                    queue.offer(it)
                }else{
                    unOpenBoxes.add(it)
                }
            }
        }

        return sum
    }


}