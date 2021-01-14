package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*

class scoreOfParentheses {

    private var cur = 0
    private var content = ""

    private fun dfs():Int{

        var sum = 0
        while (cur < content.length){
            val c = content[cur++]
            if(c == '('){
                sum += dfs()
            }else{
                break
            }
        }

        return when (sum) {
            0 -> 1
            else -> sum*2
        }
    }

    fun scoreOfParentheses(S: String): Int {

        var sum = 0
        content = S
        while (true){
            cur++

            if(cur >= content.length){
                break
            }

            sum += dfs()
        }
        return sum
    }

    fun test(){
        println(scoreOfParentheses("(()(()))"))
    }
}