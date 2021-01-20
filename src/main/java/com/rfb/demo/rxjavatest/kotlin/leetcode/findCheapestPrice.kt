package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList

class findCheapestPrice{

    data class Node(val i:Int, val dist:Int, val step:Int)

    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {

        val G = Array(n){ArrayList<IntArray>()}

        flights.forEach {
            G[it[0]].add(intArrayOf(it[1], it[2]))
        }

        val queue = LinkedList<Node>()
        queue.offer(Node(src, 0, 0))

        var ans = Int.MAX_VALUE

        val dist = IntArray(n){Int.MAX_VALUE}
        dist[src] = 0

        while (!queue.isEmpty()){
            val cur = queue.poll()
            if(cur.i == dst && cur.dist < ans){
                ans = cur.dist
            }

            if(cur.step > K){
                continue
            }

            G[cur.i].forEach {
                if(cur.dist+it[1] < dist[it[0]]){
                    dist[it[0]] = cur.dist+it[1]
                    queue.offer(Node(it[0], cur.dist+it[1], cur.step+1))
                }
            }
        }

        if(ans == Int.MAX_VALUE){
            return -1
        }
        return ans
    }

}