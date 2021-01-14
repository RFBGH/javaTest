package com.rfb.demo.rxjavatest.kotlin.leetcode

class equationsPossible {

    private val parent = IntArray(26){-1}

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var cur = u
        var level = 0
        while (parent[cur] != -1){
            cur = parent[cur]
            level++
        }
        return Pair(level, cur)
    }


    fun equationsPossible(equations: Array<String>): Boolean {

        for(equation in equations){

            val u = equation[0]-'a'
            val v = equation[3]-'a'

            val pairU = getLevelAndParent(u)
            val pairV = getLevelAndParent(v)

            if(equation[1]=='='){

                if(pairU.second != pairV.second){
                    if(pairU.first > pairV.first){
                        parent[pairV.second] = pairU.second
                    }else{
                        parent[pairU.second] = pairV.second
                    }
                }
            }
        }

        for(equation in equations){

            val u = equation[0]-'a'
            val v = equation[3]-'a'

            val pairU = getLevelAndParent(u)
            val pairV = getLevelAndParent(v)

            if(equation[1]=='!'){

                if(pairU.second == pairV.second){
                    return false
                }
            }
        }

        return true
    }
}