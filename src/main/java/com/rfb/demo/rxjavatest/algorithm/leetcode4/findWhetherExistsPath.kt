package com.rfb.demo.rxjavatest.algorithm.leetcode4

import java.util.*
import kotlin.collections.ArrayList

class findWhetherExistsPath {

    fun findWhetherExistsPath(n: Int, graph: Array<IntArray>, start: Int, target: Int): Boolean {

        val G = Array(n){ArrayList<Int>()}
        graph.forEach {
            G[it[0]].add(it[1])
        }

        val queue = LinkedList<Int>()
        queue.add(start)

        val gone = BooleanArray(n)
        gone[start] = true
        while (!queue.isEmpty()){
            val cur = queue.poll()
            for(next in G[cur]){

                if(gone[next]){
                    continue
                }
                gone[next] = true
                queue.offer(next)
                if(next == target){
                    return true
                }
            }
        }

        return false
    }

}