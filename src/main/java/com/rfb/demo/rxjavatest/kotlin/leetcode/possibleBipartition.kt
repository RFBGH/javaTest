package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList

class possibleBipartition {

    fun possibleBipartition(N: Int, dislikes: Array<IntArray>): Boolean {

        val G = Array(N+1){ArrayList<Int>()}

        dislikes.forEach {
            G[it[0]].add(it[1])
            G[it[1]].add(it[0])
        }

        val color = IntArray(N+1){-1}
        color.forEachIndexed { index, i ->
            if(i == -1){

                color[index] = 0
                val queue = LinkedList<Int>()
                queue.offer(index)
                while (!queue.isEmpty()){
                    val cur = queue.poll()
                    G[cur].forEach {
                        if(color[it] == -1){
                            color[it] = (color[cur]+1)%2
                            queue.offer(it)
                        }else{
                            if(color[it] == color[cur]){
                                return false
                            }
                        }
                    }
                }
            }
        }

        return true
    }
}