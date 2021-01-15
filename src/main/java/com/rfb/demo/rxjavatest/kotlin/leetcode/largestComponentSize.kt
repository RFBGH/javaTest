package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class largestComponentSize {

    private lateinit var parents:IntArray
    private lateinit var counts:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var cur = u
        var level = 0
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int){

        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if (pairU.second == pairV.second) {
            return
        }

        if(pairV.first> pairU.first){
            parents[pairU.second] = pairV.second
            counts[pairV.second] += counts[pairU.second]
        }else{
            parents[pairV.second] = pairU.second
            counts[pairU.second] += counts[pairV.second]
        }
    }

    fun largestComponentSize(A: IntArray): Int {

        val isPrimary = BooleanArray(100001){true}
        for(i in 2..316){
            for(j in 2..100000/i){
                isPrimary[i*j] = false
            }
        }

        val primarys = ArrayList<Int>()
        for(i in 2..50000){
            if(!isPrimary[i]){
                continue
            }
            primarys.add(i)
        }

        parents = IntArray(A.size){-1}
        counts = IntArray(A.size){1}

        for(i in primarys){

            var last = -1
            for(j in A.indices){

                if(A[j] % i != 0){
                    continue
                }

                if(last != -1){
                    union(last, j)
                }
                last = j
            }
        }

        var max = 0
        val checks = BooleanArray(A.size)
        for(i in A.indices){

            if(checks[i]){
                continue
            }

            var cur = i
            while (parents[cur] != -1){
                checks[cur] = true
                cur = parents[cur]
            }

            if(counts[cur] > max){
                max = counts[cur]
            }
        }

        return max
    }

    fun check():List<Int>{

        val A = intArrayOf(67331,71,29711,23737,89563,18295,49081,18329,19983,75843,87311,58507,83341,65027,27191,52663,54709,73673,98619,23949,2990,43167,31184,79108,41243,16871,93321,33767,79983,71569,38733,51159,3187,68146,49844,28581,49645,71707,2497,72307,71926,4600,10321,16413,63509,17483,25309,35969,75553,98089,5887,80343,92399,47979,26293,95731,84281,80329,21898,93251,82046,98361,11447,87904,27029,61507,12003,33871,7969,97367,94387,50647,71057,31777,3803,88661,26251,31991,94607,90830,99379,63032,42649,63957,92813,21341,91433,78777,45172,85678,48926,48219,90407,65393,88520,49429,85874,59347,95637,3033,80317,52957,57233,29597,80833,69997,44699,59557,407,52379,29342,39262,7873,38317,61057,29665,71069,6561,16057,82750,67111,20773,54083,77199,8783,73299,99208,50948,85781,96329,63599,14187,39137,75077,50570,3373,42004,34277,86297,74541,53269,31543,76283,75671)
        val queue = ArrayList<Int>()
        val gone = BooleanArray(A.size)
        gone[136] = true

        queue.add(42004)

        var front = 0
        while (front < queue.size){

            var cur = queue[front]

            for(index in A.indices){

                if(gone[index]){
                    continue
                }

                val a = A[index]
                for(i in 2..508){
                    if(a % i == 0 && cur % i == 0){
                        gone[index] = true
                        queue.add(a)
                        break
                    }
                }
            }

            front++
        }

        println("count:${queue.size}")
        return queue
    }

    fun test(){

        val A = intArrayOf(67331,71,29711,23737,89563,18295,49081,18329,19983,75843,87311,58507,83341,65027,27191,52663,54709,73673,98619,23949,2990,43167,31184,79108,41243,16871,93321,33767,79983,71569,38733,51159,3187,68146,49844,28581,49645,71707,2497,72307,71926,4600,10321,16413,63509,17483,25309,35969,75553,98089,5887,80343,92399,47979,26293,95731,84281,80329,21898,93251,82046,98361,11447,87904,27029,61507,12003,33871,7969,97367,94387,50647,71057,31777,3803,88661,26251,31991,94607,90830,99379,63032,42649,63957,92813,21341,91433,78777,45172,85678,48926,48219,90407,65393,88520,49429,85874,59347,95637,3033,80317,52957,57233,29597,80833,69997,44699,59557,407,52379,29342,39262,7873,38317,61057,29665,71069,6561,16057,82750,67111,20773,54083,77199,8783,73299,99208,50948,85781,96329,63599,14187,39137,75077,50570,3373,42004,34277,86297,74541,53269,31543,76283,75671)

        for(a in A){
            if(a % 389 == 0){
                print("$a ")
            }
        }
        println()

        println(largestComponentSize(A))
    }

}