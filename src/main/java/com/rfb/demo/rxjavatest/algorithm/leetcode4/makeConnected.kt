package com.rfb.demo.rxjavatest.algorithm.leetcode4

class makeConnected {


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

    fun makeConnected(n: Int, connections: Array<IntArray>): Int {

        parent = IntArray(n){-1}

        var rest = 0
        connections.forEach {
            if(!union(it[0], it[1])){
                rest++
            }
        }

        val set = HashSet<Int>()
        for(i in 0 until n){
            val pair = getLevelAndAns(i)
            set.add(pair.second)
        }

        if(set.size-1 <= rest){
            return set.size-1
        }

        return -1
    }

}