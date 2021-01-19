package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.HashMap

class getKth {

    val map = HashMap<Int, Int>()

    private fun getPriority(x:Int):Int{

        var step = 0
        var cur = x
        while (true){

            if(map.containsKey(cur)){
                step += map[cur]!!
                break
            }

            if(cur % 2 == 0){
                cur /= 2
            }else{
                cur = cur*3+1
            }

            step++
            if(cur == 1){
                break
            }
        }

        map[x] = step
        return step
    }

    fun getKth(lo: Int, hi: Int, k: Int): Int {

        val ans = Array(hi-lo+1){ IntArray(2) }

        for(i in lo..hi){
            ans[i-lo][0] = i
            ans[i-lo][1] = getPriority(i)
        }

        ans.sortWith(kotlin.Comparator { o1, o2 ->
            when{
                o1[1] < o2[1] -> -1
                o1[1] > o2[1] -> 1
                else -> when{
                    o1[0] < o2[0] -> -1
                    o1[0] > o2[0] -> 1
                    else -> 0
                }
            }
        })

        return ans[k-1][0]
    }

    fun test(){
        println(getKth(12, 15, 2))
    }
}