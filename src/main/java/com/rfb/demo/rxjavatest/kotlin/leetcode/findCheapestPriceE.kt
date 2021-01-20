package com.rfb.demo.rxjavatest.kotlin.leetcode

class findCheapestPriceE{

    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {

        val G = Array(n){ArrayList<IntArray>()}
        val parent = IntArray(n){-1}

        flights.forEach {
            G[it[0]].add(intArrayOf(it[1], it[2]))
        }

        val dist = IntArray(n){Int.MAX_VALUE}
        dist[src] = 0
        val sure = BooleanArray(n){false}

        for(k in 0 until n){

            var min = Int.MAX_VALUE
            var target = -1
            dist.forEachIndexed { index, i ->
                if(!sure[index] && min > i){
                    min = i
                    target = index
                }
            }

            if(target == -1){
                break
            }
            sure[target] = true

            var count = 0
            var cur = target
            while (cur != -1){
                cur = parent[cur]
                count++
            }

            if(count > K+1){
                continue
            }

            G[target].forEach {
                if(!sure[it[0]] && dist[it[0]] > dist[target]+it[1]){
                    dist[it[0]] = dist[target]+it[1]
                    parent[it[0]] = target
                }
            }
        }

        if(dist[dst] == Int.MAX_VALUE){
            return -1
        }

        return dist[dst]
    }

}