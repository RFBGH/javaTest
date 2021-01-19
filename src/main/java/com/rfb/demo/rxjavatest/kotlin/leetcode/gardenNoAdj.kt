package com.rfb.demo.rxjavatest.kotlin.leetcode

class gardenNoAdj {

    fun gardenNoAdj(n: Int, paths: Array<IntArray>): IntArray {

        val G = Array(n+1){IntArray(3)}

        paths.forEach {
            for(i in 0..2){
                if (G[it[0]][i] == 0) {
                    G[it[0]][i] = it[1]
                    break
                }
            }

            for(i in 0..2){
                if (G[it[1]][i] == 0) {
                    G[it[1]][i] = it[0]
                    break
                }
            }
        }

        val take = IntArray(n)
        for(i in 0 until n){

            val color = BooleanArray(5){true}
            for(k in 0..2){

                if(G[i+1][k] == 0){
                    break
                }

                color[take[G[i+1][k]-1]] = false
            }

            for(k in 1..4){
                if(color[k]){
                    take[i] = k
                    break
                }
            }
        }

        return take
    }

}