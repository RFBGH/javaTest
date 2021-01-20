package com.rfb.demo.rxjavatest.kotlin.leetcode

import kotlin.math.max

class maximumProduct{

    fun maximumProduct(nums: IntArray): Int {

        val counts = IntArray(2001)

        nums.forEach {
            counts[it+1000]++
        }

        var num0 = -1
        var num1 = -1
        var num2 = -1
        var num3 = -1
        var num4 = -1

        for(i in counts.indices){
            var count = counts[i]
            if(count == 0){
                continue
            }

            if(num0 == -1){
                num0 = i
                count--
            }

            if(count == 0){
                continue
            }

            if(num1 == -1){
                num1 = i
                break
            }
        }

        for(i in counts.indices.reversed()){

            var count = counts[i]
            if(count == 0){
                continue
            }

            if(num4 == -1){
                num4 = i
                count--
            }

            if(count == 0){
                continue
            }

            if(num3 == -1){
                num3 = i
                count--
            }

            if(count == 0){
                continue
            }

            if(num2 == -1){
                num2 = i
                break
            }
        }

        num0-=1000
        num1-=1000
        num2-=1000
        num3-=1000
        num4-=1000

        val numA = num2*num3*num4
        val numB = num0*num1*num4
        if(numA > numB){
            return numA
        }
        return numB
    }

    fun test(){
        println(maximumProduct(intArrayOf(1,2,3,4)))
    }

}