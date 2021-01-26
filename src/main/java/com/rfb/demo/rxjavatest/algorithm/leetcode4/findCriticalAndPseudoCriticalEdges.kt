package com.rfb.demo.rxjavatest.algorithm.leetcode4

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class findCriticalAndPseudoCriticalEdges {

    private lateinit var parent:IntArray

    private fun getLevelAndAns(s:Int):Pair<Int, Int>{

        var cur = s
        var level = 0
        while (parent[cur] != -1){
            cur = parent[cur]
            level++
        }

        return Pair(level, cur)
    }

    private fun union(u:Int, v:Int):Boolean{

        val pairU = getLevelAndAns(u)
        val pairV = getLevelAndAns(v)

        if(pairU.second == pairV.second){
            return false
        }

        if(pairU.first < pairV.first){
            parent[pairU.second] = pairV.second
        }else{
            parent[pairV.second] = pairU.second
        }
        return true
    }

    private fun connect(n:Int, edges: Array<IntArray>, skip:Int, min:Int, preAdd:Int=-1):Boolean{

        parent = IntArray(n){-1}

        var check = 0
        var count = 0
        if(preAdd != -1){
            union(edges[preAdd][0], edges[preAdd][1])
            count++
            check += edges[preAdd][2]
        }

        for(i in edges.indices){

            if(i == skip){
                continue
            }

            if(union(edges[i][0], edges[i][1])){
                check += edges[i][2]
                count++
            }

            if(check > min){
                break
            }

            if(count == n-1){
                break
            }
        }

        if(count != n-1 || check > min){
            return false
        }

        return true
    }

    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {

        val edgeMap = Array(n){IntArray(n)}

        edges.forEachIndexed { index, it ->
            edgeMap[it[0]][it[1]] = index
            edgeMap[it[1]][it[0]] = index
        }

        edges.sortWith(Comparator { o1, o2 ->
            when{
                o1[2] < o2[2] -> -1
                o1[2] > o2[2] -> 1
                else -> 0
            }
        })

        parent = IntArray(n){-1}
        val gone = BooleanArray(edges.size)

        var count = 0
        var len = 0
        for(i in edges.indices){

            if(union(edges[i][0], edges[i][1])){
                count++
                gone[i] = true
                len += edges[i][2]
            }

            if(count == n-1){
                break
            }
        }

        val keys = ArrayList<Int>()
        val unkeys = ArrayList<Int>()

        for(I in edges.indices){
            if(!connect(n, edges, I, len)){
                keys.add(edgeMap[edges[I][0]][edges[I][1]])
            }else{
                if(connect(n, edges, I, len, I)){
                    unkeys.add(edgeMap[edges[I][0]][edges[I][1]])
                }
            }
        }

        val result = ArrayList<List<Int>>()
        result.add(keys)
        result.add(unkeys)
        return result
    }

}