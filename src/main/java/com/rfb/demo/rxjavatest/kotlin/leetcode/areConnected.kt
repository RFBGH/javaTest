package com.rfb.demo.rxjavatest.kotlin.leetcode

class areConnected {


    private lateinit var parents:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var cur = u
        var level = 0
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int){

        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if (pairU.second == pairV.second) {
            return
        }

        if(pairV.first> pairU.first){
            parents[pairU.second] = pairV.second
        }else{
            parents[pairV.second] = pairU.second
        }
    }

    fun areConnected(n: Int, threshold: Int, queries: Array<IntArray>): List<Boolean> {

        val ans = ArrayList<Boolean>()
        if(threshold == 0){
            for(i in queries.indices){
                ans.add(true)
            }
            return ans
        }

        parents = IntArray(n+1){-1}

        for(i in threshold+1..n/2){
            var j = 2
            while(j * i <= n){
                union(i, j*i)
                j++
            }
        }

        for(query in queries){
            val pairU = getLevelAndParent(query[0])
            val pairV = getLevelAndParent(query[1])
            ans.add(pairU.second == pairV.second)
        }

        return ans
    }

    fun test(){
        val connect = Array(3){IntArray(2)}
        connect[0][0] = 1
        connect[0][1] = 4

        connect[1][0] = 2
        connect[1][1] = 5

        connect[2][0] = 3
        connect[2][1] = 6

        areConnected(6, 2, connect)
    }
}