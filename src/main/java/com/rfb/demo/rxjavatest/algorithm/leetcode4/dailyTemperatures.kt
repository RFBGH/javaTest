package com.rfb.demo.rxjavatest.algorithm.leetcode4

class dailyTemperatures {

    fun dailyTemperatures(T: IntArray): IntArray {

        val ans = IntArray(T.size)
        val map = IntArray(101){Int.MAX_VALUE}


        for(i in T.indices.reversed()){

            var min = Int.MAX_VALUE
            for(t in T[i]+1..100){
                if(min > map[t]){
                    min = map[t]
                }
            }

            if(min == Int.MAX_VALUE){
                ans[i] = 0
            }else{
                ans[i] = min - i
            }
            map[T[i]] = i
        }

        return ans
    }

}