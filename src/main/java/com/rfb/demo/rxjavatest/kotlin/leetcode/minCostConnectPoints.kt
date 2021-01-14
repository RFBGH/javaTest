package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.awt.Point
import kotlin.math.abs

class minCostConnectPoints {

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

    private fun union(v:Int, u:Int):Boolean{

        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if (pairU.second == pairV.second) {
            return false
        }

        if(pairV.first> pairU.first){
            parents[pairU.second] = pairV.second
        }else{
            parents[pairV.second] = pairU.second
        }
        return true
    }

    data class Node(val i:Int, val j:Int, val dist:Int)

    fun minCostConnectPoints(points: Array<IntArray>): Int {

        parents = IntArray(points.size){-1}

        val nodes = ArrayList<Node>(points.size*points.size/2)

        val G = Array(points.size){IntArray(points.size)}
        for(i in points.indices){
            for(j in i+1 until points.size){
                val distance = abs(points[i][0]-points[j][0]) + abs(points[i][1]-points[j][1])
                G[i][j] = distance
                G[j][i] = distance
                nodes.add(Node(i, j, distance))
            }
        }

        nodes.sortWith(Comparator { o1, o2 ->
            when{
                o1.dist < o2.dist -> -1
                o1.dist > o2.dist -> 1
                else -> 0
            }
        })

        var ans = 0
        for(node in nodes){
            if (union(node.i, node.j)) {
                ans += node.dist
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