package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class minNumberOfSemestersE {

    private data class Node(val i:Int, val deep:Int)

    private fun getDeep(G:Array<ArrayList<Int>>, s:Int):Int{

        var deep = 0
        val queue = LinkedList<Node>()
        queue.offer(Node(s, 0))
        while (!queue.isEmpty()){
            val cur = queue.poll()
            deep = cur.deep
            G[cur.i].forEach {
                queue.offer(Node(it, cur.deep+1))
            }
        }

        return deep
    }

    fun minNumberOfSemesters(n: Int, dependencies: Array<IntArray>, k: Int): Int {

        val G = Array(n+1){
            ArrayList<Int>()
        }

        val dep = IntArray(n+1)
        dependencies.forEach {
            G[it[0]].add(it[1])
            dep[it[1]]++
        }

        var ans = 0
        while (true){

            val task = ArrayList<Int>()
            for(i in 1..n){
                if(dep[i] == 0){
                    task.add(i)
                }
            }

            if(task.isEmpty()){
                break
            }

            task.sortWith(Comparator { o1, o2 ->
                val deep1 = getDeep(G, o1)
                val deep2 = getDeep(G, o2)
                when {
                    deep1 > deep2 -> -1
                    deep1 < deep2 -> 1
                    else -> {
                        when{
                            G[o1].size > G[o2].size -> -1
                            G[o1].size < G[o2].size -> 1
                            else -> 0
                        }
                    }}
            })

            ans ++
            for(i in task.indices){
                if(i >= k){
                    break
                }
                val t = task[i]
                dep[t] = -1
                G[t].forEach {
                    dep[it]--
                }
            }
        }

        return ans
    }

}