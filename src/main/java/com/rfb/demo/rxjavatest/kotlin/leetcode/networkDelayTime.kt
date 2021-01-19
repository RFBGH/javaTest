package com.rfb.demo.rxjavatest.kotlin.leetcode

class networkDelayTime {

    fun networkDelayTime(times: Array<IntArray>, n: Int, s: Int): Int {

        val G = Array(n+1){IntArray(n+1){1000000} }

        for(i in 1 .. n){
            G[i][i] = 0
        }

        times.forEach {
            G[it[0]][it[1]] = it[2]
        }

        for(k in 1 .. n){
            for(i in 1 .. n){
                for(j in 1 .. n){
                    if(G[i][j] > G[i][k]+G[k][j]){
                        G[i][j] = G[i][k]+G[k][j]
                    }
                }
            }
        }

        var max = 0
        for(k in 1 .. n){
            if(G[s][k] > max){
                max = G[s][k]
            }
        }

        if(max == 1000000){
            return -1
        }

        return max
    }

}