package com.rfb.demo.rxjavatest.algorithm.leetcode4

import java.lang.StringBuilder

class generateParenthesis {

    val ans = ArrayList<String>()

    private fun dfs(n:Int, path:ArrayList<Char>, left:Int, right:Int){
        if(left == n){
            val sb = StringBuilder()
            path.forEach {
                sb.append(it)
            }
            for (i in 0 until (n-right)){
                sb.append(')')
            }
            ans.add(sb.toString())
        }else{

            if(right < left){

                path.add('(')
                dfs(n, path, left+1, right)
                path.removeAt(path.size-1)

                path.add(')')
                dfs(n, path, left, right+1)
                path.removeAt(path.size-1)
            }else{
                path.add('(')
                dfs(n, path, left+1, right)
                path.removeAt(path.size-1)
            }
        }
    }

    fun generateParenthesis(n: Int): List<String> {
        dfs(n, ArrayList(), 0, 0)
        return ans
    }
}