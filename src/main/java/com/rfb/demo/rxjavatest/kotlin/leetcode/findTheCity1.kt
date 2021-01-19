package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class findTheCity1 {

    fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {

        val ans = Array(n){IntArray(2)}

        val G = Array(n){IntArray(n){1000000} }
        edges.forEach {
            G[it[0]][it[1]] = it[2]
            G[it[1]][it[0]] = it[2]
        }

        for(i in 0 until n){
            G[i][i] = 0
        }

        for(k in 0 until n){
            for(i in 0 until n){
                for(j in 0 until n){
                    if(G[i][j] > G[i][k]+G[k][j]){
                        G[i][j] = G[i][k]+G[k][j]
                    }
                }
            }
        }

        for(i in 0 until n){
            ans[i][0] = i

            var sum = 0
            for(j in 0 until n){
                if(G[i][j] <= distanceThreshold){
                    sum++
                }
            }
            ans[i][1] = sum
        }

        ans.sortWith(Comparator { o1, o2 ->
                    when{
                        o1[1] < o2[1] -> -1
                        o1[1] > o2[1] -> 1
                        else -> when{
                            o1[0] > o2[0] -> -1
                            o1[0] < o2[0] -> 1
                            else -> 0
                        }
                    }
                })

        return ans[0][0]
    }

}