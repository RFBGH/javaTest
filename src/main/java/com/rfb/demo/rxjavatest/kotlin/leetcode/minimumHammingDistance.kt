package com.rfb.demo.rxjavatest.kotlin.leetcode

class minimumHammingDistance {

    private lateinit var parents:IntArray

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
        }else{
            parents[pairU.second] = pairV.second
        }
    }

    fun minimumHammingDistance(source: IntArray, target: IntArray, allowedSwaps: Array<IntArray>): Int {

        parents = IntArray(source.size){-1}

        for(allowedSwap in allowedSwaps){
            union(allowedSwap[0], allowedSwap[1])
        }

        val groups = Array(source.size){HashSet<Int>()}
        for(i in source.indices){
            val pair = getLevelAndParent(i)
            groups[pair.second].add(i)
        }

        var ans = 0
        for(group in groups){
            if(group.isEmpty()){
                continue
            }

            val map = HashMap<Int,Int>()
            for(i in group){
                val count = map[source[i]]?:0
                map[source[i]] = count+1
            }

            for(i in group){

                var count = map[target[i]]
                if(count == null){
                    ans++
                }else{
                    count--
                    if(count == 0){
                       map.remove(target[i])
                    }else{
                        map[target[i]] = count
                    }
                }
            }
        }

        return ans
    }

}