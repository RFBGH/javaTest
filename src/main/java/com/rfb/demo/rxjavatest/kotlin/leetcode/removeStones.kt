package com.rfb.demo.rxjavatest.kotlin.leetcode

class removeStones {

    private lateinit var parents:IntArray
    private lateinit var counts:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var level = 0
        var cur = u
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int){
        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if(pairU.second == pairV.second){
            return
        }

        if(pairU.first > pairV.first){
            parents[pairV.second] = pairU.second
            counts[pairU.second] += counts[pairV.second]
        }else{
            parents[pairU.second] = pairV.second
            counts[pairV.second] += counts[pairU.second]
        }
    }

    fun removeStones(stones: Array<IntArray>): Int {

        parents = IntArray(stones.size){-1}
        counts = IntArray(stones.size){1}

        for(i in stones.indices){
            for(j in i+1 until stones.size){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(i, j)
                }
            }
        }

        var ans = 0
        val set = HashSet<Int>()
        for(i in stones.indices){
            val pair = getLevelAndParent(i)
            if(set.contains(pair.second)){
                continue
            }

            if(i == pair.second){
                counts
            }

            set.add(pair.second)
            ans += counts[pair.second]-1
        }

        return ans
    }

    fun test(){
        val a = Array(2){IntArray(2)}
        a[0][0] = 0
        a[0][1] = 1

        a[1][0] = 1
        a[1][1] = 0

        println(removeStones(a))
    }

}