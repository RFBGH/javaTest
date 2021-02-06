package com.rfb.demo.rxjavatest.algorithm.leetcode4

class maxScore {

    fun maxScore(cardPoints: IntArray, k: Int): Int {

        var rest = cardPoints.size - k
        var sum = 0
        cardPoints.forEach {
            sum += it
        }

        var min = Int.MAX_VALUE
        var winSum = 0
        var left = 0
        var right = left
        while (right < cardPoints.size){

            if(rest > 0){
                rest--
                winSum += cardPoints[right++]
            }else{
                rest++
                winSum -= cardPoints[left++]
            }

            if(rest == 0 && min > winSum){
                min = winSum
            }
        }

        return sum - min
    }
}