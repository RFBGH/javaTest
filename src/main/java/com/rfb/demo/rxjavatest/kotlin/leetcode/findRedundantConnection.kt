package com.rfb.demo.rxjavatest.kotlin.leetcode

class findRedundantConnection {

    private lateinit var parent:IntArray
    private fun getLevelAndParent(v:Int) : Pair<Int,Int>{

        var level = 0
        var cur = v
        while(parent[cur] != -1){
            cur = parent[cur]
            level++
        }
        return Pair(level, cur)
    }


    fun findRedundantConnection(edges: Array<IntArray>): IntArray {

        parent = IntArray(edges.size+1){-1}
        lateinit var target:IntArray

        for(edge in edges){

            val pairV = getLevelAndParent(edge[0])
            val pairU = getLevelAndParent(edge[1])

            if(pairU.second == pairV.second){
                target = edge
                continue
            }

            if(pairV.first > pairU.first){
                parent[pairU.second] = pairV.second
            }else{
                parent[pairV.second] = pairU.second
            }
        }
        return target
    }
}