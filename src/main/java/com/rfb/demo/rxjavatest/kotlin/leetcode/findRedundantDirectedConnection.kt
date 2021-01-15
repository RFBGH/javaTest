package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class findRedundantDirectedConnection {

    private data class Node(val i:Int, val prev:Node?)

    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {

        val rG = Array(edges.size+1){ArrayList<Int>()}
        val dep = IntArray(edges.size+1){0}

        var suspicious = -1
        edges.forEach {
            rG[it[1]].add(it[0])
            dep[it[0]]++
            if(rG[it[1]].size == 2){
                suspicious = it[1]
            }
        }

        if(suspicious == -1){

            val queue = ArrayList<Int>()
            var front = 0
            dep.forEachIndexed { index, i ->
                if(i == 0){
                    queue.add(index)
                }
            }

            while (front < queue.size){

                val cur = queue[front++]
                for(next in rG[cur]){
                    dep[next]--
                    if(dep[next] == 0){
                        queue.add(next)
                    }
                }
            }

            val set = HashSet<Int>(queue)
            edges.reversedArray().forEach {
                if(!set.contains(it[0]) && !set.contains(it[1])){
                    return it
                }
            }
        }else{

            val set = HashSet<Int>()
            set.add(suspicious)

            val queue = LinkedList<Node>()
            queue.offer(Node(suspicious, null))

            var circleEndNode:Node? = null
            while (!queue.isEmpty()){
                val cur = queue.poll()

                for(next in rG[cur.i]){
                    if(!set.contains(next)){
                        queue.offer(Node(next, cur))
                    }else{
                        circleEndNode = Node(next, cur)
                        break
                    }
                }
                if(circleEndNode != null){
                    break
                }
            }

            if(circleEndNode == null){
                return intArrayOf(rG[suspicious].last(), suspicious)
            }else{

                val circleNode = ArrayList<Int>()
                var cur = circleEndNode
                while (cur != null){
                    circleNode.add(cur.i)
                    cur = cur.prev
                }

                return intArrayOf(circleNode[circleNode.size-2], suspicious)
            }
        }

        return IntArray(2){0}
    }

}