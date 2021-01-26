package com.rfb.demo.rxjavatest.algorithm.leetcode4

class numEquivDominoPairs {

    fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {

        val map = HashMap<Int,Int>()
        dominoes.forEach {
            if(it[0] > it[1]){
                it.reverse()
            }

            val num = it[0]*10+it[1]
            val count = map[num]?:0
            map[num] = count+1
        }

        var ans = 0
        map.forEach { t, u ->
            ans += (u-1)*u/2
        }

        return ans
    }
}