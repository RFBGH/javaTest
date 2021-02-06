package com.rfb.demo.rxjavatest.algorithm.leetcode4

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class checkIfPrerequisite {

    fun checkIfPrerequisite(n: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): BooleanArray {

        val G = Array(n){ArrayList<Int>()}
        prerequisites.forEach {
            G[it[0]].add(it[1])
        }

        val ans = BooleanArray(queries.size)

        queries.forEachIndexed { index, ints ->

            val gone = BooleanArray(n)
            var find = false
            val queue = LinkedList<Int>()
            queue.add(ints[0])
            gone[ints[0]] = true
            while (!queue.isEmpty()){
                val cur = queue.poll()
                for(next in G[cur]){

                    if(gone[next]){
                        continue
                    }
                    gone[next] = true
                    queue.offer(next)
                    if(next == ints[1]){
                        find = true
                        break
                    }
                }
                if(find){
                    break
                }
            }

            ans[index] = find
        }

        return ans
    }

}