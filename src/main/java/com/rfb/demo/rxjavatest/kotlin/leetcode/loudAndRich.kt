package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList

class loudAndRich {


    fun loudAndRich(richer: Array<IntArray>, quiet: IntArray): IntArray {

        val dep = IntArray(quiet.size)
        val G = Array(quiet.size){ArrayList<Int>()}
        for(edge in richer){
            G[edge[0]].add(edge[1])
            dep[edge[1]]++
        }

        val ans = IntArray(quiet.size){it}

        val queue: Queue<Int> = LinkedList<Int>()
        for(i in dep.indices){

            if(dep[i] != 0){
                continue
            }

            queue.offer(i)
        }

        while (!queue.isEmpty()){

            val cur = queue.poll()
            for(next in G[cur]){

                if(quiet[ans[cur]] < quiet[ans[next]]){
                    ans[next] = ans[cur]
                }

                dep[next]--
                if(dep[next] == 0){
                    queue.offer(next)
                }
            }
        }

        return ans
    }

    fun test(){
        val richer = arrayOf(intArrayOf(1,0), intArrayOf(2,1),
                intArrayOf(3,1), intArrayOf(3,7), intArrayOf(4,3), intArrayOf(5,3), intArrayOf(6, 3))
        loudAndRich(richer, intArrayOf(3,2,5,4,6,1,7,0))
    }
}