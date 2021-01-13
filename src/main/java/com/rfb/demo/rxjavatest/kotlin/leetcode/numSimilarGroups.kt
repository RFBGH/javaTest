package com.rfb.demo.rxjavatest.kotlin.leetcode

class numSimilarGroups {

    private lateinit var parent:IntArray

    private fun getLevelAndParent(u : Int):Pair<Int, Int>{
        var level = 0
        var cur = u
        while (parent[cur] != -1){
            cur = parent[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int){

        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)
        if(pairU.first > pairV.first){
            if(pairV.second != pairU.second){
                parent[pairV.second] = pairU.second
            }
        }else{
            if(pairU.second != pairV.second){
                parent[pairU.second] = pairV.second
            }
        }
    }

    fun numSimilarGroups(strs: Array<String>): Int {

        parent = IntArray(strs.size){
            -1
        }

        for(i in strs.indices){
            for(j in i+1 until strs.size){

                var diff = 0
                for(k in strs[i].indices){
                    if (strs[i][k] != strs[j][k]) {
                        diff++

                        if(diff > 2){
                            break
                        }
                    }
                }

                if(diff > 2){
                    continue
                }

                union(i, j)
            }
        }

        val ans = HashSet<Int>()
        for(i in strs.indices){
            val pair = getLevelAndParent(i)
            ans.add(pair.second)
        }

        return ans.size
    }

    fun test(){
        val strs = arrayOf<String>("qihcochwmglyiggvsqqfgjjxu","gcgqxiysqfqugmjgwclhjhovi","gjhoggxvcqlcsyifmqgqujwhi","wqoijxciuqlyghcvjhgsqfmgg","qshcoghwmglygqgviiqfjcjxu","jgcxqfqhuyimjglgihvcqsgow","qshcoghwmggylqgviiqfjcjxu","wcoijxqiuqlyghcvjhgsqgmgf","qshcoghwmglyiqgvigqfjcjxu","qgsjggjuiyihlqcxfovchqmwg","wcoijxjiuqlyghcvqhgsqgmgf","sijgumvhqwqioclcggxgyhfjq","lhogcgfqqihjuqsyicxgwmvgj","ijhoggxvcqlcsygfmqgqujwhi","qshcojhwmglyiqgvigqfgcjxu","wcoijxqiuqlyghcvjhgsqfmgg","qshcojhwmglyiggviqqfgcjxu","lhogcgqqfihjuqsyicxgwmvgj","xscjjyfiuglqigmgqwqghcvho","lhggcgfqqihjuqsyicxgwmvoj","lhgocgfqqihjuqsyicxgwmvgj","qihcojhwmglyiggvsqqfgcjxu","ojjycmqshgglwicfqguxvihgq","sijvumghqwqioclcggxgyhfjq","gglhhifwvqgqcoyumcgjjisqx")
        println(numSimilarGroups(strs))
    }
}