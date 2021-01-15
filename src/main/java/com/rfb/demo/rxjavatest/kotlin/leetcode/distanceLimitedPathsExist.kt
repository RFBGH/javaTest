package com.rfb.demo.rxjavatest.kotlin.leetcode

class distanceLimitedPathsExist {

    private lateinit var parents:IntArray
    private lateinit var minEdges:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var level = 0
        var cur = u
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int, dist:Int){
        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if(pairU.second == pairV.second){
            return
        }

        if(pairU.first > pairV.first){
            parents[pairV.second] = pairU.second
            minEdges[pairV.second] = dist
        }else{
            parents[pairU.second] = pairV.second
            minEdges[pairU.second] = dist
        }
    }

    fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {

        edgeList.sortWith(Comparator { o1, o2 ->
            when{
                o1[2] < o2[2] -> -1
                o1[2] > o2[2] -> 1
                else -> 0
            }
        })

        parents = IntArray(n){-1}
        minEdges = IntArray(n){0}

        for(edge in edgeList){
            union(edge[0], edge[1], edge[2])
        }

        val ans = BooleanArray(queries.size)
        for(i in queries.indices){

            val query = queries[i]
            val hold = query[2]

            var v = query[0]
            var u = query[1]

            val pairV = getLevelAndParent(v)
            val pairU = getLevelAndParent(u)

            if(pairV.second != pairU.second){
                ans[i] = false
                continue
            }

            var maxV = 0
            var curV = v
            var maxU = 0
            var curU = u
            if(pairV.first > pairU.first){

                for(j in 0 until pairV.first-pairU.first){
                    if(minEdges[curV] > maxV){
                        maxV = minEdges[curV]
                    }
                    curV = parents[curV]
                }

            }else if(pairV.first < pairU.first){

                for(j in 0 until pairU.first-pairV.first){
                    if(minEdges[curU] > maxU){
                        maxU = minEdges[curU]
                    }
                    curU = parents[curU]
                }
            }

            while (curU != curV){

                if(minEdges[curV] > maxV){
                    maxV = minEdges[curV]
                }

                if(minEdges[curU] > maxU){
                    maxU = minEdges[curU]
                }

                curU = parents[curU]
                curV = parents[curV]
            }

            var max = maxV
            if(max < maxU){
                max = maxU
            }

            ans[i] = max < hold
        }

        return ans
    }

    fun test(){

        distanceLimitedPathsExist(13, arrayOf(intArrayOf(9,1,53), intArrayOf(3,2,66), intArrayOf(12,5,99),intArrayOf(9,7,26)),
                arrayOf(intArrayOf(9,7,65), intArrayOf(9,6,1)))

    }

}
