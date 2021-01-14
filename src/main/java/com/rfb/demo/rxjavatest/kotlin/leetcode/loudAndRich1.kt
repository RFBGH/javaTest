package com.rfb.demo.rxjavatest.kotlin.leetcode

class loudAndRich1 {


    fun loudAndRich(richer: Array<IntArray>, quiet: IntArray): IntArray {

        val G = Array(quiet.size){ArrayList<Int>()}
        for(edge in richer){
            G[edge[1]].add(edge[0])
        }

        val ans = IntArray(quiet.size){it}

        for(i in ans.indices){

            val gone = BooleanArray(quiet.size)
            gone[i] = true
            val queue = ArrayList<Int>()
            var front = 0

            queue.add(i)

            var min = quiet[i]
            var index = i

            while (front < queue.size){

                val cur = queue[front]

                for(next in G[cur]){

                    if(gone[next]){
                        continue
                    }
                    gone[next] = true
                    queue.add(next)

                    if(quiet[next] < min){
                        min = quiet[next]
                        index = next
                    }
                }
                front++
            }

            ans[i] = index
        }

        return ans
    }
}