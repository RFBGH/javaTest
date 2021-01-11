package com.rfb.demo.rxjavatest.kotlin.leetcode

class smallestStringWithSwaps {

    lateinit var parents:Array<Int>

    private fun level(v:Int): Pair<Int, Int>{

        var level = 0;
        var cur = v
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int){

        val pairV = level(v)
        val pairU = level(u)

        if(pairV.first> pairU.first){
            if (pairU.second != pairV.second) {
                parents[pairU.second] = pairV.second
            }
        }else{
            if (pairV.second != pairU.second) {
                parents[pairV.second] = pairU.second
            }
        }
    }

    fun smallestStringWithSwaps(s: String, pairs: List<List<Int>>): String {

        val ans = CharArray(s.length)

        parents = Array(s.length) {-1}
        for(pair in pairs){
            union(pair[0], pair[1])
        }

        val groups = Array(s.length){ArrayList<Int>()}
        for(i in s.indices){
            val parent = level(i)
            groups[parent.second].add(i)
        }

        for(group in groups){

            val counts = Array(26){0}
            for(i in group){
                counts[s[i]-'a']++
            }

            var index = 0
            for(i in counts.indices){
                while (counts[i] != 0){
                    counts[i]--
                    ans[group[index++]] = 'a'+i
                }
            }
        }

        return String(ans)
    }

    fun test(){

        val pairs = ArrayList<ArrayList<Int>>()
        val pair0 = ArrayList<Int>(arrayListOf(2,3))
        pairs.add(pair0)

        val pair1 = ArrayList<Int>(arrayListOf(3,2))
        pairs.add(pair1)

        val pair2 = ArrayList<Int>(arrayListOf(0,1))
        pairs.add(pair2)

        val pair3 = ArrayList<Int>(arrayListOf(4,0))
        pairs.add(pair3)

        val pair4 = ArrayList<Int>(arrayListOf(3,2))
        pairs.add(pair4)

        println(smallestStringWithSwaps("qdwyt", pairs))
    }

}