package com.rfb.demo.rxjavatest.kotlin.leetcode

import kotlin.math.abs

class minCostConnectPointsE {

    fun minCostConnectPoints(points: Array<IntArray>): Int {

        val G = Array(points.size){IntArray(points.size)}
        for(i in points.indices){
            for(j in i+1 until points.size){

                val distance = abs(points[i][0]-points[j][0]) + abs(points[i][1]-points[j][1])
                G[i][j] = distance
                G[j][i] = distance
            }
        }

        val sures = BooleanArray(points.size)
        val dists = IntArray(points.size){Int.MAX_VALUE}
        val parent = IntArray(points.size){-1}

        dists[0] = 0
        for(I in points.indices){

            var min = Int.MAX_VALUE
            var index = 0

            for(i in dists.indices){
                if(!sures[i] && dists[i] < min){
                    min = dists[i]
                    index = i
                }
            }

            if(min == Int.MAX_VALUE){
                break
            }

            sures[index] = true
            for(i in points.indices){
                if(sures[i]){
                    continue
                }

                if(dists[i] >= dists[index]+G[index][i]){
                    parent[i] = index
                    dists[i] = dists[index]+G[index][i]
                }
            }
        }

        var ans = 0
        val hasCount = BooleanArray(points.size)
        for(i in points.indices){

            if(hasCount[i]){
                continue
            }

            var cur = i
            while (true){
                hasCount[cur] = true
                val last = parent[cur]
                if(last == -1){
                    break
                }

                ans += abs(points[cur][0]-points[last][0]) + abs(points[cur][1]-points[last][1])
                cur = last

                if(hasCount[cur]){
                    break
                }
            }
        }

        return ans
    }

    fun test(){
        val points = Array(5){ IntArray(2) }
        points[0][0] = 0
        points[0][1] = 0

        points[1][0] = 2
        points[1][1] = 2

        points[2][0] = 3
        points[2][1] = 10

        points[3][0] = 5
        points[3][1] = 2

        points[4][0] = 7
        points[4][1] = 0

        println(minCostConnectPoints(points))
    }
}