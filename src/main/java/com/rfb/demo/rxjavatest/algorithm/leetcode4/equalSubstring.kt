package com.rfb.demo.rxjavatest.algorithm.leetcode4

import kotlin.math.abs

class equalSubstring {

    fun equalSubstring(s: String, t: String, maxCost: Int): Int {

        var max = 0
        var left = 0
        var right = 0
        var rest = maxCost

        while (right < s.length){

            if(s[right] == t[right]){
                right++
            }else{
                val cut = abs(s[right] - t[right])
                if(cut <= rest){
                    rest -= cut
                    right++
                }else{
                    rest += abs(s[left] - t[left])
                    left++
                }
            }

            if(right - left > max){
                max = right - left
            }
        }

        return max
    }
}