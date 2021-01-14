package com.rfb.demo.rxjavatest.kotlin.leetcode

import kotlin.collections.HashMap

class trulyMostPopular {

    private lateinit var parent:IntArray
    private lateinit var count:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var cur = u
        var level = 0
        while (parent[cur] != -1){
            cur = parent[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(u:Int, v:Int){

        val pairU = getLevelAndParent(u)
        val pairV = getLevelAndParent(v)

        if(pairU.second == pairV.second){
            return
        }

        if(pairU.first > pairV.first){
            parent[pairV.second] = pairU.second
            count[pairU.second] += count[pairV.second]
        }else{
            parent[pairU.second] = pairV.second
            count[pairV.second] += count[pairU.second]
        }
    }

    fun trulyMostPopular(names: Array<String>, synonyms: Array<String>): Array<String> {

        parent = IntArray(names.size){-1}
        count = IntArray(names.size){0}
        val nameMap = HashMap<String, Int>()

        val realNames = ArrayList<String>()

        for(i in names.indices){
            val name = names[i]

            val segments = name.split("(")
            nameMap[segments[0]] = i
            count[i] = segments[1].substring(0, segments[1].length-1).toInt()

            realNames.add(segments[0])
        }

        for(synonym in synonyms){
            val segments = synonym.split(",")
            val u = nameMap[segments[0].substring(1, segments[0].length)]?:-1
            val v = nameMap[segments[1].substring(0, segments[1].length-1)]?:-1
            if(u == -1 || v == -1){
                continue
            }

            union(u, v)
        }

        val results = Array(names.size){ArrayList<String>()}

        for(name in realNames){
            val u = nameMap[name]!!
            val pairU = getLevelAndParent(u)
            results[pairU.second].add(name)
        }

        val ans = ArrayList<String>()

        for(i in results.indices){

            val result = results[i]
            if(result.isEmpty()){
                continue
            }

            result.sort()
            ans.add("${result[0]}(${count[i]})")
        }

        return ans.toTypedArray()
    }

    fun test(){


        trulyMostPopular(arrayListOf("John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)").toTypedArray(),
                arrayListOf("(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)").toTypedArray())

    }

}