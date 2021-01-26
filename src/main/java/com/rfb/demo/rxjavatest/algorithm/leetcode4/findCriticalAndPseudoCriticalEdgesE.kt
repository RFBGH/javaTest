package com.rfb.demo.rxjavatest.algorithm.leetcode4

class findCriticalAndPseudoCriticalEdgesE {

    private fun prim(s:Int, G:Array<ArrayList<IntArray>>, n:Int):List<IntArray>{

        val sure = BooleanArray(n)
        val dist = IntArray(n){Int.MAX_VALUE}
        val parent = IntArray(n){-1}

        dist[s] = 0
        val edges = ArrayList<IntArray>()

        for(I in 0 until n){

            var min = Int.MAX_VALUE
            var target = -1

            for(i in 0 until n){

                if(!sure[i] && min > dist[i]){
                    min = dist[i]
                    target = i
                }
            }

            if(target == -1){
                break
            }
            sure[target] = true

            G[target].forEach {
                val t = it[0]
                if(!sure[t] && dist[t] > dist[target] + it[1]){
                    dist[t] = dist[target] + it[1]
                    parent[t] = target
                }
            }
        }

        for(i in 0 until n){
            if(i == s){
                continue
            }

            edges.add(intArrayOf(i, parent[i]))
        }

        return edges
    }

    fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {

        val G = Array(n){ ArrayList<IntArray>() }

        val edgeMap = Array(n){IntArray(n)}

        edges.forEachIndexed { index, it ->
            G[it[0]].add(intArrayOf(it[1], it[2]))
            G[it[1]].add(intArrayOf(it[0], it[2]))

            edgeMap[it[0]][it[1]] = index
            edgeMap[it[1]][it[0]] = index
        }

        val keys = HashSet<Int>()
        val unkeys = HashSet<Int>()
        for(I in 0 until n){

            val edgeList = prim(I, G, n)
            val list = IntArray(edgeList.size){
                edgeMap[edgeList[it][0]][edgeList[it][1]]
            }

            if(keys.isEmpty() && unkeys.isEmpty()){
                list.forEach {
                    keys.add(it)
                }
                continue
            }

            list.forEach {
                if(!keys.contains(it)){
                    keys.remove(it)
                    unkeys.add(it)
                }
            }
        }

        val result = ArrayList<List<Int>>()
        result.add(ArrayList(keys))
        result.add(ArrayList(unkeys))
        return result
    }

}